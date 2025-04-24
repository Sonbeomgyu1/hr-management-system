package com.apple.shop.contract;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import com.apple.shop.member.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {

	private final ContractRepository contractRepository;
	private final MemberRepository memberRepository;
	
	@GetMapping("/upload-form")
    public String showUploadForm(Model model, Principal principal) {
        
		 // 현재 로그인한 사용자의 username을 가져옴 (Spring Security에서 자동 주입)
	    String username = principal.getName();

	    // DB에서 username을 기준으로 회원 정보 조회
	    Optional<Member> memberOpt = memberRepository.findByUsername(username);

	    if (memberOpt.isPresent()) {
	        // 회원 정보가 존재하면
	        Member member = memberOpt.get();
	        
	        // 모델에 memberId 속성으로 회원의 ID 값을 추가 (HTML에서 th:value로 바인딩됨)
	        model.addAttribute("memberId", member.getId());
	    } else {
	        // 회원 정보가 존재하지 않는 경우, null을 전달 (예외 처리를 추가해도 좋음)
	        model.addAttribute("memberId", null);
	    }
	    // "contract/upload-test.html" 뷰 페이지로 이동
	    return "contract/upload-test";
	}
	
	@Value("${upload.dir}") // application.properties에 설정
    private String uploadDir;
	
	@PostMapping("/upload")
	public String uploadContract(@RequestParam("title") String title, // 제목
								 @RequestParam("file") MultipartFile file, // 파일
								 @RequestParam("startDate") String startDate, // 시작일
								 @RequestParam("endDate") String endDate,  // 종료일
								 @RequestParam("memberId") Long memberId) // 연결할 고객 ID
										 throws IOException {
		// 1. 파일 저장
		String originalFileName = file.getOriginalFilename(); // 원본 파일명

		// ⛳ 확장자 추출
		String extension = "";
		int dotIndex = originalFileName.lastIndexOf('.');
		if (dotIndex > 0) {
		    extension = originalFileName.substring(dotIndex);
		}

		// ⛳ 저장할 파일명 생성 (UUID + 확장자)
		String savedFileName = UUID.randomUUID().toString() + extension;
		String savedPath = uploadDir + File.separator + savedFileName;

		// ⛳ 폴더 없으면 생성
		File saveDir = new File(uploadDir);
		if (!saveDir.exists()) {
		    saveDir.mkdirs(); // 폴더 생성
		}

		// ⛳ 파일 저장
		file.transferTo(new File(savedPath));

		
		//2.계약 저장
		Contract contract = new Contract();
		contract.setTitle(title);
		contract.setFilePath(savedPath); // 파일 경로 저장
		contract.setOriginalFileName(originalFileName);
		contract.setStartDate(LocalDate.parse(startDate)); // 문자열 → LocalDate 변환
		contract.setEndDate(LocalDate.parse(endDate));
		
		// 3. 고객 정보 조회 및 연결
		Member member = memberRepository.findById(memberId).orElseThrow();
		contract.setMember(member);
		
		contractRepository.save(contract);
		return "redirect:/my-page"; // 업로드 후 다시 폼으로 이동
		
	}
	
	@GetMapping("/list")
	public String listContracts(Model model) {
	    List<Contract> contracts = contractRepository.findAll();
	    model.addAttribute("contracts", contracts);
	    return "contract/upload-list";
	}

	
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam("path") String path, Principal principal) throws IOException {
	    File file = new File(path);
	    if (!file.exists()) {
	        throw new FileNotFoundException("파일이 존재하지 않습니다: " + path);
	    }

	    // 파일 리소스 준비
	    Resource resource = new FileSystemResource(file);

	    // 한글 파일명 처리
	    String filename = file.getName();
	    String encodedFileName = URLEncoder.encode(filename, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
	    String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"; filename*=UTF-8''" + encodedFileName;

	    // 로그인한 사용자 ID로 계약서 조회 및 notified 변경
	    String username = principal.getName();
	    memberRepository.findByUsername(username).ifPresent(member -> {
	        contractRepository.findByMemberIdAndFilePath(member.getId(), path).ifPresent(contract -> {
	            // ✅ DB에 저장된 값이 0이면 1로 변경
	            if (!contract.isNotified()) {
	                contract.setNotified(true); // JPA에서 boolean true → DB 1로 저장됨
	                contractRepository.save(contract);
	            }
	        });
	    });

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}



	@PostMapping("/delete")
	@Transactional
	public String deleteContract(
	        // 삭제할 계약서의 ID를 요청 파라미터로 받음 (form의 input name="contractId" 와 매핑됨)
	        @RequestParam("contractId") Long contractId, 
	        
	        // 현재 로그인된 사용자의 인증 정보를 가져옴
	        Authentication auth) {
	    
	    // 인증 정보에서 사용자 객체(CustomUser)를 꺼냄
	    CustomUser user = (CustomUser) auth.getPrincipal();

	    // 만약 현재 사용자가 ROLE_ADMIN 권한을 가지고 있다면
	    if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
	        // 관리자는 모든 계약서 삭제 가능
	        contractRepository.deleteById(contractId);
	    } else {
	        // 일반 사용자는 자신이 등록한 계약서만 삭제 가능 (ID와 본인 memberId 일치 조건)
	        contractRepository.deleteByIdAndMemberId(contractId, user.getId());
	    }

	    // 삭제 후 계약서 목록 페이지로 리다이렉트
	    return "redirect:/my-page";
	}


	
	@GetMapping("/check-expiring")
	public String checkExpiringContracts() {
	    List<Contract> contracts = contractRepository.findAll();

	    for (Contract contract : contracts) {
	        if (!contract.isNotified() && contract.getEndDate().isBefore(LocalDate.now().plusDays(3))) {
	            // TODO: 알림 전송
	            contract.setNotified(true);
	            contractRepository.save(contract);
	        }
	    }

	    return "redirect:/contracts/list";
	}



}

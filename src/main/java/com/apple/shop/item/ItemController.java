package com.apple.shop.item;

import com.apple.shop.comment.Comment;
import com.apple.shop.comment.CommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CommentRepository commentRepository;

    //리스트페이지에서 상품목록들 보여지게 하는 코드
    @GetMapping("/list")
    String list(Model model) {
        itemService.modelList(model);  // ✅ model을 전달하여 데이터 추가
        return "item/list.html";
    }

    @GetMapping("/write")
    String write() {
        return "item/write.html";
    }

    //상세페이지 form 데이터 보내기
    @PostMapping("/add")
    String writePost(@RequestParam(name = "title") String title, 
                     @RequestParam(name = "price") Integer price) {
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(name = "id") Long id, 
                         Model model, HttpServletRequest request) {
        Optional<Item> result = itemRepository.findById(id);

        if (result.isPresent()) {
            Item item = result.get();
            model.addAttribute("data", item);

            // 댓글 조회 (재활용)
            List<Comment> comments = commentRepository.findAllByParentIdAndType(id, "item");
            model.addAttribute("comments", comments);

            model.addAttribute("referer", request.getRequestURI());

            return "item/detail"; // Thymeleaf 템플릿 반환
        } else {
            return "redirect:/list"; // 데이터 없을 경우 목록 페이지로 리디렉션
        }
    }

    //연필 눌르면 각 아이디에 번호에 맞게 데이터 가져오기
    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable(name = "id") Long id, Model model) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            model.addAttribute("data", item.get());
        } else {
            return "redirect:/list"; // 해당 아이템이 없다면 목록으로 리다이렉트
        }
        return "item/edit"; // edit.html로 이동
    }

    //수정폼태그에서 보내는 데이터 
    @PostMapping("/edit")
    String editItem(@RequestParam(name = "title") String title, 
                    @RequestParam(name = "price") Integer price, 
                    @RequestParam(name = "id") Long id) {
        itemService.formEdit(title, price, id);
        return "redirect:/list";
    }

    //ajax로 서버에 데이터 날릴떄
    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam(name = "id") long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    //지금 list페이지에는 fot문이 돌고있어서 이렇게하면 글 목록 5개씩 보여줌
    @GetMapping("/list/page/{abc}")
    String getListPage(Model model, @PathVariable(name = "abc") Integer abc) {
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(abc - 1, 5));
        model.addAttribute("items", result.getContent());
        model.addAttribute("currentPage", abc);
        model.addAttribute("totalPages", result.getTotalPages());
        return "item/list.html";
    }

    //검색기능
    @PostMapping("/search")
    String postSearch(@RequestParam(name = "searchText") String searchText, 
                      Model model) {
        var searchtext = itemRepository.rawQuery1(searchText);
        model.addAttribute("items", searchtext);
        return "item/list.html";
    }
}

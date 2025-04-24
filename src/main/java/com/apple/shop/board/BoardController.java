package com.apple.shop.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/board")
    String List(Model model){
        List<Board> result = boardRepository.findAll();
        model.addAttribute("boards", result);
        return "board/board.html";
    }

    @GetMapping("/boardwrite")
    String boardwrite(){
        return "board/boardwrite";
    }

    // 게시글 쓰기 Post
    @PostMapping("/boardAdd")
    String boardAdd(
        @RequestParam(name = "boardcontent") String boardcontent, // 게시글 내용
        @RequestParam(name = "boardtitle") String boardtitle     // 게시글 제목
    ){
        Board board = new Board();
        board.setBoardcontent(boardcontent);
        board.setBoardtitle(boardtitle);
        boardRepository.save(board);
        return "redirect:/board";
    }

    // 링크 눌르면 그 상세페이지로 이동
    @GetMapping("/boarddetail/{id}")
    String boarddetail(
        @PathVariable(name = "id") Long id,                     // 게시글 ID
        Model model, 
        @RequestParam(name = "boarddate", required = false) LocalDateTime boarddate  // 게시글 날짜 (필요한 경우)
    ){
        Optional<Board> result = boardRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("data", result.get());
            return "board/boarddetail";
        } else {
            return "redirect:/board";
        }
    }

    @GetMapping("/boardedit/{id}")
    public String editBoard(@PathVariable(name = "id") Long id, Model model){
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()){
            model.addAttribute("data", board.get());
        } else {
            return "redirect:/board";
        }
        return "board/boardedit";
    }

    @PostMapping("/boardedit")
    String editBoard(
        @RequestParam(name = "boardcontent") String boardcontent,  // 수정된 게시글 내용
        @RequestParam(name = "boardtitle") String boardtitle,      // 수정된 게시글 제목
        @RequestParam(name = "id") Long id                        // 게시글 ID
    ){
        Board board = new Board();
        board.setId(id);
        board.setBoardcontent(boardcontent);
        board.setBoardtitle(boardtitle);
        boardRepository.save(board);
        return "redirect:/board";
    }

    // 휴지통 클릭하면 삭제하기
    @DeleteMapping("/board")
    ResponseEntity<String> deleateBoard(
        @RequestParam(name = "id") long id   // 삭제할 게시글 ID
    ){
        boardRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    // board 검색 기능
    @PostMapping("/boardsearch")
    String BoardSearch(
        @RequestParam(name = "searchText", required = false) String searchText,  // 검색 텍스트
        Model model
    ) {
        List<Board> searchResult;

        // searchText가 비어 있으면 전체 목록 조회
        if (searchText == null || searchText.trim().isEmpty()) {
            searchResult = boardRepository.findAll(); // 전체 게시글 가져오기
        } else {
            searchResult = boardRepository.rowQuery2(searchText); // 검색 결과 가져오기
        }

        model.addAttribute("boards", searchResult);
        return "board/board";
    }
}

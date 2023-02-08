package com.ezen.controller;

import com.ezen.entity.Board;
import com.ezen.entity.Member;
import com.ezen.entity.Search;
import com.ezen.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@Controller
@Log4j2
public class BoardController {

    @Autowired
    BoardService boardService;

//    @GetMapping("/boardList")
//    public String boardList() {
//        return "board/boardList";
//    }
    @GetMapping("/board")
    public String getBoard(Board board, Model model) {
        log.info("게시글 조회" + board);
        model.addAttribute("board", boardService.getBoard(board));
        return "board/getBoard";
    }

    @GetMapping("/insertBoard")
    public String insertBoardForm(@SessionAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "board/insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board, @SessionAttribute("member") Member member) {
        board.setMember(member);
        boardService.insertBoard(board);
        return "redirect:/boardList";
    }

    @GetMapping("/updateBoard")
    public String updateBoardForm(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "board/updateBoard";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(Board board, Model model) {
        boardService.updateBoard(board);
        return "redirect:/boardList";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        log.info(board.getBoardSeq() + "번째 게시글 삭제");
        boardService.deleteBoard(board);
        return "redirect:/boardList";
    }

    @RequestMapping("/boardList")
    public String getNoticeList(@RequestParam(value = "page", defaultValue = "0") int page, Search search, Model model,
                                HttpServletRequest request) {
        if(search.getSearchCondition() == null) {
            search.setSearchCondition("TITLE");
        }
        if(search.getSearchKeyword() == null) {
            search.setSearchKeyword("");
        }
        Page<Board> boardList = boardService.getBoardList(page, search);
        log.info("게시글 목록: " + boardList.getContent());
        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }
}

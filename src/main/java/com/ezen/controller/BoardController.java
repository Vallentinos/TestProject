package com.ezen.controller;

import com.ezen.entity.Board;
import com.ezen.entity.Member;
import com.ezen.entity.Search;
import com.ezen.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("board", boardService.getBoard(board));
        return "board/getBoard";
    }

    @GetMapping("/insertBoard")
    public String insertBoardForm(@SessionAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "board/insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board, Model model) {
        model.addAttribute("board", board);
        boardService.insertBoard(board);
        return "redirect:/boardList";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(Board board, Model model) {
        boardService.updateBoard(board);
        return "redirect:/board";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        boardService.deleteBoard(board);
        return "redirect:/boardList";
    }

    @RequestMapping("/boardList")
    public String getBoardList(Search search, Model model) {
        if(search.getSearchCondition() == null) {
            search.setSearchCondition("TITLE");
        }
        if(search.getSearchKeyword() == null) {
            search.setSearchKeyword("");
        }
        Page<Board> boardList = boardService.getBoardList(search);
        log.info("게시글 목록: " + boardList);
        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }
}

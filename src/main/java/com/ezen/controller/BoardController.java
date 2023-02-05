package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/boardList")
    public String boardList() {
        return "board/boardList";
    }

    @GetMapping("/board")
    public String insertBoard() {
        return "board/insertBoard";
    }
}

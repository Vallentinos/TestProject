package com.ezen.controller;

import com.ezen.entity.BoardReply;
import com.ezen.entity.Member;
import com.ezen.service.BoardReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardReplyController {

    @Autowired
    BoardReplyService boardReplyService;
//    @Autowired
//    BoardReplyRepository boardReplyRepository;

    @RequestMapping("/getBoardReply")
    public @ResponseBody List<BoardReply> boardReplyList(@ModelAttribute BoardReply boardReply) {
        return boardReplyService.getBoardReplyList(boardReply);
    }

    @PostMapping("/insertReply")
    public @ResponseBody void insertReply(@ModelAttribute BoardReply boardReply,
                                          @SessionAttribute("member") Member member,
                                          @RequestParam(value = "boardSeq", defaultValue = "0") Long boardSeq) {
        boardReplyService.insertBoardReply(boardReply);
    }
}

package com.ezen.test;

import com.ezen.entity.Board;
import com.ezen.entity.BoardReply;
import com.ezen.entity.Member;
import com.ezen.persistence.BoardReplyRepository;
import com.ezen.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardReplyRepositoryTest {

    @Autowired
    BoardReplyRepository boardReplyRepository;
    @Autowired
    BoardRepository boardRepository;

    @Test
    void 댓글_테스트() {

        Board board = new Board();
        Member member = new Member();

        board.setBoardSeq(79L);
        member.setUsername("member12");


        BoardReply boardReply = new BoardReply();

        boardReply.setBoard(board);
        boardReply.setContent("댓글 테스트 내용");
        boardReply.setMember(member);
        boardReply.setRegdate(new Date());

        boardReplyRepository.save(boardReply);
    }
}

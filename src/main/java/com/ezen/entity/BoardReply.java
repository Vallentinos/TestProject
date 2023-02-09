package com.ezen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
@Entity
public class BoardReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BoardReplySeq;
    private String content;
    private Date regdate;

    @ManyToOne
    @JoinColumn(name="username", nullable=false, updatable=false)
    private Member member; // 댓글 작성자

    public void setMember(Member member) {
        this.member = member;
        member.getBoardReplyList().add(this);
    }

    @ManyToOne
    @JoinColumn(name="boardSeq", nullable = false, updatable = false)
    private Board board; // 댓글 작성할 게시글

    public void setBoard(Board board) {
        this.board = board;
        board.getBoardReplyList().add(this);
    }
}

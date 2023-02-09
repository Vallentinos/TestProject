package com.ezen.entity;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@ToString(exclude = {"member", "board"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
=======
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
>>>>>>> d93dc0c (게시판 댓글 엔티티추가)
@Entity
public class BoardReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Long boardReplySeq;
    private String content;

    @Column(insertable=false, updatable=false, columnDefinition="date default sysdate")
    private Date regdate; // 등록일

    @LastModifiedDate
    private Date updateDate;
=======
    private Long BoardReplySeq;
    private String content;
    private Date regdate;
>>>>>>> d93dc0c (게시판 댓글 엔티티추가)

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

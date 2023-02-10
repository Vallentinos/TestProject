package com.ezen.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@ToString(exclude = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardSeq;

	private String title;
//	private String writer;
	private String content;

	@Column(columnDefinition = "char default '0'")
	private String category; // 1: 공지사항, 2: 자유게시판

	@Column(insertable=false, updatable=false, columnDefinition="date default sysdate")
	private Date regdate;

	@ManyToOne
	@JoinColumn(name="username", nullable=false, updatable=false)
	private Member member; // 게시글 작성자

	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<BoardReply> boardReplyList = new ArrayList<BoardReply>();

}

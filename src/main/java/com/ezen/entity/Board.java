package com.ezen.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long board_seq;

	private String title;
	private String writer;
	private String content;

	@Column(insertable=false, updatable=false, columnDefinition="date default sysdate")
	private Date regdate;

//	@ManyToOne
//	@JoinColumn(name="username", nullable=false, updatable=false)
//	private Member member;
//
	public Board() {

	}
//
//	public void setMember(Member member) {
//		this.member = member;
//		member.getBoardList().add(this);
//	}

}

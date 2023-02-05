package com.ezen.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class Funding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long funding_seq;
	private String name;
	private String title;
	private String image; // 메인이미지
	private String content; // 내용
	private int price;
	private Date period;
	private String account;
	private String kind; // 한식, 중식, 양식, 일식
	private int result; // 1. 등록전, 2. 등록완료
	private Date regdate;
	private String agree;
	
}

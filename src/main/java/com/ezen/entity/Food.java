package com.ezen.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="member")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Food {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long food_seq; // 식자재번호
	
	private String category; // 대분류
	private String name; // 소분류
	private Date exp; // 유통기한
	
	/*
	@Builder
	public Food(Long food_seq, String category, String name, Date exp) {
		this.food_seq = food_seq;
		this.category = category;
		this.name = name;
		this.exp = exp;
	}
	*/
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false, updatable=false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getFoodList().add(this);
	}
}

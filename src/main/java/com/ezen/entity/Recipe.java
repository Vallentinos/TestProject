package com.ezen.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;	

@Getter
@ToString
@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long recipe_seq;
	
	private String id; // 사용자 아이디
	private String recipe_name; // 레시피명
	private String degree; // 난이도
	private int cooking_time; // 요리시간
	private String name; // 식자재명
	private String mainImage; // 메인이미지
	private String image; // 설명사진
	private String content; // 설명
	private String amount; // 요리분량
	private String kind; // 종류
	@Column(insertable=false, updatable=false, columnDefinition = "number default 0")
	private int good; // 추천수
}

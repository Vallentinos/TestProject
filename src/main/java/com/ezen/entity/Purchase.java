package com.ezen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseSeq;
    
    private String comment; // 배송요청사항
    private String agree;
    private String payer; // 입금자명
    private Date regdate; // 구매일자
    private int quantity; // 구매수량
    private int result; // 진행상태 -> 1: 배송전, 2: 배송중, 3: 배송완료
    private int price; // 결제금액
}

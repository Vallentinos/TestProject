package com.ezen.service;

import com.ezen.entity.Heart;

public interface HeartService {

    void insertHeart(Heart heart); // 좋아요

    void deleteHeart(Heart heart); // 좋아요 취소
}

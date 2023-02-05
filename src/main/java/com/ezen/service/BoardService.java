package com.ezen.service;

import com.ezen.entity.Board;

public interface BoardService {

    void insertBoard(Board board); // 게시판 등록

    void updateBoard(Board board); // 수정

    void deleteBoard(Board board); // 삭제
}

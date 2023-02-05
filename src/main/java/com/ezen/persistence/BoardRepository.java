package com.ezen.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}

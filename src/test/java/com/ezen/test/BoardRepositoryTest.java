package com.ezen.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ezen.entity.Board;
import com.ezen.persistence.BoardRepository;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
//	@Test
//	public void saveBoard() {
//
//		BoardDTO boardDTO = BoardDTO.builder()
//				.title("게시글 테스트 제목")
//				.content("게시글 테스트 내용")
//				.regdate(new Date())
//				.build();
//
//		Board board = boardDTO.toEntity(boardDTO);
//		boardRepository.save(board);
//	}

}

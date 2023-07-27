package com.board.board.service;

import com.board.board.dto.BoardDTO;
import com.board.board.dto.BoardListDTO;
import com.board.board.dto.BoardRegisterDTO;

import org.springframework.transaction.annotation.Transactional;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;

@Transactional
public interface BoardService {

    // 수정
    void modify(BoardDTO boardDTO);

    // 목록
    PageResponseDTO<BoardListDTO> getList(PageRequestDTO pageRequestDTO);

    // 등록
    void insert(BoardRegisterDTO dto);

    // 조회
    BoardDTO read(Integer bno);

    // 삭제
    void delete(Integer bno);
    
}

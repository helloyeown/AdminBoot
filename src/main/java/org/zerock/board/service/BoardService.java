package org.zerock.board.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;

@Transactional
public interface BoardService {

    // 수정
    void modify(BoardDTO boardDTO);

    // 목록
    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    // 등록
    int insert(BoardDTO dto);

    // 조회
    BoardDTO read(int bno);

    // 삭제
    void delete(int bno);
    
}

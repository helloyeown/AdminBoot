package org.zerock.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.mappers.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    
    private final BoardMapper boardMapper;


    // 목록
    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {
        List<BoardDTO> list = boardMapper.getList(pageRequestDTO);
        long total = boardMapper.getCountEnd(pageRequestDTO);

		return PageResponseDTO.<BoardDTO>withAll()
						.list(list)
						.total(total)
						.build();
    }

    // 등록
    @Override
    public int insert(BoardDTO dto) {
        
        return boardMapper.regist(dto);

    }

    // 조회
    @Override
    public BoardDTO read(int bno) {

        return boardMapper.getOne(bno);

    }

    // 삭제
    @Override
    public void delete(int bno) {

        boardMapper.delete(bno);

    }

    // 수정
    @Override
    public void modify(BoardDTO boardDTO) {
        
        boardMapper.modify(boardDTO);
    
    }

}

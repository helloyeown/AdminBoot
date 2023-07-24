package com.board.board.service;

import java.util.List;

import com.board.board.dto.BoardDTO;
import org.springframework.stereotype.Service;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.mappers.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    
    private final BoardMapper boardMapper;


    // 목록
    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        List<BoardDTO> list = boardMapper.getList(pageRequestDTO);
        long total = boardMapper.getCountEnd(pageRequestDTO);
        log.info("----------------" + pageRequestDTO.getType());
        log.info("----------------" + pageRequestDTO.getTypes());

		return PageResponseDTO.<BoardDTO>withAll()
						.list(list)
						.total(total)
                        .pageRequestDTO(pageRequestDTO)
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

package com.board.board.mappers;

import java.util.List;

import com.board.board.dto.BoardDTO;
import com.board.board.dto.PageRequestDTO;

public interface BoardMapper {
    
    List<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    long getCountEnd(PageRequestDTO pageRequestDTO);

    int regist(BoardDTO boardDTO);

    BoardDTO getOne(int bno);

    void modify(BoardDTO boardDTO);

    void delete(int bno);

}

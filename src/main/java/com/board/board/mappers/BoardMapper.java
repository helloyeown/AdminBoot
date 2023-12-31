package com.board.board.mappers;

import java.util.List;

import com.board.board.dto.BoardDTO;
import com.board.board.dto.BoardListDTO;
import com.board.board.dto.BoardRegisterDTO;
import com.board.board.dto.PageRequestDTO;

public interface BoardMapper {
    
    List<BoardListDTO> getList(PageRequestDTO pageRequestDTO);

    long getCountEnd(PageRequestDTO pageRequestDTO);

    int regist(BoardRegisterDTO boardRegisterDTO);

    BoardDTO getOne(Integer bno);

    int modify(BoardDTO boardDTO);

    void delete(int bno);

}

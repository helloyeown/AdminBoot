package org.zerock.board.mappers;

import java.util.List;

import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;

public interface BoardMapper {
    
    List<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    long getCountEnd(PageRequestDTO pageRequestDTO);

    int regist(BoardDTO boardDTO);

    BoardDTO getOne(int bno);

    void modify(BoardDTO boardDTO);

    void delete(int bno);

}

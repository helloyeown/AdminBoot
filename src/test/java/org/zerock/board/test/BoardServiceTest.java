package org.zerock.board.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.mappers.BoardMapper;
import org.zerock.board.service.BoardService;
import org.zerock.board.service.BoardServiceImpl;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTest {
    
    @Autowired(required = false)
    BoardService boardService;


    @Test
    public void paging(){
        
        PageRequestDTO dto = PageRequestDTO.builder().page(1).size(10).build();

        PageResponseDTO list = boardService.getList(dto);

        log.info(list);

    }


    @Test
    public void modify(){
        
        BoardDTO boardDTO = BoardDTO.builder()
                    .bno(65631)
                    .title("수정")
                    .content("new content")
                    .writer("new writer")
                    .build();

        boardService.modify(boardDTO);

    }


    @Test
    public void delete(){
        boardService.delete(65627);
    }


    @Test
    public void read(){

        BoardDTO dto = boardService.read(65628);

        log.info(dto);

    }

    @Test
    public void listTest(){

        List<BoardDTO> list = boardService.getList();

        log.info(list);

    }

    @Test
    public void registTest(){
        
        BoardDTO dto = BoardDTO.builder()
            .title("new title")
            .content("new content")
            .writer("new writer")
            .build();

        int result = boardService.insert(dto);

        log.info(result + "================");

    }

}

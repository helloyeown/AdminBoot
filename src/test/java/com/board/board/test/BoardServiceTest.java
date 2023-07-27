package com.board.board.test;

import com.board.board.dto.BoardDTO;
import com.board.board.dto.BoardRegisterDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.mappers.FileMapper;
import com.board.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.board.board.dto.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTest {
    
    @Autowired(required = false)
    private BoardService boardService;

    @Autowired
    private FileMapper fileMapper;


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
        boardService.delete(1024);
    }


    @Test
    public void read(){

        BoardDTO dto = boardService.read(65628);

        log.info(dto);

    }

    @Test
    public void listTest(){

        PageRequestDTO dto = PageRequestDTO.builder()
        .build();

        log.info(boardService.getList(dto));

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

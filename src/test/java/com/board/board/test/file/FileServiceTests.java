package com.board.board.test.file;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.board.dto.BoardRegisterDTO;
import com.board.board.mappers.FileMapper;
import com.board.board.service.BoardService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FileServiceTests {
    
    @Autowired
    private BoardService boardService;

    @Autowired
    private FileMapper fileMapper;

    @Test
    public void fileRegistTest(){

        BoardRegisterDTO registerDTO = BoardRegisterDTO.builder()
                .title("File Service Test Title1")
                .content("File Service Test Content1")
                .writer("File Service test writer1")
                .fileNames(List.of(UUID.randomUUID() + "_f1.jpg", UUID.randomUUID() + "_f2.jpg"))
                .build();

        boardService.insert(registerDTO);

    }

}

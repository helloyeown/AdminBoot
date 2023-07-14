package org.zerock.board.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.mappers.BoardMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardDAOTest {
    
    @Autowired (required = false)
    private BoardMapper boardMapper;


    // 삭제
    @Test
    public void delete(){
        
        boardMapper.delete(65629);

    }


    // 수정
    @Test
    public void modify(){
        
        BoardDTO dto = BoardDTO.builder()
                    .bno(65629)
                    .title("modify")
                    .content("modify")
                    .build();

        boardMapper.modify(dto);
    }

    
    // 조회
    @Test
    public void getone(){

        BoardDTO dto = boardMapper.getOne(65628);

        log.info(dto);

    }


    // 등록
    @Test
    public void insertTest(){

        BoardDTO dto = BoardDTO.builder()
                    .title("new title")
                    .content("new content")
                    .writer("new writer")
                    .build();

        int result = boardMapper.regist(dto);

        log.info(result);

    }

    // 목록
    @Test
    public void getListTest(){

        PageRequestDTO dto = PageRequestDTO.builder().build();
        
        List<BoardDTO> list = boardMapper.getList(dto);

        log.info("getList Test...........");
        log.info(list);

    }

}

package org.zerock.board.test.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.mappers.ReplyMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyMapperTests {
    
    @Autowired(required = false)
    private ReplyMapper replyMapper;

    @Test
    public void listTest(){
        Integer bno = 524;
        PageRequestDTO dto = PageRequestDTO.builder().build();

        log.info("list Test...............");
        log.info(replyMapper.getList(bno, dto));
    }

}

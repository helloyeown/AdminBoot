package org.zerock.board.test.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.service.reply.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    
    @Autowired(required = false)
    private ReplyService replyService;

    // list
    @Test
    public void listTest(){
        Integer bno = 2;
        PageRequestDTO dto = PageRequestDTO.builder().build();

        log.info(replyService.getList(bno, dto));
    }


}

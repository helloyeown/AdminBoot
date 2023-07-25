package com.board.board.test.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.reply.ReplyDTO;
import com.board.board.service.reply.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    
    @Autowired(required = false)
    private ReplyService replyService;

    // list
    @Test
    public void listTest(){
        Integer bno = 1021;
        PageRequestDTO dto = PageRequestDTO.builder().build();

        log.info(replyService.getList(bno, dto));
    } 

    // regist 원댓글
    @Test
    public void replyRegistTest(){
        ReplyDTO dto = ReplyDTO.builder().bno(1395).reply("serviceTest").replyer("user").build();

        log.info(replyService.regist(dto));
    }

    // regist 대댓글
    @Test
    public void testRegistReplyChild(){
        // gno가 0이 아니므로 registReplyChild가 실행됨
        ReplyDTO dto = ReplyDTO.builder().bno(1395).reply("replyChild").replyer("user2").gno(21).build();

        log.info(replyService.regist(dto));
    }

    // read
    @Test
    public void readTest(){
        log.info(replyService.read(24));
    }

    // delete
    @Test
    public void deleteTest(){
        replyService.delete(24);
    }

    // modify
    @Test
    public void modifyTest(){

        ReplyDTO dto = ReplyDTO.builder().rno(3).reply("modify test").build();

        replyService.modify(dto);
    }


}

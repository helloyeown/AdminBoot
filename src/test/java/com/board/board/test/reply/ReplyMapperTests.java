package com.board.board.test.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.reply.ReplyDTO;
import com.board.board.mappers.ReplyMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyMapperTests {
    
    @Autowired(required = false)
    private ReplyMapper replyMapper;

    @Test
    public void listTest(){
        Integer bno = 1;
        PageRequestDTO dto = PageRequestDTO.builder().build();

        log.info("list Test...............");
        log.info(replyMapper.getList(bno, dto));
    }

    @Test
    public void registTest(){
        // reply 등록 -> gno값 부여
        ReplyDTO dto = ReplyDTO.builder().bno(1395).reply("test").replyer("user").build();
        PageRequestDTO page = PageRequestDTO.builder().build();

        replyMapper.regist(dto);
        replyMapper.updateReplyGno(dto.getRno());

        log.info(replyMapper.getList(1395, page));
    }

    @Test
    public void readTest(){
        log.info(replyMapper.read(24));
    }

    @Test
    public void deleteTest(){
        replyMapper.delete(26);
    }

    @Test
    public void modifyTest(){
        ReplyDTO dto = ReplyDTO.builder().reply("modify test").rno(2).build();

        replyMapper.modify(dto);
    }

}

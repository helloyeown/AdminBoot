package com.board.board.service.reply;

import org.springframework.transaction.annotation.Transactional;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.dto.reply.ReplyDTO;

@Transactional
public interface ReplyService {
    
    // list
    PageResponseDTO<ReplyDTO> getList(Integer bno, PageRequestDTO requestDTO);

    // regist
    Integer regist(ReplyDTO replyDTO);

    // read
    ReplyDTO read(Integer rno);

    // delete
    void delete(Integer rno);

    // modify
    void modify(ReplyDTO dto);
    
}

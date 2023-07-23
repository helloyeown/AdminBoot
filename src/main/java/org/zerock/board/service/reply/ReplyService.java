package org.zerock.board.service.reply;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.dto.reply.ReplyDTO;

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

}

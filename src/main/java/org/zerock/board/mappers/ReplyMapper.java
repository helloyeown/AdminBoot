package org.zerock.board.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.reply.ReplyDTO;

public interface ReplyMapper {
    
    // 목록
    List<ReplyDTO> getList(@Param("bno") Integer bno, @Param("pr") PageRequestDTO pageRequestDTO);

    // total
    int getRnoCnt(Integer bno);

    // 등록
    // gno==0(원댓글)
    int regist(ReplyDTO replyDTO);

    // gno==0(원댓글) -> gno를 rno 값으로 업데이트
    int updateReplyGno(Integer rno);

    // 대댓글 등록
    // gno != 0
    int registReplyChild(ReplyDTO replyDTO);


}

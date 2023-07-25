package com.board.board.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.reply.ReplyDTO;

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

    // 조회
    ReplyDTO read(Integer rno);

    // 삭제
    int delete(Integer rno);

    // 수정
    int modify(ReplyDTO replyDTO);


}

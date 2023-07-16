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

}

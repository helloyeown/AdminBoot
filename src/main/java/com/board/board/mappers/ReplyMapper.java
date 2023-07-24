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

}

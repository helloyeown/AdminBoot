package com.board.board.service.reply;

import java.util.List;

import org.springframework.stereotype.Service;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.dto.reply.ReplyDTO;
import com.board.board.mappers.ReplyMapper;

import groovyjarjarantlr4.v4.codegen.model.dbg;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;

    @Override
    public PageResponseDTO<ReplyDTO> getList(Integer bno, PageRequestDTO requestDTO) {
        
        // 사이즈 20개로 조정
        requestDTO.setSize(20);
        // 페이지
        int pageNum = requestDTO.getPage();

        // total

        int total = replyMapper.getRnoCnt(bno);
        // 끝 페이지 계산
        if (!requestDTO.isReplyLast()) {
            // 마지막 페이지가 아니라면 페이지 숫자 생성
            pageNum = (int)(Math.ceil(total/(double)requestDTO.getSize()));

            pageNum = pageNum <= 0 ? 1 : pageNum;
        }
        
        requestDTO.setPage(pageNum);

        // list
        List<ReplyDTO> list = replyMapper.getList(bno, requestDTO);

        return PageResponseDTO.<ReplyDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(requestDTO)
                .build();
    }

    @Override
    public Integer regist(ReplyDTO replyDTO) {
        
        // rno값을 result에 담아서 리턴
        Integer result = null;
        int gno = replyDTO.getGno();

        // 원댓글일 때
        if(gno == 0) {
            int count = replyMapper.regist(replyDTO);

            // 정상 등록이 아닐 경우 예외 처리
            if(count != 1) {
                throw new RuntimeException("Reply Insert Exception");
            }

            Integer rno = replyDTO.getRno();
            replyMapper.updateReplyGno(rno);

            result = rno;

        // 대댓글일 때
        } else {

            int count = replyMapper.registReplyChild(replyDTO);

            if(count != 1) {
                throw new RuntimeException("Reply Insert Exception");
            }

            result = replyDTO.getRno();

        }
        return result;
    }

    @Override
    public ReplyDTO read(Integer rno) { 
        return replyMapper.read(rno);
    }

    @Override
    public void delete(Integer rno) {
        replyMapper.delete(rno);
    }
    
}

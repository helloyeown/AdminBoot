package org.zerock.board.service.reply;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.dto.reply.ReplyDTO;
import org.zerock.board.mappers.ReplyMapper;

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
    
}

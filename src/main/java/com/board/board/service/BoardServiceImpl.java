package com.board.board.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.board.board.dto.BoardDTO;
import com.board.board.dto.BoardRegisterDTO;

import org.springframework.stereotype.Service;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.mappers.BoardMapper;
import com.board.board.mappers.FileMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;


    // 목록
    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        List<BoardDTO> list = boardMapper.getList(pageRequestDTO);
        long total = boardMapper.getCountEnd(pageRequestDTO);
        log.info("----------------" + pageRequestDTO.getType());
        log.info("----------------" + pageRequestDTO.getTypes());

		return PageResponseDTO.<BoardDTO>withAll()
						.list(list)
						.total(total)
                        .pageRequestDTO(pageRequestDTO)
						.build();
    }

    // 등록
    @Override
    public void insert(BoardRegisterDTO dto) {

        log.info("Board Service insert.........................");

        // 게시판 등록
        int count = boardMapper.regist(dto);

        // 파일 이름을 List로 가져오기
        List<String> fileNames = dto.getFileNames();

        // 게시판과 파일이 등록되었으면 실행
        if(count > 0 && dto.getFileNames() != null && !dto.getFileNames().isEmpty()) {
            
            Integer bno = dto.getBno();
            log.info("----------------------bno: " + bno);

            // 여러 스레드가 동시에 변수 값을 변경하려 할 때, 한 번에 하나의 스레드만 변수에 접근할 수 있도록 함
            // 멀티스레드 환경에서도 안전하게 변수를 조작
            AtomicInteger index = new AtomicInteger();

            // 등록된 파일 fileNames에서 추출
            if(fileNames != null && fileNames.size() > 0){
                List<Map<String, String>> list = fileNames.stream().map(str -> {

                    log.info("----------------------");
                    log.info("----------------------");
                    log.info(str);
                    log.info(str.length());

                    log.info("----------------------");


                    String uuid = str.substring(0, 36);
                    String fileName = str.substring(37);

                    return Map.of("uuid", uuid, "fileName", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());
                }).collect(Collectors.toList());
                
                log.info("=====================================================================");
                log.info("=====================================================================");
                log.info(list);
                
                // 파일 등록 실행
                fileMapper.registFile(list);
            }
        }
    }

    // 조회
    @Override
    public BoardDTO read(Integer bno) {

        return boardMapper.getOne(bno);

    }

    // 삭제
    @Override
    public void delete(Integer bno) {

        // 게시글 삭제하면서 파일도 함께 삭제
        boardMapper.delete(bno);
        fileMapper.deleteImage(bno);

    }

    // 수정
    @Override
    public void modify(BoardDTO boardDTO) {
        
        boardMapper.modify(boardDTO);
    
    }

}

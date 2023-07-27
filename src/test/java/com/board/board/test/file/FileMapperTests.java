package com.board.board.test.file;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.board.dto.BoardRegisterDTO;
import com.board.board.mappers.BoardMapper;
import com.board.board.mappers.FileMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FileMapperTests {
    
    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private BoardMapper boardMapper;

    // 등록
    @Test
    public void insertFileTest(){
        
        BoardRegisterDTO registerDTO = BoardRegisterDTO.builder()
                .title("file title")
                .content("file content")
                .writer("file writer")
                .fileNames(List.of(UUID.randomUUID() + "_f1.jpg", UUID.randomUUID() + "_f2.jpg"))
                .build();

        // 파일 이름 list로 가져오기
        List<String> fileNames = registerDTO.getFileNames();

        // 게시판 등록
        int count = boardMapper.regist(registerDTO);
        log.info("insert product count: " + count);

        // 등록 성공, 파일이 등록되었다면 실행
        if(count > 0 && registerDTO.getFileNames() != null && !registerDTO.getFileNames().isEmpty()){
            // bno 가져오기
            Integer bno = registerDTO.getBno();
            log.info("-----------------------------bno: " + bno);

            // 여러 스레드가 동시에 변수 값을 변경하려 할 때, 한 번에 하나의 스레드만 변수에 접근할 수 있도록 함
            // 멀티스레드 환경에서도 안전하게 변수를 조작
            AtomicInteger index = new AtomicInteger();

            // 등록된 fileNames에서 추출
            List<Map<String, String>> list = fileNames.stream().map(str -> {
                // uuid 가져오기
                String uuid = str.substring(0, 36);
                // 실제 파일명 가져오기
                String fileName = str.substring(37);

                // return Map에 담기
                return Map.of("uuid", uuid, "fileName", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            log.info("=====================================================================");
            log.info("=====================================================================");
            log.info(list);

            // 파일 등록 실행
            fileMapper.registFile(list);
        }
    }


    @Test
    public void deleteFileTest(){
        fileMapper.deleteImage(1031);
    }

}

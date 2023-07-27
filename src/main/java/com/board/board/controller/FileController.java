package com.board.board.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.board.board.dto.file.FileUploadDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/files/")
public class FileController {

    // tbl_file에 이미지를 저장하고 tbl_board list 불러올 때 bno로 join
    
    // custom 예외처리 (파일 없음)
    public static class DataNotFoundException extends RuntimeException {
        public DataNotFoundException(String msg) {
            super(msg);
        }
    }

    // import 시에 springframework으로 시작하는 Value
    @Value("${com.board.upload.path}")
    private String uploadPath;      // nginx

    // 파일 업로드
    @PostMapping("upload")
    public List<FileUploadDTO> fileUpload(MultipartFile[] files){

        // 파일이 없을 때
        if(files == null || files.length == 0) {
            return null;
        }

        List<FileUploadDTO> fileList = new ArrayList<>();

        // 파일이 여러 개일 경우가 있으니 for로 파일 하나씩 추출
        for (MultipartFile file : files) {
            
            FileUploadDTO result = null;

            // 파일 이름, 크기
            String fileName = file.getOriginalFilename();
            long size = file.getSize();

            String uuid = UUID.randomUUID().toString();
            // 파일명 저장
            String saveFileName = uuid + "_" + fileName;
            // 실제 파일 저장
            File saveFile = new File(uploadPath, saveFileName);

            // 파일 복사할 때 예외 처리 필요
            try {
                // FileCopyUtils 사용하여 InputStream으로 받고 OutPutStream으로 보내줌
                // getBytes() 파일을 바이트 배열로 받아줌
                FileCopyUtils.copy(file.getBytes(), saveFile);

                result = FileUploadDTO.builder()
                        .uuid(uuid)
                        .fileName(fileName).build();

                // 파일 확장자 이미지 파일 체크
                String mimeType = Files.probeContentType(saveFile.toPath());
                log.info("mimeType: " + mimeType);

                // mimeType이 image인지 체크
                if(mimeType.startsWith("image")) {
                    // 썸네일 파일 생성 (s_)
                    File thumbFile = new File(uploadPath, "s_" + saveFileName);
                    Thumbnailator.createThumbnail(saveFile, thumbFile, 80, 80);
                    // getLink 사용을 위해 img true로 반환
                    result.setImg(true);
                }

                fileList.add(result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileList;
    }

    // 파일 삭제
    @DeleteMapping("remove/{fileName}")
    public Map<String, String> removeFile(@PathVariable("fileName") String fileName){

        File originFile = new File(uploadPath, fileName);

        try {
            
            String mimeType = Files.probeContentType(originFile.toPath());

            if(mimeType.startsWith("image")) {
                File thumbFile = new File(uploadPath, "s_" + fileName);
                // 실제 파일 삭제
                thumbFile.delete();
            }
            originFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Map.of("result", "success");

    }
    

}

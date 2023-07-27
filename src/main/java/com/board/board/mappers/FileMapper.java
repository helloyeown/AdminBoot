package com.board.board.mappers;

import java.util.List;
import java.util.Map;

public interface FileMapper {
    
    // 파일 등록 -> Map의 리스트를 파라미터로 받음
    int registFile(List<Map<String, String>> imageList);

    int deleteImage(Integer bno);

}

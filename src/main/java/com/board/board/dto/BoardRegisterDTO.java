package com.board.board.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRegisterDTO {
    
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private String dueDate;

    private List<String> fileNames;     // 파일업로드 파일명 저장

}

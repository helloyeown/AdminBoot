package com.board.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class BoardDTO {
    
    private int bno;
    private String title;
    private String content;
    private String writer;
    private String dueDate;
    private String updateDate;
    private boolean exist;

    private List<String> fileNames;

}

package com.board.board.dto.file;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUploadDTO {

    private String uuid;        // pk
    private String fileName;    // 실제 파일이름 (not null)
    private boolean img;        // 이미지 존재 여부 (default 0, not null)
    // bno -> FK, delete 했을 때 cascade 

    // 이미지 파일 경로 (nginx)
    public String getLink(){
        if(img) {
            return "s_" + uuid + "_" + fileName;
        } else {
            return "noImage.png";
        }
    }

}

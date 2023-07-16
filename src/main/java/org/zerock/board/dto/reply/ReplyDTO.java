package org.zerock.board.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDTO {  
    
  private int rno;                    //pk
  private int bno;                    //게시물 번호
  private String reply;               //댓글내용
  private String replyer;             //작성자
  private String replyDate;           //작성시간
  
  @Builder.Default
  private int gno = 0;                //대댓글처리 번호 0 default설정
  private int step;                   //대댓글 여부 확인
  private boolean status;             //댓글 삭제여부
}

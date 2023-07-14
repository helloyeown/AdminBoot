package org.zerock.board.dto;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class PageRequestDTO {
    
    @Builder.Default
	private int page = 1;
	@Builder.Default
	private int size = 10;

    private String link;

    // 검색
    private String type;
    private String keyword;

    public void setPage(int page){
        if(page<=0){
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public void setSize(int size){
        if(size > 100 || size <= 0) {
            this.size = 10;
        } else {
            this.size = size;
        }
    }

    public int getSkip(){
        return (this.page - 1) * this.size;
    }

    // 다음 버튼을 출력하기 위해 필요한 아이템 수
    public int getCountEnd(){
        int temp = (int)(Math.ceil(this.page / 10.0)) * (10 * this.size);

        return temp + 1;
    }

    // link
    public String getLink() {

        if (link == null) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("page=" + this.page);
            stringBuilder.append("&size=" + this.size);

            // 검색 타입
            if (type != null && type.length() > 0){
                stringBuilder.append("&type=" + this.type);
            }

            // 검색어
            if (keyword != null && keyword.length() > 0) {
                try {
                    stringBuilder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                    // URL로 공백이나 특수문자가 들어오지 않도록 인코딩 처리
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            link = stringBuilder.toString();
        }
        return  link;
    }

    // type 배열로 반환 처리
    public String[] getTypes(){
        if(this.type == null || this.type.isEmpty()){
            return null;
        }
        return this.type.split("");
    }

}

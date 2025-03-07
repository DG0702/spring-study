package me.Lee.Springstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.Lee.Springstudy.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
// 요청 받을 객체
public class AddArticleRequest {
    
    // 필드 생성
    private String title;
    private String content;

    // 생성자를 사용해 객체 생성
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}

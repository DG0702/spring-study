package me.Lee.Springstudy.dto;

import lombok.Getter;
import me.Lee.Springstudy.domain.Article;

@Getter
public class ArticleResponse {

    // 필드
    private final String title;
    private final String content;

    // 생성자
    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}

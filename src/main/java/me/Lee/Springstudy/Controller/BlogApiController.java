package me.Lee.Springstudy.Controller;

import lombok.RequiredArgsConstructor;

import me.Lee.Springstudy.Service.BlogService;
import me.Lee.Springstudy.domain.Article;
import me.Lee.Springstudy.dto.AddArticleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    // 의존성 주입
    private final BlogService blogService;


    // 블로그 글 추가
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

}

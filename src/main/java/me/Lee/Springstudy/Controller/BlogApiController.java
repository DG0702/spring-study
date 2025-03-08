package me.Lee.Springstudy.Controller;

import lombok.RequiredArgsConstructor;

import me.Lee.Springstudy.Service.BlogService;
import me.Lee.Springstudy.domain.Article;
import me.Lee.Springstudy.dto.AddArticleRequest;
import me.Lee.Springstudy.dto.ArticleResponse;
import me.Lee.Springstudy.dto.UpdateArticleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 블로그 모든 글 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    // 블로그 글 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }


    // 블로그 글 삭재
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }


    // 블로그 글 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id,request);

        return ResponseEntity.ok()
                .body(updateArticle);

    }
}

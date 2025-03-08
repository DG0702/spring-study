package me.Lee.Springstudy.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.Lee.Springstudy.Repository.BlogRepository;
import me.Lee.Springstudy.domain.Article;
import me.Lee.Springstudy.dto.AddArticleRequest;
import me.Lee.Springstudy.dto.UpdateArticleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

// final 필드의 생성자 생성
@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    // 블로그 모든 글 조회
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 블로그 글 조회
    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    // 블로그 글 삭제
    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    // 블로그 글 수정
    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(),request.getContent());

        return article;
    }


}

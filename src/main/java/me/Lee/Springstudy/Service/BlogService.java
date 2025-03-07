package me.Lee.Springstudy.Service;

import lombok.RequiredArgsConstructor;
import me.Lee.Springstudy.Repository.BlogRepository;
import me.Lee.Springstudy.domain.Article;
import me.Lee.Springstudy.dto.AddArticleRequest;
import org.springframework.stereotype.Service;

// final 필드의 생성자 생성
@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

}

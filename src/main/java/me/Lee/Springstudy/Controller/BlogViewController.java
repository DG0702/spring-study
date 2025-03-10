package me.Lee.Springstudy.Controller;

import lombok.RequiredArgsConstructor;
import me.Lee.Springstudy.Service.BlogService;
import me.Lee.Springstudy.domain.Article;
import me.Lee.Springstudy.dto.ArticleListViewResponse;
import me.Lee.Springstudy.dto.ArticleViewResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    // 의존성 주입
    private final BlogService blogService;

    // 블로그 글 목록 구현
    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles",articles);
        return "/blog/articleList";
    }

    
    
    // 블로그 글 구현(조회)
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id , Model model){

        Article article = blogService.findById(id);
        model.addAttribute("article",new ArticleViewResponse(article));
        return "/blog/article";
    }


    // 블로그 글 생성, 수정
    @GetMapping("new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){

        // id가 없다면 -> 글 생성
        if(id == null){
            model.addAttribute("article", new ArticleViewResponse());
        }
        // id가 있다면 -> 글 수정
        else{
            Article article = blogService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }

        return "/blog/newArticle";
    }

}

package me.Lee.Springstudy.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.Lee.Springstudy.Repository.BlogRepository;
import me.Lee.Springstudy.domain.Article;
import me.Lee.Springstudy.dto.AddArticleRequest;
import me.Lee.Springstudy.dto.UpdateArticleRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.xml.transform.Result;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    // 테스트용 MVC 요청, 응답 기능을 제공하는 유틸리티 클래스
    @Autowired
    protected MockMvc mockMvc;

    // 직렬화, 역직렬화를 위한 클래스
    @Autowired
    protected ObjectMapper objectMapper;

    // 웹 애플리케이션 설정 정보를 담고 있는 컨테이너
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    public void MockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();

        blogRepository.deleteAll();
    }


    @DisplayName("addArticle : 블로그 글 추가.")
    @Test
    public void addArticle() throws Exception {

        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title,content);

        // 직렬화 (자바 -> JSON)
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        // 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then
        // 검증
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }


    @DisplayName("findAllArticles : 블로그 글 목록 조회.")
    @Test
    public void findAllArticles() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON)
        );
        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));

    }

    
    @DisplayName("findArticle : 블로그 글 조회")
    @Test
    public void findArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle =  blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when

        final ResultActions result = mockMvc.perform(get(url,savedArticle.getId())
                .accept(MediaType.APPLICATION_JSON)
        );


        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    @DisplayName("deleteArticle : 블로그 글 삭제")
    @Test
    public void deleteArticle() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when

        mockMvc.perform(delete(url,savedArticle.getId()))
                .andExpect(status().isOk());

        // then
        List<Article> articles = blogRepository.findAll();

        assertThat(articles).isEmpty();

    }


    @DisplayName("/updateArticle : 블로그 글 수정")
    @Test
    public void updateArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(newTitle,newContent);
        // when

        final ResultActions result = mockMvc.perform(put(url,savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateArticleRequest)));

        // then
        result.andExpect(status().isOk());

        Article article = blogRepository.findById(savedArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);

    }



}
package me.Lee.Springstudy.repository;

import me.Lee.Springstudy.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}

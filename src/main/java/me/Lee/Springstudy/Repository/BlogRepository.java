package me.Lee.Springstudy.Repository;

import me.Lee.Springstudy.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}

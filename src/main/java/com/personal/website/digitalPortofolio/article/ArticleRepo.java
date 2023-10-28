package com.personal.website.digitalPortofolio.article;

import com.personal.website.digitalPortofolio.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
	@Query(
			value = "SELECT * FROM article where user_id = ?1",
			nativeQuery = true
	)
	List<Article> findArticlesByUser(long userId);
}

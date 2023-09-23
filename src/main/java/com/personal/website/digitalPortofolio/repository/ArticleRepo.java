package com.personal.website.digitalPortofolio.repository;

import com.personal.website.digitalPortofolio.DTO.AuthorDTO;
import com.personal.website.digitalPortofolio.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
	@Query(
			value = "SELECT * FROM article where author_id = ?1",
			nativeQuery = true
	)
	List<Article> findArticlesByUser(long authorId);
}

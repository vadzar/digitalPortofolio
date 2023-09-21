package com.personal.website.digitalPortofolio.repository;

import com.personal.website.digitalPortofolio.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
}

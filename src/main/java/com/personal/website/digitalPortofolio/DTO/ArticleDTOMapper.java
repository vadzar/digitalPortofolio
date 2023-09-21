package com.personal.website.digitalPortofolio.DTO;

import com.personal.website.digitalPortofolio.model.Article;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ArticleDTOMapper implements Function<Article, ArticleDTO> {
	@Override
	public ArticleDTO apply(Article article) {
		return new ArticleDTO(
				article.getId(),
				article.getTitle(),
				article.getContent(),
				article.getDateTimePosted(),
				article.getLastEdited()
		);
	}
}

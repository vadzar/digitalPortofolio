package com.personal.website.digitalPortofolio.DTO;

import com.personal.website.digitalPortofolio.model.ArticleType;

import java.time.LocalDateTime;

public record ArticleDTO (
	long id,
	String title,
	String author,
	String content,
	LocalDateTime dateTimePosted,
	LocalDateTime lastEdited,
	ArticleType type
) {
}

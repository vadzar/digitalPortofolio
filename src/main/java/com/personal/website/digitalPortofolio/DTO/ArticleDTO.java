package com.personal.website.digitalPortofolio.DTO;

import java.time.LocalDateTime;

public record ArticleDTO (
	long id,
	String title,
	String content,
	LocalDateTime dateTimePosted,
	LocalDateTime lastEdited
) {
}

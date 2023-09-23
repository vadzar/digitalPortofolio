package com.personal.website.digitalPortofolio.author;


public record AuthorDTO(
		long id,
		String name,
		String email,
		AuthorType type
) {
}

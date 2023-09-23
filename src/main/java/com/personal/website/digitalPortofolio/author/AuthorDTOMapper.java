package com.personal.website.digitalPortofolio.author;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AuthorDTOMapper implements Function<Author, AuthorDTO> {
	@Override
	public AuthorDTO apply(Author author) {
		return new AuthorDTO(
				author.getId(),
				author.getName(),
				author.getEmail(),
				author.getType()
		);
	}
}

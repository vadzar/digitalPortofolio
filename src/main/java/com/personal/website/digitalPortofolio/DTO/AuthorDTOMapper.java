package com.personal.website.digitalPortofolio.DTO;

import com.personal.website.digitalPortofolio.model.Author;
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

package com.personal.website.digitalPortofolio.DTO;


import com.personal.website.digitalPortofolio.model.AuthorType;

public record AuthorDTO(
		long id,
		String name,
		String email,
		AuthorType type
) {
}

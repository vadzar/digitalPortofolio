package com.personal.website.digitalPortofolio.User;

public record UserDTO(
		long id,
		String firstName,
		String lastName,
		String email,
		UserRole role
) {
}

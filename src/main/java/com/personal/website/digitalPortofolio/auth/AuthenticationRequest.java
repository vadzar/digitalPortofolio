package com.personal.website.digitalPortofolio.auth;

public record AuthenticationRequest(
		String email,
		String password
) {
}

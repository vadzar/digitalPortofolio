package com.personal.website.digitalPortofolio.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record AuthenticationResponse(
		String token
) {
}

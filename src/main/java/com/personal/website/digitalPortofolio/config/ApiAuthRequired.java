package com.personal.website.digitalPortofolio.config;

import com.personal.website.digitalPortofolio.User.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ApiAuthRequired {
	protected User currentUser;

	public User getCurrentUser() {
		currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return currentUser;
	}

	public String getUserEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}

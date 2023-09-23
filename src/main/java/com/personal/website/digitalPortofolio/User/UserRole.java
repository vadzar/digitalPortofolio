package com.personal.website.digitalPortofolio.User;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public enum UserRole {
	ADMIN("App Admin"), AUTHOR("Article Author"), EDITOR("Article Editor");

	private final String display;
	UserRole(String d) {
		this.display = d;
	}

	public String getDisplay() {
		return display;
	}

	public Collection<String> getDisplayList() {
		return Arrays.stream(values())
				.map(UserRole::getDisplay)
				.collect(Collectors.toList());
	}
}

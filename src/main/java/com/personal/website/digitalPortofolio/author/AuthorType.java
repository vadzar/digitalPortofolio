package com.personal.website.digitalPortofolio.author;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AuthorType {
	ADMIN("Super Admin"), AUTHOR("Author");

	private final String display;

	AuthorType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public List<String> getDisplayList() {
		return Arrays.stream(values())
				.map(AuthorType::getDisplay)
				.collect(Collectors.toList());
	}

}

package com.personal.website.digitalPortofolio.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public enum ArticleType {
	PROJECT("Project"), BLOG("Blog");

	private final String display;

	ArticleType(String d) {
		this.display = d;
	}

	public String getDisplay() {
		return display;
	}

	static List<String> getDisplayList() {
		return Arrays.stream(values())
				.map(ArticleType::getDisplay)
				.collect(Collectors.toList());
	}
}

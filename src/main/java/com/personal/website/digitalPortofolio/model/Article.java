package com.personal.website.digitalPortofolio.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	private String title;
	private String author;

	@Column(name="content", columnDefinition = "TEXT")
	private String content;

	private LocalDateTime dateTimePosted;
	private LocalDateTime lastEdited;

	@Enumerated(value = EnumType.STRING)
	ArticleType type;

	public Article() {
	}

	public Article(String title, String author, String content, ArticleType type) {
		this.title = title;
		this.author = author;
		this.content = content;
		this.type = type;
	}

	public enum ArticleType {
		PROJECT, BLOG
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getDateTimePosted() {
		return dateTimePosted;
	}

	public LocalDateTime getLastEdited() {
		return lastEdited;
	}

	public ArticleType getType() {
		return type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDateTimePosted(LocalDateTime dateTimePosted) {
		this.dateTimePosted = dateTimePosted;
	}

	public void setLastEdited(LocalDateTime lastEdited) {
		this.lastEdited = lastEdited;
	}

	public void setType(ArticleType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", content='" + content + '\'' +
				", dateTimePosted=" + dateTimePosted +
				", lastEdited=" + lastEdited +
				'}';
	}
}

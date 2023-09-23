package com.personal.website.digitalPortofolio.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	private String title;

	@Column(name="content", columnDefinition = "TEXT")
	private String content;

	private LocalDateTime dateTimePosted;
	private LocalDateTime lastEdited;

	@Enumerated(value = EnumType.STRING)
	ArticleType type;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Author author;

	public Article() {
	}

	public Article(String title, String content, ArticleType type) {
		this.title = title;
		this.content = content;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
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

	public Author getAuthor() {
		return author;
	}

	public String getAuthorName() { return author != null ? author.getName() : ""; }

	public void setTitle(String title) {
		this.title = title;
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

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + getAuthor() + '\'' +
				", content='" + content + '\'' +
				", dateTimePosted=" + dateTimePosted +
				", lastEdited=" + lastEdited +
				'}';
	}
}

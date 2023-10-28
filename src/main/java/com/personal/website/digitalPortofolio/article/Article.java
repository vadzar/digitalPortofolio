package com.personal.website.digitalPortofolio.article;

import com.personal.website.digitalPortofolio.User.User;
import com.personal.website.digitalPortofolio.author.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	User user;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Author author;

	public Article(String title, String content, ArticleType type) {
		this.title = title;
		this.content = content;
		this.type = type;
	}

	public String getAuthorName() { return author != null ? author.getName() : ""; }

}

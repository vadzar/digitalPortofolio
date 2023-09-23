package com.personal.website.digitalPortofolio.article;

import com.personal.website.digitalPortofolio.article.Article;
import com.personal.website.digitalPortofolio.article.ArticleRepo;
import com.personal.website.digitalPortofolio.article.ArticleType;
import com.personal.website.digitalPortofolio.author.Author;
import com.personal.website.digitalPortofolio.author.AuthorRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ArticleRepoTest {
	@Autowired
	ArticleRepo underTest;

	@Autowired
	AuthorRepo authorRepo;

	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}

	@Test
	void findArticlesByUser() {
		Author author = new Author(
				"test",
				"author",
				"testauthor@mail.com"
		);
		authorRepo.save(author);

		Article article = new Article(
				"test title",
				"test content",
				ArticleType.PROJECT
		);
		article.setAuthor(author);
		underTest.save(article);

		List<Article> articles = underTest.findArticlesByUser(author.getId());

		assertThat(articles).isInstanceOf(List.class);
		assertThat(articles).isNotNull();
	}
}
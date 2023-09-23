package com.personal.website.digitalPortofolio.author;

import com.personal.website.digitalPortofolio.author.Author;
import com.personal.website.digitalPortofolio.author.AuthorRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AuthorRepoTest {
	@Autowired
	AuthorRepo underTest;

	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}

	@Test
	void canFindByEmail() {
		Author author = new Author(
				"test",
				"user",
				"testuser@gmail.com"
		);
		underTest.save(author);

		Optional<Author> expected = underTest.findByEmail(author.getEmail());

		assertThat(expected).isNotNull();
		assertThat(expected.get()).isSameAs(author);
	}
}
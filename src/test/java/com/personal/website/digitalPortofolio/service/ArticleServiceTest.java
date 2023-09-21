package com.personal.website.digitalPortofolio.service;

import com.personal.website.digitalPortofolio.DTO.ArticleDTO;
import com.personal.website.digitalPortofolio.DTO.ArticleDTOMapper;
import com.personal.website.digitalPortofolio.model.Article;
import com.personal.website.digitalPortofolio.repository.ArticleRepo;
import exception.ApiBadRequestException;
import exception.ApiNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
	@Mock
	private ArticleRepo articleRepo;

	private ArticleService underTest;

	private final ArticleDTOMapper articleDTOMapper = new ArticleDTOMapper();

	private Article articleTest;

	@BeforeEach
	void setUp() {
		underTest = new ArticleService(articleRepo, articleDTOMapper);
		articleTest = new Article(
				"test article title",
				"test author",
				"this is article test content",
				Article.ArticleType.PROJECT
		);
	}

	@Test
	void canGetList() {
		// when
		underTest.getList();
		// then
		verify(articleRepo).findAll();
	}

	@Test
	void canGetArticle() {
		// given
		given(articleRepo.findById(articleTest.getId()))
				.willReturn(Optional.of(articleTest));
		ArticleDTO expected = articleDTOMapper.apply(articleTest);
		// when
		ArticleDTO result = underTest.getArticle(articleTest.getId());
		// then
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(expected);

	}

	@Test
	void canNotGetArticle() {
		// when
		// then
		assertThatThrownBy(() -> underTest.getArticle(articleTest.getId()))
				.isInstanceOf(ApiNotFoundException.class)
				.hasMessageContaining(String.format("Article with id %s not found", articleTest.getId()));
	}

	@Test
	void canCreateArticle() {
		// given
		given(articleRepo.save(articleTest))
				.willReturn(articleTest);
		// when
		underTest.createArticle(articleTest);
		// then
		ArgumentCaptor<Article> articleArgumentCaptor = ArgumentCaptor.forClass(Article.class);
		verify(articleRepo).save(articleArgumentCaptor.capture());
		Article captorArticle = (Article) articleArgumentCaptor.getValue();

		assertThat(captorArticle).isEqualTo(articleTest);
	}

	@Test
	void canNotCreateArticle() {
		// when
		// then
		assertThatThrownBy(() -> underTest.createArticle(null))
				.isInstanceOf(ApiBadRequestException.class)
				.hasMessageContaining("Article can't be empty");
	}

	@Test
	void canUpdateArticle() {
		// given
		given(articleRepo.findById(articleTest.getId()))
				.willReturn(Optional.of(articleTest));
		given(articleRepo.save(articleTest))
				.willReturn(articleTest);
		ArticleDTO beforeUpdate = articleDTOMapper.apply(articleTest);
		// when
		ArticleDTO result = underTest.updateArticle(articleTest.getId(), articleTest);
		// then
		verify(articleRepo).save(articleTest);
		assertThat(result).isNotNull();
		assertThat(result.lastEdited()).isNotEqualTo(beforeUpdate.lastEdited());
	}

	@Test
	void canNotUpdateArticle() {
		// when
		// then
		assertThatThrownBy(()-> underTest.updateArticle(articleTest.getId(), articleTest))
				.isInstanceOf(ApiNotFoundException.class)
				.hasMessageContaining(String.format("Article with id %s not found", articleTest.getId()));
		verify(articleRepo, never()).save(articleTest);
	}

	@Test
	void canDeleteArticle() {
		// given
		given(articleRepo.findById(articleTest.getId()))
				.willReturn(Optional.of(articleTest));
		String expected = String.format("Article with id: %s successfully deleted", articleTest.getId());
		// when
		String result = underTest.deleteArticle(articleTest.getId());
		// then
		verify(articleRepo).delete(articleTest);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void canNotDeleteArticle() {
		// when
		// then
		assertThatThrownBy(() -> underTest.deleteArticle(articleTest.getId()))
				.isInstanceOf(ApiNotFoundException.class)
				.hasMessageContaining(String.format("Article with id %s not found", articleTest.getId()));
		verify(articleRepo, never()).delete(articleTest);
	}
}
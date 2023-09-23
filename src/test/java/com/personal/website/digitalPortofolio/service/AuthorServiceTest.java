package com.personal.website.digitalPortofolio.service;

import com.personal.website.digitalPortofolio.DTO.AuthorDTO;
import com.personal.website.digitalPortofolio.DTO.AuthorDTOMapper;
import com.personal.website.digitalPortofolio.exception.ApiBadRequestException;
import com.personal.website.digitalPortofolio.exception.ApiNotFoundException;
import com.personal.website.digitalPortofolio.model.Author;
import com.personal.website.digitalPortofolio.repository.AuthorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
	@Mock
	private AuthorRepo authorRepo;
	private AuthorService underTest;
	private final AuthorDTOMapper authorDTOMapper = new AuthorDTOMapper();

	private Author author;

	@BeforeEach
	void setUp() {
		underTest = new AuthorService(authorRepo, authorDTOMapper);
		author = new Author(
				"test",
				"author",
				"testauthor@mail.com"
		);
	}

	@Test
	void canGetUserList() {
		// when
		underTest.getUserList();
		// then
		verify(authorRepo).findAll();
	}

	@Test
	void canGetUserByEmail() {
		// given
		given(authorRepo.findByEmail(author.getEmail()))
				.willReturn(Optional.of(author));
		AuthorDTO expected = authorDTOMapper.apply(author);
		// when
		AuthorDTO result = underTest.getUserByEmail(author.getEmail());
		// then
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void canNotGetUserByEmail() {
		// when
		// then
		assertThatThrownBy(() -> underTest.getUserByEmail(author.getEmail()))
				.isInstanceOf(ApiNotFoundException.class)
				.hasMessageContaining(String.format("User with email %s not found", author.getEmail()));
	}

	@Test
	void canGetUserById() {
		// given
		given(authorRepo.findById(author.getId()))
				.willReturn(Optional.of(author));
		AuthorDTO expected = authorDTOMapper.apply(author);
		// when
		AuthorDTO result = underTest.getUserById(author.getId());
		// then
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void canNotGetUserById() {
		// when
		// then
		assertThatThrownBy(() -> underTest.getUserById(author.getId()))
				.isInstanceOf(ApiNotFoundException.class)
				.hasMessageContaining(String.format("User with id %s not found", author.getId()));
	}

	@Test
	void canCreateUser() {
		// given
		given(authorRepo.save(author))
				.willReturn(author);
		// when
		underTest.createUser(author);
		// then
		ArgumentCaptor<Author> authorArgumentCaptor = ArgumentCaptor.forClass(Author.class);
		verify(authorRepo).save(authorArgumentCaptor.capture());
		Author authorCaptured = (Author) authorArgumentCaptor.getValue();

		assertThat(authorCaptured).isEqualTo(author);
	}

	@Test
	void canNotCreateUser() {
		// given
		given(authorRepo.findByEmail(author.getEmail()))
				.willReturn(Optional.of(author));
		// when
		// then
		assertThatThrownBy(() -> underTest.createUser(author))
				.isInstanceOf(ApiBadRequestException.class)
				.hasMessageContaining(
						String.format("User email %s is used, please use another email", author.getEmail())
				);
		verify(authorRepo, never()).save(author);
	}

	@Test
	void canUpdateUser() {
		// given
		given(authorRepo.findById(author.getId()))
				.willReturn(Optional.of(author));
		given(authorRepo.save(author))
				.willReturn(author);
		AuthorDTO expected = authorDTOMapper.apply(author);
		// when
		AuthorDTO result = underTest.updateUser(author, author.getId());
		// then
		assertThat(result).isEqualTo(expected);
		verify(authorRepo).save(author);
	}

	@Test
	void canNotUpdateUser() {
		// when
		// then
		assertThatThrownBy(() -> underTest.updateUser(author, author.getId()))
				.isInstanceOf(ApiNotFoundException.class)
				.hasMessageContaining(
						String.format("User with id %s not found", author.getId())
				);
		verify(authorRepo, never()).save(author);
	}

	@Test
	void canDeleteUser() {
		// given
		given(authorRepo.findById(author.getId()))
				.willReturn(Optional.of(author));
		Map<String, String> expected = Map.of("message", String.format("User with id %s deleted", author.getId()));
		// when
		Map<String, String> result = underTest.deleteUser(author.getId());
		// then
		verify(authorRepo).delete(author);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void canNotDeleteUser() {
		// when
		// then
		assertThatThrownBy(() -> underTest.deleteUser(author.getId()))
				.isInstanceOf(ApiNotFoundException.class)
				.hasMessageContaining(
						String.format("User with id %s not found", author.getId())
				);
		verify(authorRepo, never()).delete(author);
	}
}
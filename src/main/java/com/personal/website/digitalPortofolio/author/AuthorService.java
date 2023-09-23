package com.personal.website.digitalPortofolio.author;

import com.personal.website.digitalPortofolio.exception.ApiBadRequestException;
import com.personal.website.digitalPortofolio.exception.ApiNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {
	private final AuthorRepo authorRepo;
	private final AuthorDTOMapper authorDTOMapper;

	public AuthorService(AuthorRepo authorRepo, AuthorDTOMapper authorDTOMapper) {
		this.authorRepo = authorRepo;
		this.authorDTOMapper = authorDTOMapper;
	}

	public List<AuthorDTO> getUserList() {
		return authorRepo.findAll()
				.stream()
				.map(authorDTOMapper)
				.collect(Collectors.toList());
	}

	public AuthorDTO getUserByEmail(String email) {
		return authorRepo.findByEmail(email)
				.map(authorDTOMapper)
				.orElseThrow(() -> new ApiNotFoundException(String.format("User with email %s not found", email)));
	}

	public AuthorDTO getUserById(long id) {
		return authorRepo.findById(id)
				.map(authorDTOMapper)
				.orElseThrow(() -> new ApiNotFoundException(String.format("User with id %s not found", id)));
	}

	public AuthorDTO createUser(Author author) {
		authorRepo.findByEmail(author.getEmail())
				.ifPresent(
						(val) -> {
							throw new ApiBadRequestException(
									String.format("User email %s is used, please use another email", val.getEmail())
							);
						}
				);
		return authorDTOMapper.apply(authorRepo.save(author));
	}

	public AuthorDTO updateUser(Author author, long id) {
		Author savedAuthor = authorRepo.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(String.format("User with id %s not found", id)));

		savedAuthor.setEmail(author.getEmail());
		savedAuthor.setFirstName(author.getFirstName());
		savedAuthor.setLastName(author.getLastName());
		savedAuthor.setPassword(author.getPassword());

		return authorDTOMapper.apply(authorRepo.save(author));
	}

	public Map<String, String> deleteUser(long id) {
		Author savedAuthor = authorRepo.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(String.format("User with id %s not found", id)));
		authorRepo.delete(savedAuthor);

		return Map.of("message", String.format("User with id %s deleted", id));
	}
}

package com.personal.website.digitalPortofolio.controller;

import com.personal.website.digitalPortofolio.DTO.AuthorDTO;
import com.personal.website.digitalPortofolio.model.Author;
import com.personal.website.digitalPortofolio.service.AuthorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/user")
public class AuthorController {
	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping("/list")
	public List<AuthorDTO> getList() { return authorService.getUserList(); }

	@GetMapping("/{id}")
	public AuthorDTO getById(@PathVariable long id) {
		return authorService.getUserById(id);
	}

	@GetMapping("/")
	public AuthorDTO getByEmail(@RequestParam String email){
		return authorService.getUserByEmail(email);
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public AuthorDTO create(@RequestBody Author author) {
		return authorService.createUser(author);
	}

	@PutMapping(value = "/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public AuthorDTO update(@PathVariable long id, @RequestBody Author author) {
		return authorService.updateUser(author, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable long id) {
		return ResponseEntity.ok(authorService.deleteUser(id));
	}




}

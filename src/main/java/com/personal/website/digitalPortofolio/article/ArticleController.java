package com.personal.website.digitalPortofolio.article;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/article")
public class ArticleController {
	private final ArticleService articleService;

	@GetMapping("/")
	public ResponseEntity<String> hellow() {
		return ResponseEntity.ok("Hello your API secured");
	}
	@GetMapping("/list")
	public List<ArticleDTO> getArticleList(@RequestParam long authorId) {
		return articleService.getList(authorId);
	}

	@GetMapping("/{id}")
	public ArticleDTO getArticle(@PathVariable long id) {
		return articleService.getArticle(id);
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ArticleDTO createArticle(@RequestParam Long authorId,
	                                @RequestBody Article article) {
		return articleService.createArticle(article, authorId);
	}

	@PostMapping(value = "/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ArticleDTO updateArticle(@PathVariable long id,
	                                @RequestBody Article article) {
		return articleService.updateArticle(id, article);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteArticle(@PathVariable long id) {
		String result = articleService.deleteArticle(id);
		return ResponseEntity.ok(result);
	}
}
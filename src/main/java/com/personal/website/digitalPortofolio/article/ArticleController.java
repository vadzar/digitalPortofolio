package com.personal.website.digitalPortofolio.article;

import com.personal.website.digitalPortofolio.config.ApiAuthRequired;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/article")
public class ArticleController extends ApiAuthRequired {
	private final ArticleService articleService;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/")
	public List<ArticleDTO> getArticleList() {
		return articleService.getList(getCurrentUser().getId());
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/{id}")
	public ArticleDTO getArticle(@PathVariable long id) {
		return articleService.getArticle(id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ArticleDTO createArticle(@RequestBody Article article) {
		return articleService.createArticle(article, getCurrentUser().getId());
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ArticleDTO updateArticle(@PathVariable long id,
	                                @RequestBody Article article) {
		return articleService.updateArticle(id, article);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteArticle(@PathVariable long id) {
		String result = articleService.deleteArticle(id);
		return ResponseEntity.ok(result);
	}
}

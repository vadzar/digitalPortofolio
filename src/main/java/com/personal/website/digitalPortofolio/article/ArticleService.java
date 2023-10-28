package com.personal.website.digitalPortofolio.article;

import com.personal.website.digitalPortofolio.User.User;
import com.personal.website.digitalPortofolio.User.UserRepository;
import com.personal.website.digitalPortofolio.author.Author;
import com.personal.website.digitalPortofolio.exception.ApiBadRequestException;
import com.personal.website.digitalPortofolio.exception.ApiNotFoundException;
import com.personal.website.digitalPortofolio.author.AuthorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ArticleService {
	private final ArticleRepo articleRepo;
	private final AuthorRepo authorRepo;
	private final UserRepository userRepo;
	private final ArticleDTOMapper articleDTOMapper;

	public List<ArticleDTO> getList(long userId) {
		return articleRepo.findArticlesByUser(userId)
				.stream()
				.map(articleDTOMapper)
				.collect(Collectors.toList());
	}

	public ArticleDTO getArticle(long id) {
		return articleRepo.findById(id)
				.map(articleDTOMapper)
				.orElseThrow(
						() -> new ApiNotFoundException(String.format("Article with id %s not found", id))
				);
	}

	public ArticleDTO createArticle(Article article, Long userId) {
		if(userId == null)
			throw new ApiBadRequestException("AuthorId can't be empty");
		User user =  userRepo.findById(userId)
				.orElseThrow(() -> new ApiNotFoundException(
						String.format("Author with id %s not found", userId)
				));

		if(article == null)
			throw new ApiBadRequestException("Article can't be empty");
		article.setDateTimePosted(LocalDateTime.now());
		article.setLastEdited(LocalDateTime.now());
		article.setUser(user);

		return articleDTOMapper.apply(articleRepo.save(article));
	}

	public ArticleDTO updateArticle(long id, Article article) {
		Article savedArticle = articleRepo.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(String.format("Article with id %s not found", id)));
		savedArticle.setTitle(article.getTitle());
		savedArticle.setContent(article.getContent());
		savedArticle.setLastEdited(LocalDateTime.now());

		return articleDTOMapper.apply(articleRepo.save(savedArticle));
	}

	public String deleteArticle(long id) {
		Article article = articleRepo.findById(id)
				.orElseThrow(
						() -> new ApiNotFoundException(String.format("Article with id %s not found", id))
				);
		article.setUser(null);
		articleRepo.delete(article);

		return String.format("Article with id: %s successfully deleted", id);
	}
}

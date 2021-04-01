package com.nico.store.store.service;

import java.util.List;

import com.nico.store.store.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

	List<Article> findAllArticles();

	Page<Article> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh,
			List<String> categories, List<String> brands, String search);

	List<Article> findFirstArticles();

	Article findArticleById(Long id);

	Article saveArticle(Article article);

	void deleteArticleById(Long id);

	List<String> getAllCategories();

	List<String> getAllBrands();

	List<Article> findAllByKeyword(String keyword);

}

package com.nico.store.store.service.impl;

import com.nico.store.store.domain.Article;
import com.nico.store.store.repository.ArticleRepository;
import com.nico.store.store.repository.ArticleSpecification;
import com.nico.store.store.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

  @Autowired private ArticleRepository articleRepository;

  @Value("${articleservice.featured-items-number}")
  private int featuredArticlesNumber;

  @Override
  public List<Article> findAllArticles() {
    return (List<Article>) articleRepository.findAllEagerBy();
  }

  @Override
  public Page<Article> findArticlesByCriteria(
      Pageable pageable,
      Integer priceLow,
      Integer priceHigh,
      List<String> categories,
      List<String> brands,
      String search) {
    Page<Article> page =
        articleRepository.findAll(
            ArticleSpecification.filterBy(priceLow, priceHigh, categories, brands, search),
            pageable);
    return page;
  }

  @Override
  public List<Article> findFirstArticles() {
    return articleRepository.findAll(PageRequest.of(0, featuredArticlesNumber)).getContent();
  }

  @Override
  public Article findArticleById(Long id) {
    Optional<Article> opt = articleRepository.findById(id);
    return opt.get();
  }

  @Override
  @CacheEvict(
      value = {"sizes", "categories", "brands"},
      allEntries = true)
  public Article saveArticle(Article article) {
    return articleRepository.save(article);
  }

  @Override
  @CacheEvict(
      value = {"sizes", "categories", "brands"},
      allEntries = true)
  public void deleteArticleById(Long id) {
    articleRepository.deleteById(id);
  }

  @Override
  @Cacheable("categories")
  public List<String> getAllCategories() {
    return articleRepository.findAllCategories();
  }

  @Override
  @Cacheable("brands")
  public List<String> getAllBrands() {
    return articleRepository.findAllBrands();
  }

  @Override
  public List<Article> findAllByKeyword(String keyword) {
    return articleRepository.findAllProductsByKeyword(keyword);
  }
}

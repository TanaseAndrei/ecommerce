package com.nico.store.store.controller.rest;

import com.nico.store.store.domain.Article;
import com.nico.store.store.domain.dto.ArticleDisplayDto;
import com.nico.store.store.repository.ArticleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ArticleRestController {

    private final ArticleRepository articleRepository;

    public ArticleRestController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Endpoint used to fetch from the database based on a keyword, the first 10 articles.
     * @return list of articles, under a json format
     */
    @GetMapping(value = "/articlesForAutocomplete", produces = "application/json")
    @ResponseBody
    public List<ArticleDisplayDto> getArticles(@RequestParam(value = "term", required = false, defaultValue = "") String term){
            List<Article> articles = articleRepository
                    .findAllProductsByKeyword(term)
                    .stream()
                    .limit(10)
                    .collect(Collectors.toList());
    return convertToDto(articles);
    }

    private List<ArticleDisplayDto> convertToDto(List<Article> articles) {
        return articles
                .stream()
                .map(article -> {
                    ArticleDisplayDto articleDisplayDto = new ArticleDisplayDto();
                    articleDisplayDto.setArticleId(article.getId());
                    articleDisplayDto.setArticleName(article.getTitle());
                    return articleDisplayDto;
                })
                .collect(Collectors.toList());
    }
}

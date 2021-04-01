package com.nico.store.store.controller.rest;

import com.nico.store.store.domain.Article;
import com.nico.store.store.repository.ArticleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ArticleRestController {

    private final ArticleRepository articleRepository;

    public ArticleRestController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/articlesForAutocomplete", produces = "application/json")
    @ResponseBody
    public List<String> getArticles(){
//        return articleRepository
//                .findAllProductsByKeyword(keyword)
//                .stream()
//                .limit(10)
//                .collect(Collectors.toList());
        return Arrays.asList("article1", "article2", "article3");
    }
}

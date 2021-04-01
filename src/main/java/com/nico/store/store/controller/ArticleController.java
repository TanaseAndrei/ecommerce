package com.nico.store.store.controller;

import com.nico.store.store.domain.Article;
import com.nico.store.store.domain.ArticleBuilder;
import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import com.nico.store.store.service.ArticleService;
import com.nico.store.store.utils.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/add")
    public String addArticle(Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
        model.addAttribute("allBrands", articleService.getAllBrands());
        model.addAttribute("allCategories", articleService.getAllCategories());
        return "addArticle";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addArticlePost(@ModelAttribute("article") Article article, HttpServletRequest request, @RequestParam("file")MultipartFile picture) throws IOException {
        Article newArticle = new ArticleBuilder()
                .withTitle(article.getTitle())
                .stockAvailable(article.getStock())
                .withPrice(article.getPrice())
                .ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
                .ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
                .withPicture(imageService.create(picture))
                .withDiagonal(article.getDiagonal())
                .withNits(article.getNits())
                .withMemoryType(article.getMemoryType())
                .withMemoryCapacity(article.getMemoryCapacity())
                .withNumberOfSlots(article.getNumberOfSlots())
                .withProcessorModel(article.getProcessorModel())
                .withNumberOfCores(article.getNumberOfCores())
                .withProcessorTechnology(article.getProcessorTechnology())
                .withProcessorType(article.getProcessorType())
                .withResolution(article.getResolution())
                .withRefreshRate(article.getRefreshRate())
                .withMaximumMemoryCapacity(article.getMaximumMemoryCapacity())
                .withProcessorProducer(article.getProcessorProducer())
                .build();

        articleService.saveArticle(newArticle);
        return "redirect:article-list";
    }

    @RequestMapping("/article-list")
    public String articleList(Model model) {
        List<Article> articles = articleService.findAllArticles();
        model.addAttribute("articles", articles);
        return "articleList";
    }

    @RequestMapping("/edit")
    public String editArticle(@RequestParam("id") Long id, Model model) {
        Article article = articleService.findArticleById(id);
        StringBuilder preselectedSizes = new StringBuilder();
        StringBuilder preselectedBrands = new StringBuilder();
        for (Brand brand : article.getBrands()) {
            preselectedBrands.append(brand.getName()).append(",");
        }
        StringBuilder preselectedCategories = new StringBuilder();
        for (Category category : article.getCategories()) {
            preselectedCategories.append(category.getName()).append(",");
        }
        model.addAttribute("article", article);
        model.addAttribute("preselectedSizes", preselectedSizes.toString());
        model.addAttribute("preselectedBrands", preselectedBrands.toString());
        model.addAttribute("preselectedCategories", preselectedCategories.toString());
        model.addAttribute("allBrands", articleService.getAllBrands());
        model.addAttribute("allCategories", articleService.getAllCategories());
        return "editArticle";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editArticlePost(@ModelAttribute("article") Article article, HttpServletRequest request) {
        Article newArticle = new ArticleBuilder()
                .withTitle(article.getTitle())
                .stockAvailable(article.getStock())
                .withPrice(article.getPrice())
                .ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
                .ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
                .withDiagonal(article.getDiagonal())
                .withNits(article.getNits())
                .withMemoryType(article.getMemoryType())
                .withMemoryCapacity(article.getMemoryCapacity())
                .withNumberOfSlots(article.getNumberOfSlots())
                .withProcessorModel(article.getProcessorModel())
                .withNumberOfCores(article.getNumberOfCores())
                .withProcessorTechnology(article.getProcessorTechnology())
                .withProcessorType(article.getProcessorType())
                .withResolution(article.getResolution())
                .withRefreshRate(article.getRefreshRate())
                .withMaximumMemoryCapacity(article.getMaximumMemoryCapacity())
                .withProcessorProducer(article.getProcessorProducer())
                .build();
        newArticle.setId(article.getId());
        articleService.saveArticle(newArticle);
        return "redirect:article-list";
    }

    @RequestMapping("/delete")
    public String deleteArticle(@RequestParam("id") Long id) throws IOException {
        Article article = articleService.findArticleById(id);
        imageService.delete(article.getPicture());
        articleService.deleteArticleById(id);
        return "redirect:article-list";
    }

}

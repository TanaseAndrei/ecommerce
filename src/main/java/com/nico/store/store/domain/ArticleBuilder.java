package com.nico.store.store.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleBuilder {

  private String title;
  private int stock;
  private double price;
  private String picture;
  private List<String> categories;
  private List<String> brands;
  private String processorProducer;
  private String processorType;
  private String processorModel;
  private int numberOfCores;
  private String processorTechnology;
  private float diagonal;
  private String resolution;
  private int nits;
  private int refreshRate;
  private String memoryCapacity;
  private String memoryType;
  private int numberOfSlots;
  private int maximumMemoryCapacity;

  public ArticleBuilder() {}

  public ArticleBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public ArticleBuilder stockAvailable(int stock) {
    this.stock = stock;
    return this;
  }

  public ArticleBuilder withPrice(double price) {
    this.price = price;
    return this;
  }

  public ArticleBuilder withPicture(String picture) {
    this.picture = picture;
    return this;
  }

  public ArticleBuilder ofCategories(List<String> categories) {
    this.categories = categories;
    return this;
  }

  public ArticleBuilder ofBrand(List<String> brands) {
    this.brands = brands;
    return this;
  }

  public Article build() {
    Article article = new Article();
    article.setTitle(this.title);
    article.setPrice(this.price);
    article.setStock(this.stock);
    article.setPicture(this.picture);
    article.setProcessorProducer(this.processorProducer);
    article.setDiagonal(this.diagonal);
    article.setMemoryCapacity(this.memoryCapacity);
    article.setNits(this.nits);
    article.setMemoryType(this.memoryType);
    article.setNumberOfCores(this.numberOfCores);
    article.setMaximumMemoryCapacity(this.maximumMemoryCapacity);
    article.setNumberOfSlots(this.numberOfSlots);
    article.setResolution(this.resolution);
    article.setProcessorType(this.processorType);
    article.setRefreshRate(this.refreshRate);
    article.setProcessorModel(this.processorModel);
    article.setProcessorTechnology(this.processorTechnology);

    if (this.categories != null && !this.categories.isEmpty()) {
      Set<Category> catElements = new HashSet<>();
      for (String val : this.categories) {
        catElements.add(new Category(val, article));
      }
      article.setCategories(catElements);
    }
    if (this.brands != null && !this.brands.isEmpty()) {
      Set<Brand> brandlements = new HashSet<>();
      for (String val : this.brands) {
        brandlements.add(new Brand(val, article));
      }
      article.setBrands(brandlements);
    }

    return article;
  }

  public ArticleBuilder withProcessorProducer(String processorProducer) {
    this.processorProducer = processorProducer;
    return this;
  }

  public ArticleBuilder withProcessorType(String processorType) {
    this.processorType = processorType;
    return this;
  }

  public ArticleBuilder withProcessorModel(String processorModel) {
    this.processorModel = processorModel;
    return this;
  }

  public ArticleBuilder withNumberOfCores(int numberOfCores) {
    this.numberOfCores = numberOfCores;
    return this;
  }

  public ArticleBuilder withProcessorTechnology(String processorTechnology) {
    this.processorTechnology = processorTechnology;
    return this;
  }

  public ArticleBuilder withDiagonal(float diagonal) {
    this.diagonal = diagonal;
    return this;
  }

  public ArticleBuilder withResolution(String resolution) {
    this.resolution = resolution;
    return this;
  }

  public ArticleBuilder withNits(int nits) {
    this.nits = nits;
    return this;
  }

  public ArticleBuilder withRefreshRate(int refreshRate) {
    this.refreshRate = refreshRate;
    return this;
  }

  public ArticleBuilder withMemoryCapacity(String memoryCapacity) {
    this.memoryCapacity = memoryCapacity;
    return this;
  }

  public ArticleBuilder withMemoryType(String memoryType) {
    this.memoryType = memoryType;
    return this;
  }

  public ArticleBuilder withNumberOfSlots(int numberOfSlots) {
    this.numberOfSlots = numberOfSlots;
    return this;
  }

  public ArticleBuilder withMaximumMemoryCapacity(int maximumMemoryCapacity) {
    this.maximumMemoryCapacity = maximumMemoryCapacity;
    return this;
  }
}

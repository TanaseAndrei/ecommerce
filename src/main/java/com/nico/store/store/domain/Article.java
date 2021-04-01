package com.nico.store.store.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private int stock;
	private double price;
	private String picture;
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

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Brand> brands;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Category> categories;

	public Article() {}

	public boolean hasStock(int amount) {
		return (this.getStock() > 0) && (amount <= this.getStock());
	}

	public void decreaseStock(int amount) {
		this.stock -= amount;
	}

	public void addCategory(Category category) {
		categories.add(category);
		category.setArticle(this);
	}

	public void removeCategory(Category category) {
		categories.remove(category);
		category.setArticle(null);
	}

	public void addSize(Brand brand) {
		brands.add(brand);
		brand.setArticle(this);
	}

	public void removeSize(Brand brand) {
		brands.remove(brand);
		brand.setArticle(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Set<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getProcessorProducer() {
		return processorProducer;
	}

	public void setProcessorProducer(String processorProducer) {
		this.processorProducer = processorProducer;
	}

	public String getProcessorType() {
		return processorType;
	}

	public void setProcessorType(String processorType) {
		this.processorType = processorType;
	}

	public String getProcessorModel() {
		return processorModel;
	}

	public void setProcessorModel(String processorModel) {
		this.processorModel = processorModel;
	}

	public int getNumberOfCores() {
		return numberOfCores;
	}

	public void setNumberOfCores(int numberOfCores) {
		this.numberOfCores = numberOfCores;
	}

	public String getProcessorTechnology() {
		return processorTechnology;
	}

	public void setProcessorTechnology(String processorTechnology) {
		this.processorTechnology = processorTechnology;
	}

	public float getDiagonal() {
		return diagonal;
	}

	public void setDiagonal(float diagonal) {
		this.diagonal = diagonal;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getNits() {
		return nits;
	}

	public void setNits(int nits) {
		this.nits = nits;
	}

	public int getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(int refreshRate) {
		this.refreshRate = refreshRate;
	}

	public String getMemoryCapacity() {
		return memoryCapacity;
	}

	public void setMemoryCapacity(String memoryCapacity) {
		this.memoryCapacity = memoryCapacity;
	}

	public String getMemoryType() {
		return memoryType;
	}

	public void setMemoryType(String memoryType) {
		this.memoryType = memoryType;
	}

	public int getNumberOfSlots() {
		return numberOfSlots;
	}

	public void setNumberOfSlots(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
	}

	public int getMaximumMemoryCapacity() {
		return maximumMemoryCapacity;
	}

	public void setMaximumMemoryCapacity(int maximumMemoryCapacity) {
		this.maximumMemoryCapacity = maximumMemoryCapacity;
	}
}

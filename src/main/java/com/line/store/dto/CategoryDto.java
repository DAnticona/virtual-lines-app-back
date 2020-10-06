package com.line.store.dto;

import java.util.List;

public class CategoryDto {
	
	private Integer categoryId;
	private String name;
	private List<SubcategoryDto> subcategories;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SubcategoryDto> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<SubcategoryDto> subcategories) {
		this.subcategories = subcategories;
	}
	
	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", name=" + name + "]";
	}

}

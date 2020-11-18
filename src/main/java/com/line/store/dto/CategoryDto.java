package com.line.store.dto;

public class CategoryDto {
	
	private String categoryId;
	private String name;
	private String activeFg;
//	private List<SubcategoryDto> subcategories;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public List<SubcategoryDto> getSubcategories() {
//		return subcategories;
//	}
//	public void setSubcategories(List<SubcategoryDto> subcategories) {
//		this.subcategories = subcategories;
//	}
	public String getActiveFg() {
		return activeFg;
	}
	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}
	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", name=" + name + ", activeFg=" + activeFg + "]";
	}
}

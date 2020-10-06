package com.line.store.dto;

public class SubcategoryDto {
	
	private Integer categoryId;
	private Integer subcategoryId;
	private String name;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(Integer subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "SubcategoryDto [categoryId=" + categoryId + ", subcategoryId=" + subcategoryId + ", name=" + name + "]";
	}
}

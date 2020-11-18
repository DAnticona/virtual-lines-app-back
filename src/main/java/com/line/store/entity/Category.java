package com.line.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "category")
public class Category implements Serializable {
	
	private static final long serialVersionUID = -7120877210963971100L;

	@Id
	@Column(name = "category_id")
	private String categoryId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "active_fg", nullable = false)
	private String activeFg;
	
	@OneToMany(mappedBy = "category")
	@JsonManagedReference
	private List<Subcategory> subcategories;
	
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
	public List<Subcategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	public String getActiveFg() {
		return activeFg;
	}
	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", activeFg=" + activeFg + "]";
	}
}

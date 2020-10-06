package com.line.store.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.line.store.entity.id.SubcategoryId;

@Entity
@Table(name = "subcategory")
public class Subcategory {
	
	@EmbeddedId
	private SubcategoryId subcategoryId;
	
	@MapsId("categoryId")
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	@JsonBackReference
	private Category category;
	
	@Column(name = "name")
	private String name;

	public SubcategoryId getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(SubcategoryId subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Subcategory [subcategoryId=" + subcategoryId + ", category=" + category + ", name=" + name + "]";
	}

}

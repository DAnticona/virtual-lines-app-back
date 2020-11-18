package com.line.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "subcategory")
public class Subcategory implements Serializable {
	
	private static final long serialVersionUID = -2701048908553961154L;

	@Id
	@Column(name = "subcategory_id")
	private String subcategoryId;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	@JsonBackReference
	private Category category;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "active_fg", nullable = false)
	private String activeFg;

	public String getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(String subcategoryId) {
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

	public String getActiveFg() {
		return activeFg;
	}

	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}

	@Override
	public String toString() {
		return "Subcategory [subcategoryId=" + subcategoryId + ", category=" + category + ", name=" + name
				+ ", activeFg=" + activeFg + "]";
	}
}

package com.line.store.entity.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubcategoryId {
	
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "subcategory_id")
	private Integer subcategoryId;

	public SubcategoryId() {
	}
	public SubcategoryId(Integer categoryId, Integer subcategoryId) {
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
	}
	
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((subcategoryId == null) ? 0 : subcategoryId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubcategoryId other = (SubcategoryId) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (subcategoryId == null) {
			if (other.subcategoryId != null)
				return false;
		} else if (!subcategoryId.equals(other.subcategoryId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SubcategoryId [categoryId=" + categoryId + ", subcategoryId=" + subcategoryId + "]";
	}
}

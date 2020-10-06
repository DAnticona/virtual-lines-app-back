package com.line.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store {
	
	@Id
	@Column(name = "store_id")
	private String storeId;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "category_id", referencedColumnName = "category_id"),
		@JoinColumn(name = "subcategory_id", referencedColumnName = "subcategory_id")
	})
	private Subcategory subcategory;
	
	@Column(name = "active_fg")
	private String activeFg;
	
	@Column(name = "public_name")
	private String publicName;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "phone")
	private String phone;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public String getActiveFg() {
		return activeFg;
	}

	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}

	public String getPublicName() {
		return publicName;
	}

	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", subcategory=" + subcategory + ", activeFg=" + activeFg + ", publicName="
				+ publicName + ", latitude=" + latitude + ", longitude=" + longitude + ", image=" + image
				+ ", description=" + description + ", website=" + website + ", phone=" + phone + "]";
	}

}

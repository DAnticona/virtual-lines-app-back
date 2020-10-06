package com.line.store.dto;

public class StoreDto {
	
	private String storeId;
	private SubcategoryDto subcategory;
	private String activeFg;
	private String publicName;
	private Double latitude;
	private Double longitude;
	private String image;
	private String description;
	private String website;
	private String phone;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public SubcategoryDto getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(SubcategoryDto subcategory) {
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
		return "StoreDto [storeId=" + storeId + ", subcategory=" + subcategory + ", activeFg=" + activeFg
				+ ", publicName=" + publicName + ", latitude=" + latitude + ", longitude=" + longitude + ", image="
				+ image + ", description=" + description + ", website=" + website + ", phone=" + phone + "]";
	}
}

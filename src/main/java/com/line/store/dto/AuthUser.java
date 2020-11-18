package com.line.store.dto;

public class AuthUser {
	
	private String userId;
	private StoreDto store;
	private RoleDto role;
	private String storeFg;
	private String name;
	private String email;
	private String activeFg;
	private String image;
	private String token;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public StoreDto getStore() {
		return store;
	}
	public void setStore(StoreDto store) {
		this.store = store;
	}
	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
	public String getStoreFg() {
		return storeFg;
	}
	public void setStoreFg(String storeFg) {
		this.storeFg = storeFg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActiveFg() {
		return activeFg;
	}
	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "AuthUser [userId=" + userId + ", store=" + store + ", role=" + role + ", storeFg=" + storeFg + ", name="
				+ name + ", email=" + email + ", activeFg=" + activeFg + ", image=" + image + ", token=" + token + "]";
	}
}

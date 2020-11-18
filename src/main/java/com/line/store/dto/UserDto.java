package com.line.store.dto;

public class UserDto {
	
	private String userId;
	private StoreDto store;
	private RoleDto role;
	private String storeFg;
	private String name;
	private String email;
	private String password;
	private String activeFg;
	private String image;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getActiveFg() {
		return activeFg;
	}
	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", store=" + store + ", role=" + role + ", storeFg=" + storeFg + ", name="
				+ name + ", email=" + email + ", password=" + password + ", activeFg=" + activeFg + ", image=" + image
				+ "]";
	}
}

package com.line.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -5654942465144035794L;

	@Id
	@Column(name = "user_id")
	private String userId;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "store_id", referencedColumnName = "store_id")
	private Store store;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Role role;
	
	@Column(name = "store_fg", nullable = false)
	private String storeFg;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "active_fg", nullable = false)
	private String activeFg;
	
	@Column(name = "image")
	private String image;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
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
		return "User [userId=" + userId + ", store=" + store + ", role=" + role + ", storeFg=" + storeFg + ", name="
				+ name + ", email=" + email + ", password=" + password + ", activeFg=" + activeFg + "image=" + image + "]";
	}

}

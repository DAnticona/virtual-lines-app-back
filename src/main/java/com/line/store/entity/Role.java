package com.line.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {
	
	private static final long serialVersionUID = -6584197209578414113L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "client_fg")
	private String clientFg;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientFg() {
		return clientFg;
	}

	public void setClientFg(String clientFg) {
		this.clientFg = clientFg;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", clientFg=" + clientFg + "]";
	}

}

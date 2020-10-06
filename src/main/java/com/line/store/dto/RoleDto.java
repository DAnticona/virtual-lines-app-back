package com.line.store.dto;

public class RoleDto {

	private Integer roleId;
	private String name;
	
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
	
	@Override
	public String toString() {
		return "RoleDto [roleId=" + roleId + ", name=" + name + "]";
	}
}

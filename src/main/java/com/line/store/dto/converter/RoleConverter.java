package com.line.store.dto.converter;

import org.springframework.stereotype.Component;

import com.line.store.dto.RoleDto;
import com.line.store.entity.Role;

@Component
public class RoleConverter extends AbstractConverter<Role, RoleDto>{

	@Override
	public Role fromDto(RoleDto dto) {
		Role role = new Role();
		
		role.setName(dto.getName());
		role.setRoleId(dto.getRoleId());
		
		return role;
	}

	@Override
	public RoleDto fromEntity(Role entity) {
		RoleDto role = new RoleDto();
		
		role.setName(entity.getName());
		role.setRoleId(entity.getRoleId());
		
		return role;
	}

}

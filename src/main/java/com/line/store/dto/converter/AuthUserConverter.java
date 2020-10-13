package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.AuthUser;
import com.line.store.entity.User;

@Component
public class AuthUserConverter extends AbstractConverter<User, AuthUser> {

	@Autowired
	StoreConverter storeConverter;
	@Autowired
	RoleConverter roleConverter;
	
	@Override
	public User fromDto(AuthUser dto) {
		User user = new User();
		
		user.setUserId(dto.getUserId());
		user.setEmail(dto.getEmail());
		user.setActiveFg(dto.getActiveFg());
		user.setName(dto.getName());
		user.setStoreFg(dto.getStoreFg());
		
		if(dto.getRole() != null) {
			user.setRole(roleConverter.fromDto(dto.getRole()));
		}
		
		if(dto.getStore() != null) {
			user.setStore(storeConverter.fromDto(dto.getStore()));
		}
		
		return user;
	}

	@Override
	public AuthUser fromEntity(User entity) {
		AuthUser user = new AuthUser();
		
		user.setUserId(entity.getUserId());
		user.setEmail(entity.getEmail());
		user.setActiveFg(entity.getActiveFg());
		user.setName(entity.getName());
		user.setStoreFg(entity.getStoreFg());
		
		if(entity.getRole() != null) {
			user.setRole(roleConverter.fromEntity(entity.getRole()));
		}
		
		if(entity.getStore() != null) {
			user.setStore(storeConverter.fromEntity(entity.getStore()));
		}
		
		return user;
	}

}

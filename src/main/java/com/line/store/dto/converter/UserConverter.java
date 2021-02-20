package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.line.store.dto.UserDto;
import com.line.store.entity.User;
import com.line.store.util.Utils;

@Component
public class UserConverter extends AbstractConverter<User, UserDto>{
	
	@Value("${avatar.url}")
	private String avatarUrl;

	@Autowired
	StoreConverter storeConverter;
	@Autowired
	RoleConverter roleConverter;
	@Autowired
	SlotConverter slotConverter;
	@Autowired
	LineConverter lineConverter;
	@Autowired
	Utils UTILS;
	
	@Override
	public User fromDto(UserDto dto) {
		User user = new User();
		
		user.setUserId(dto.getUserId());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setActiveFg(dto.getActiveFg());
		user.setName(dto.getName());
		user.setStoreFg(dto.getStoreFg());
		user.setImage(dto.getImage());
		user.setSubscriptorId(dto.getSubscriptorId());
		
		if(dto.getRole() != null) {
			user.setRole(roleConverter.fromDto(dto.getRole()));
		}
		
		if(dto.getStore() != null) {
			user.setStore(storeConverter.fromDto(dto.getStore()));
		}
		
		return user;
	}

	@Override
	public UserDto fromEntity(User entity) {
		UserDto user = new UserDto();
		
		user.setUserId(entity.getUserId());
		user.setEmail(entity.getEmail());
		user.setActiveFg(entity.getActiveFg());
		user.setName(entity.getName());
		user.setStoreFg(entity.getStoreFg());
		user.setSubscriptorId(entity.getSubscriptorId());
		
		if(entity.getImage() != null) {
			user.setImage(avatarUrl + entity.getImage());
		} else {
			user.setImage(entity.getImage());
		}
		
		if(entity.getRole() != null) {
			user.setRole(roleConverter.fromEntity(entity.getRole()));
		}
		
		if(entity.getStore() != null) {
			user.setStore(storeConverter.fromEntity(entity.getStore()));
		}
		
		return user;
	}

}

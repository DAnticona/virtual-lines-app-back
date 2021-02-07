package com.line.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.line.store.constant.ApiState;
import com.line.store.dao.RoleDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.RoleDto;
import com.line.store.dto.converter.RoleConverter;

@Service
public class RoleService {
	
	@Autowired
	RoleDao roleDao;
	@Autowired
	RoleConverter roleConverter;
	
	public ApiResponse findAll() {
		
		List<RoleDto> roles = roleConverter.fromEntity(roleDao.findAll());
		
		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), roles, roles.size());
		
	}
	
	public ApiResponse findAllStoreRoles() {
		
		List<RoleDto> roles = roleConverter.fromEntity(roleDao.findByClientFg("N"));
		
		roles = roles.stream().filter(x -> x.getRoleId() != 4).collect(Collectors.toList());
		
		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), roles, roles.size());
		
	}

}

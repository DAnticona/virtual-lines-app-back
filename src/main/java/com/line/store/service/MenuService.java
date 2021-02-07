package com.line.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.line.store.constant.ApiState;
import com.line.store.dao.MenuDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.MenuDto;
import com.line.store.dto.converter.MenuConverter;

@Service
public class MenuService {
	
	@Autowired
	MenuDao menuDao;
	@Autowired
	MenuConverter menuConverter;
	
	public ApiResponse findAll() {
		List<MenuDto> menus = menuConverter.fromEntity((menuDao.findAll()));
		
		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), menus, null);
	}

}

package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.MenuDto;
import com.line.store.entity.Menu;

@Component
public class MenuConverter extends AbstractConverter<Menu, MenuDto> {

	@Autowired
	SubmenuConverter submenuConverter;

	@Override
	public Menu fromDto(MenuDto dto) {
		Menu menu = new Menu();
		menu.setMenuId(dto.getMenuId());
		menu.setName(dto.getName());
		menu.setIcon(dto.getIcon());
		menu.setOrderNu(dto.getOrderNu());

		if (dto.getSubmenus() != null) {
			menu.setSubmenus(submenuConverter.fromDto(dto.getSubmenus()));
		}

		return menu;
	}

	@Override
	public MenuDto fromEntity(Menu entity) {
		MenuDto menu = new MenuDto();
		menu.setMenuId(entity.getMenuId());
		menu.setName(entity.getName());
		menu.setOrderNu(entity.getOrderNu());
		menu.setIcon(entity.getIcon());

		if (entity.getSubmenus() != null) {
			menu.setSubmenus(submenuConverter.fromEntity(entity.getSubmenus()));
		}

		return menu;
	}

}

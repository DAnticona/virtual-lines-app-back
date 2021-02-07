package com.line.store.dto.converter;

import org.springframework.stereotype.Component;

import com.line.store.dto.SubmenuDto;
import com.line.store.entity.Submenu;

@Component
public class SubmenuConverter extends AbstractConverter<Submenu, SubmenuDto
> {

	@Override
	public Submenu fromDto(SubmenuDto dto) {
		Submenu submenu = new Submenu();
		submenu.setSubmenuId(dto.getSubmenuId());
		submenu.setName(dto.getName());
		submenu.setOrderNu(dto.getOrderNu());
		submenu.setPath(dto.getPath());

		return submenu;
	}

	@Override
	public SubmenuDto fromEntity(Submenu entity) {
		SubmenuDto submenu = new SubmenuDto();
		submenu.setMenuId(entity.getMenu().getMenuId());
		submenu.setSubmenuId(entity.getSubmenuId());
		submenu.setName(entity.getName());
		submenu.setOrderNu(entity.getOrderNu());
		submenu.setPath(entity.getPath());

		return submenu;
	}

}

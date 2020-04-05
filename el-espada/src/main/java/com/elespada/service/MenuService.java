package com.elespada.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elespada.model.Menu;

@Component
public interface MenuService {
	public List<Menu> getMenuListbyIds(String menuIds);

	List<Long> convertStringToList(String menuIds);

	List<Menu> getMenuListByLongIds(List<Long> idList);
}

package com.elespada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elespada.model.Menu;
import com.elespada.repo.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	MenuRepository menuRepository;

	@Override
	public List<Menu> getMenuListbyIds(String menuIds) throws Exception {
		logger.debug("getMenuListbyIds");
		List<Long> idList = convertStringToList(menuIds);
		return getMenuListByLongIds(idList);
	}

	@Override
	public List<Long> convertStringToList(String menuIds) throws Exception {
		logger.debug("convertStringToList");
		return Stream.of(menuIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<Menu> getMenuListByLongIds(List<Long> idList) throws Exception {
		logger.debug("getMenuListByLongIds start");
		Iterable<Menu> itrMenuList = menuRepository.findAllById(idList);
		List<Menu> menuList = new ArrayList<Menu>();
		(itrMenuList).forEach(menuList::add);
		logger.debug("getMenuListByLongIds end");
		return menuList;
	}

}

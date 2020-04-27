/*
 * Copyright [2020] [ElEspada - UIS Avengers-Force - Software Engineering Capstone - Springfield, IL]
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.elespada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elespada.model.Menu;
import com.elespada.repo.MenuRepository;

/**
 * <b>MenuServiceImpl.java implements <b>MenuService</b>
 * interface</b><blockquote>Service Class that interacts with Model in MVC
 * pattern
 * <p>
 * This class handles all transaction that are necessary for interacting with
 * MenuRepository. It also has some conversion utilities. As Menu is a table
 * where menu items are stored, most of the methos here does only retrieval of
 * data. All the methods that interact with DB are transactional
 */
@Service
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	MenuRepository menuRepository; // used for CRUD operations on MENU table

	@Override
	public List<Menu> getMenuListbyIds(String menuIds) throws Exception {
		logger.debug("getMenuListbyIds");

		// convert the comma separated string to LongList for retrieving from DB
		List<Long> idList = convertStringToList(menuIds);

		// return Menu items requested
		return getMenuListByLongIds(idList);
	}

	/**
	 * Utility method to convert comma separated id's to List<Long>
	 *
	 * @param String
	 * @return List<Long>
	 */
	@Override
	public List<Long> convertStringToList(String menuIds) throws Exception {
		logger.debug("convertStringToList");
		return Stream.of(menuIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
	}

	/**
	 * To retrieve Menu list by list of menu id's passed as a Long list
	 *
	 * @param List<Long> idList
	 * @return List<Menu>
	 */
	@Override
	@Transactional
	public List<Menu> getMenuListByLongIds(List<Long> idList) throws Exception {
		logger.debug("getMenuListByLongIds start");
		List<Menu> menuList = new ArrayList<Menu>();

		for (Long id : idList) {

			// traverse through the list of long menu Id's and fetch the menu from table
			Optional<Menu> menuItem = menuRepository.findById(id);

			// add the db result item into list
			menuList.add(menuItem.get());
		} // for loop end

		logger.debug("getMenuListByLongIds end");
		return menuList;
	}

	@Override
	@Transactional
	public List<Menu> retrieveMenu() throws Exception {
		logger.debug("retrieveMenu start");
		List<Menu> menuList = new ArrayList<Menu>();

		// fetch all records from table MENU
		Iterable<Menu> menuDbIterable = menuRepository.findAll();

		// convert the Iterable to List
		menuDbIterable.forEach(menuList::add);

		logger.debug("retrieveMenu end");
		return menuList;
	}

	@Override
	@Transactional
	public Menu retrieveMenuByMenuId(Long menuId) throws Exception {
		logger.debug("retrieveMenuByMenuId");

		// fetch single menu record from table MENU
		return menuRepository.findById(menuId).get();
	}

}

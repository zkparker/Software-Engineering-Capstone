/*
 * Copyright [2020] [ElEspada - Software Engineering Capstone - Springfield, IL]
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
import java.util.Iterator;
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
 * Service class for integration with menu table and some utilities
 */
@Service
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	MenuRepository menuRepository;

	/**
	 * To retrieve menu list by menu id's passed a comma separated string
	 * 
	 * @param String
	 * @return List<Menu>
	 */
	@Override
	public List<Menu> getMenuListbyIds(String menuIds) throws Exception {
		logger.debug("getMenuListbyIds");
		List<Long> idList = convertStringToList(menuIds);
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
		for(Long id: idList) {
			Optional<Menu> menuItem = menuRepository.findById(id);
			menuList.add(menuItem.get());
		}
		logger.debug("getMenuListByLongIds end");
		return menuList;
	}

}

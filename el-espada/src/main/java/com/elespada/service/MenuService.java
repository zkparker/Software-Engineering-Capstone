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

import java.util.List;

import org.springframework.stereotype.Component;

import com.elespada.model.Menu;

/**
 * <b>MenuService.java</b><blockquote> Interface for Menu Services
 */
@Component
public interface MenuService {

	/**
	 * To retrieve menu list by menu id's passed a comma separated string. When user
	 * selects add to order, they are saved in a comma separated string, these are
	 * the MENU table primary keys. This method fetches the MENU items from MENU
	 * table
	 *
	 * @param menuIds String comma separated menu id's
	 * @return List<Menu> selected menu list
	 */
	public List<Menu> getMenuListbyIds(String menuIds) throws Exception;

	/**
	 * Helper utility method that converts the comma separated menu id's to List of
	 * type Long
	 * <p>
	 * <code>Input: 21,22,23</code><br>
	 * <code>Output: [21,22,23]</code>
	 *
	 * @param menuIds String comma separated menu id's
	 * @return List<Long>
	 */
	public List<Long> convertStringToList(String menuIds) throws Exception;

	/**
	 * This method makes the DB call through menuRepository to fetch the Menu List
	 * by passing List of Long menu id's.
	 *
	 * @param idList of type List<Long>
	 * @return List<Menu>
	 * @throws Exception
	 */
	public List<Menu> getMenuListByLongIds(List<Long> idList) throws Exception;

	/**
	 * This method makes a DB call through menuRepository to fetch all records in
	 * the MENU table.
	 * <p>
	 * <code>SELECT * FROM MENU</code>
	 *
	 * @return List<Menu>
	 * @throws Exception
	 */
	public List<Menu> retrieveMenu() throws Exception;

	/**
	 * This method makes a DB call through menuRepository to fetch one Menu item by
	 * primary key menuId
	 * <p>
	 * <code>SELECT * FROM MENU WHERE MENUID=?</code>
	 *
	 * @param menuId of type Long - this is the primary key
	 * @return Menu
	 * @throws Exception
	 */
	public Menu retrieveMenuByMenuId(Long menuId) throws Exception;
}

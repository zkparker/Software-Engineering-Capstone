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
package com.elespada.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <b>CommonController.java</b> <blockquote>Controller in MVC pattern
 * <p>
 * This is a common web controller for handling the actions on ElEspada website
 * menu. This controller class handles requests for About Us, Contact Us, Hours
 * of Operation, Full Menu. These are mostly static pages and not relevant to
 * the actual functionality of placing orders
 */
@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	/**
	 * This method handles requests coming to the application’s context path
	 * (/about) by redirecting to the view named about.html. Displays About Us page
	 * TODO Add requirements for all menu items
	 *
	 * @return view to display about.html
	 */
	@RequestMapping("/about")
	public String getAbout() {
		logger.debug("About Us");
		return "about";
	}

	/**
	 * This method handles requests coming to the application’s context path
	 * (/contact) by redirecting to the view named contact.html. Displays Contact Us
	 * page
	 *
	 * @return view to display contact.html
	 */
	@RequestMapping("/contact")
	public String getContact() {
		logger.debug("Contact Us");
		return "contact";
	}

	/**
	 * This method handles requests coming to the application’s context path
	 * (/hours) by redirecting to the view named hours.html. Displays Hours page
	 *
	 * @return view to display hours.html
	 */
	@RequestMapping("/hours")
	public String getHours() {
		logger.debug("Hours of Operation");
		return "hours";
	}

	/**
	 * This method handles requests coming to the application’s context path (/menu)
	 * by redirecting to the view named menu.html. Displays Full menu page
	 *
	 * @return view to display menu.html
	 */
	@RequestMapping("/menu")
	public String getMenu() {
		logger.debug("Full Menu");
		return "menu";
	}

}

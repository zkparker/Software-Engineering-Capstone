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
package com.elespada.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elespada.VO.PaymentVO;

@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	/**
	 * Displays About Us page
	 * @return view
	 */
	@RequestMapping("/about")
	public String getAbout() {
		logger.debug("About Us");
		return "about";
	}
	
	/**
	 * Displays Contact Us page
	 * @return view
	 */
	@RequestMapping("/contact")
	public String getContact() {
		logger.debug("Contact Us");
		return "contact";
	}
	
	/**
	 * Displays Hours page
	 * @return view
	 */
	@RequestMapping("/hours")
	public String getHours() {
		logger.debug("Hours of Operation");
		return "hours";
	}
	
	/**
	 * Displays Full menu page
	 * @return view
	 */
	@RequestMapping("menu")
	public String getMenu() {
		logger.debug("Full Menu");
		return "menu";
	}
	
	/**
	 * Displays Payment page
	 * @return view
	 */
	@RequestMapping("payment")
	public String getPayment(Model model) {
		logger.debug("Payment Details");
		model.addAttribute("paymentVO", new PaymentVO());
		return "payment";
	}
}

package com.elespada.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping("menu")
	public String getMenu() {
		logger.debug("Full Menu");
		return "/menu";
	}

}

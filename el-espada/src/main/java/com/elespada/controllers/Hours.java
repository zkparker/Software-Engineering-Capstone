package com.elespada.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hours {

	private static final Logger logger = LoggerFactory.getLogger(Hours.class);

	@RequestMapping("/hours")
	public String getHours() {
		logger.debug("Hours of Operation");
		return "/hours";
	}
}

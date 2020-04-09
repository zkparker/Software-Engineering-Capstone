package com.elespada.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinalController {

	private static final Logger logger = LoggerFactory.getLogger(FinalController.class);

	@RequestMapping("/final")
	public String getFinalPage() {
		logger.debug("Final");
		return "final";
	}
}

package com.elespada.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Contact {

	private static final Logger logger = LoggerFactory.getLogger(Contact.class);

	@RequestMapping("/contact")
	public String getContact() {
		logger.debug("Contact Us");
		return "/contact";
	}
}

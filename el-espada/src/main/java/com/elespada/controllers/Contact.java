package com.elespada.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Contact {
	
	@RequestMapping("/contact")
	public String getContact() {
		
			return "contact";
		}
	}

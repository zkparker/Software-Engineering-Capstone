package com.elespada.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutUsController {
	
	@RequestMapping("/about")
	public String getAbout() {
		
			return "about";
		}
	}

package com.elespada.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewOrder {
	
	@RequestMapping("reviewOrder")
	public String getReviewOrder() {
		return "reviewOrder";
	}

}

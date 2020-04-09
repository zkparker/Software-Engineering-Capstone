package com.elespada.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@RequestMapping("payment")
	public String getPayment() {
		logger.debug("Payment Details");
		return "payment";
	}

}

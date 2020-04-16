package com.elespada.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elespada.VO.EspadaVO;
import com.elespada.VO.PaymentVO;

@Controller
public class PaymentController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@RequestMapping("payment")
	public String getPayment(Model model) {
		logger.debug("Payment Details");
		model.addAttribute("paymentVO", new PaymentVO());
		return "/payment";
	}

}

package com.elespada.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elespada.VO.PaymentVO;

@Controller
public class FinalController {

	private static final Logger logger = LoggerFactory.getLogger(FinalController.class);

	@RequestMapping(value="/final", method = RequestMethod.POST)
	public String getFinalPage(@ModelAttribute("paymentVO") PaymentVO paymentVO, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		logger.debug("Final");
		logger.error(paymentVO.toString());
		return "final";
	}
}

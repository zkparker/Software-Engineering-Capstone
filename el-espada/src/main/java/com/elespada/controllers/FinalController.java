package com.elespada.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elespada.VO.PaymentVO;
import com.elespada.service.MenuService;
import com.elespada.service.OrderService;

@Controller
public class FinalController {

	@Autowired
	OrderService orderService;
	
	private static final Logger logger = LoggerFactory.getLogger(FinalController.class);

	@RequestMapping(value="/final", method = RequestMethod.POST)
	public String getFinalPage(@ModelAttribute("paymentVO") PaymentVO paymentVO, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		logger.debug("Final - Processing Payment start");
		try {
		HttpSession session = req.getSession();
		Long orderId = (Long) session.getAttribute("orderId");
		logger.debug("Order id:"+orderId);
		orderService.updatePaymentDetails(orderId, paymentVO);
		logger.debug("Final - Processing Payment end");
		}catch(Exception e) {
			logger.error("Exception during processing payment:" + e);
		}
		return "/final";
	}
}

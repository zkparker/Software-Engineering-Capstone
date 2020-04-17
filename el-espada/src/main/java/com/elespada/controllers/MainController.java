/*
 * Copyright [2020] [ElEspada - Software Engineering Capstone - Springfield, IL]
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.elespada.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elespada.VO.EspadaVO;
import com.elespada.VO.PaymentVO;
import com.elespada.model.Menu;
import com.elespada.repo.MenuRepository;
import com.elespada.service.MenuService;
import com.elespada.service.OrderService;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	MenuService menuService;

	@Autowired
	OrderService orderService;

	/**
	 * Controller method for start ordering
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @return String view
	 */
	@RequestMapping(value = {"/index", "", "/"})
	public String getIndex(Model model, HttpServletRequest req, HttpServletResponse res) {
		logger.debug("Menu Display Start");
		HttpSession session = req.getSession();
		session.removeAttribute("orderId");
		session.removeAttribute("orderTotal");
		model.addAttribute("menu", menuRepository.findAll());
		model.addAttribute("espadaVO", new EspadaVO());
		logger.debug("Menu Display End");
		return "index";
	}

	/**
	 * Controller method for Order Review, computes orderTotal, generates orderId
	 * 
	 * @param espadaVO
	 * @param model
	 * @param req
	 * @param res
	 * @return String view
	 */
	@RequestMapping(value = "/reviewOrder", method = RequestMethod.POST)
	public String reviewOrder(@ModelAttribute("espadaVO") EspadaVO espadaVO, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		logger.debug("Review Order Start");
		Long orderId = null;
		HttpSession session = req.getSession();
		try {
			logger.debug("Order Start:" + orderId);
			orderId = orderService.createOrder().getOrderId();
			session.setAttribute("orderId", orderId);
			orderService.createOrderDetails(espadaVO.getMenuIds(), orderId);
			Float orderTotal = orderService.computeOrderTotal(orderService.findOrderById(orderId));
			logger.debug("orderTotal::"+orderTotal);
			session.setAttribute("orderTotal", orderTotal);
			List<Menu> menuList = menuService.getMenuListbyIds(espadaVO.getMenuIds());
			logger.debug("Menu items in order:" + menuList);
			model.addAttribute("menu", menuList);
			logger.debug("Review Order End");
			return "reviewOrder";
		} catch (Exception e) {
			logger.error("Exception during Review Order:" + e);
			orderService.deleteOrder(orderId);
			session.removeAttribute("orderId");
			session.removeAttribute("orderTotal");
			model.addAttribute("espadaVO", new EspadaVO());
			model.addAttribute("menu", menuRepository.findAll());
			return "index";
		}
	}

	/**
	 * Controller method to delete an item from existing order 
	 * 
	 * @param menuId
	 * @param model
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/deleteItem/{menuId}")
	public String deleteItem(@PathVariable("menuId") Long menuId, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		logger.debug("Delete Item Start");
		HttpSession session = req.getSession();
		Long orderId = (Long) session.getAttribute("orderId");
		try {
			logger.debug("Deleting menu from order with id:"+orderId+", item:"+menuRepository.findById(menuId));
			List<Long> menuIds = orderService.deleteItemfromOrder(orderId, menuId);
			List<Menu> remainingItems = menuService.getMenuListByLongIds(menuIds);
			if(!remainingItems.isEmpty()) {
				Float orderTotal = orderService.computeOrderTotal(orderService.findOrderById(orderId));
				logger.debug("orderTotal::"+orderTotal);
				session.setAttribute("orderTotal", orderTotal);
				model.addAttribute("menu", remainingItems);
				logger.debug("Review Order End");
				return "reviewOrder";
			} else {
				logger.debug("No items remaining in order, deleting order and returning to Menu");
				orderService.deleteOrder(orderId);
				session.removeAttribute("orderId");
				session.removeAttribute("orderTotal");
				model.addAttribute("espadaVO", new EspadaVO());
				model.addAttribute("menu", menuRepository.findAll());
				return "index";
			}
		} catch(Exception e) {
			logger.error("Exception during Delete Item:" + e);
			orderService.deleteOrder(orderId);
			session.removeAttribute("orderId");
			session.removeAttribute("orderTotal");
			model.addAttribute("espadaVO", new EspadaVO());
			model.addAttribute("menu", menuRepository.findAll());
			return "index";
		}
	}
	
	/**
	 * Controller method to process payment and finalize the order
	 * 
	 * @param paymentVO
	 * @param model
	 * @param req
	 * @param res
	 * @return
	 */
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
			return "final";
		}catch(Exception e) {
			logger.error("Exception during processing payment:" + e);
			model.addAttribute("espadaVO", new EspadaVO());
			model.addAttribute("menu", menuRepository.findAll());
			return "index";
		}
	}

}

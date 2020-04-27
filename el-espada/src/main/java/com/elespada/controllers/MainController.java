/*
 * Copyright [2020] [ElEspada - UIS Avengers-Force - Software Engineering Capstone - Springfield, IL]
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
import com.elespada.service.MenuService;
import com.elespada.service.OrderService;

/**
 * <b>MainController.java</b><blockquote>Controller in MVC pattern
 * <p>
 * This class handles all the requests that are related with online ordering:
 * <br>
 * 1. Adding items to order <br>
 * 2. Deleting items from order <br>
 * 3. Start ordering button from other pages like about us, contact us, hours of
 * operation, full menu <br>
 * 4. Start over from order review page <br>
 * 5. Checkout and <br>
 * 6. Final order summary page
 */
@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	MenuService menuService; // used for MENU specific services

	@Autowired
	OrderService orderService; // used for ORDERS specific services

	/**
	 * This method handles requests coming to the application’s context path (/index
	 * or / or nothing) by redirecting to the view named index.html. /index or / or
	 * nothing, (example http://localhost:8080/index, http://localhost:8080/,
	 * http://localhost:8080).
	 * <p>
	 * This method will load all the menu items from database table MENU on the
	 * starting page so that users can start adding items to order.
	 *
	 *
	 * @param model used for setting data from table to be used on view index.html
	 * @param req   HttpServletRequest for handling http requests
	 * @param res   HttpServletResponse for handling http responses
	 * @return String view to display index.html
	 * @throws Exception
	 */
	@RequestMapping(value = { "/index", "", "/" })
	public String getMenu(Model model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.debug("Menu Display Start");
		// Get the Session object for the HttpServletRequest object to maintain session
		// attributes
		HttpSession session = req.getSession();

		// retrieve the orderId from session
		Long orderId = (Long) session.getAttribute("orderId");

		return navigateToHomePage(model, orderId, session);
	}

	/**
	 * This method handles requests coming to the application’s context path
	 * (/reviewOrder) by redirecting to the view named reviewOrder.html. Navigates
	 * the user to "Review Order" page once they click on "Continue to Place Order".
	 * Displays the items added to order, generates orderId and computes orderTotal
	 *
	 * @param espadaVO captures this object from the index.html page
	 * @param model    used for setting the items added to order
	 * @param req      HttpServletRequest for handling http requests
	 * @param res      HttpServletResponse for handling http responses
	 * @return String view to display reviewOrder.html or index.html
	 * @throws 63
	 */
	@RequestMapping(value = "/reviewOrder", method = RequestMethod.POST)
	public String reviewOrder(@ModelAttribute("espadaVO") EspadaVO espadaVO, Model model, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		logger.debug("Review Order Start");
		Long orderId = 0L; // create a Long object to hold the current order id
		// Get the Session object for the HttpServletRequest object to maintain session
		// attributes
		HttpSession session = req.getSession();
		try {
			// create a new order in the ORDERS table
			orderId = orderService.createOrder().getOrderId();
			logger.debug("Order Start:" + orderId);

			// set the orderId in session, to use for the life of the order
			session.setAttribute("orderId", orderId);

			// stores all the items added to order into ORDER_DETAILS table
			orderService.createOrderDetails(espadaVO.getMenuIds(), orderId);

			// aggregate the menu item prices and store in in orderTotal
			Float orderTotal = orderService.computeOrderTotal(orderService.findOrderById(orderId));
			logger.debug("orderTotal::" + orderTotal);

			// set the orderTotal in session, to use for the life of the order
			session.setAttribute("orderTotal", orderTotal);

			// load only the items that are added in order, so that they can be displayed to
			// user
			List<Menu> menuList = menuService.getMenuListbyIds(espadaVO.getMenuIds());
			logger.debug("Menu items in order:" + menuList);

			// set the items n order to model attribute menu
			model.addAttribute("menu", menuList);
			logger.debug("Review Order End");

			// navigate the user to reviewOrder.html page
			return "reviewOrder";
		} catch (Exception e) {
			// if any exceptions happen during reviewing order, navigate the use to the main
			// page
			logger.error("Exception during Review Order:" + e);

			// delete any stale data from DB
						if ((orderId != null) && (orderId.longValue() != 0)) {
							orderService.deleteOrder(orderId);
						}

			return navigateToHomePage(model, orderId, session);
		}
	}

	/**
	 * This method handles requests coming to the application’s context path
	 * (/deleteItem/{menuId}) by redirecting to the view named reviewOrder.html
	 * after deleting an item sent in PathVariable menuId. If all the items in the
	 * order are deleted, the user is navigated to the index page to start ordering
	 * again.
	 *
	 * @param menuId the menu item that needs to be deleted from the order
	 * @param model  remaining items left in the order
	 * @param req    HttpServletRequest for handling http requests
	 * @param res    HttpServletResponse for handling http responses
	 * @return String view to display reviewOrder.html or index.html
	 * @throws Exception
	 */
	@RequestMapping("/deleteItem/{menuId}")
	public String deleteItem(@PathVariable("menuId") Long menuId, Model model, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		logger.debug("Delete Item Start");

		// Get the Session object for the HttpServletRequest object to maintain session
		// attributes
		HttpSession session = req.getSession();

		// retrieve the orderId from session
		Long orderId = (Long) session.getAttribute("orderId");
		try {
			logger.debug("Deleting menu from order with id:" + orderId + ", item:"
					+ menuService.retrieveMenuByMenuId(menuId));
			// delete the select item that was sent in PathVariable menuId and return the
			// remaining menu item id's
			List<Long> menuIds = orderService.deleteItemfromOrder(orderId, menuId);

			// load the remaining items
			List<Menu> remainingItems = menuService.getMenuListByLongIds(menuIds);

			if (!remainingItems.isEmpty()) {
				// if there are items left in the order, aggregate the menu item prices and
				// store in in orderTotal
				Float orderTotal = orderService.computeOrderTotal(orderService.findOrderById(orderId));

				// set the orderTotal in session, to use for the life of the order
				logger.debug("orderTotal::" + orderTotal);
				session.setAttribute("orderTotal", orderTotal);

				// set the remaining items in menu model to be displayed on the Review Order
				// page
				model.addAttribute("menu", remainingItems);
				logger.debug("Review Order End");

				// navigate the user to reviewOrder.html page
				return "reviewOrder";
			} else {
				// if the user deletes all the items in the order
				logger.debug("No items remaining in order, deleting order and returning to Menu");

				// delete any stale data from DB
				if ((orderId != null) && (orderId.longValue() != 0)) {
					orderService.deleteOrder(orderId);
				}

				return navigateToHomePage(model, orderId, session);
			}
		} catch (Exception e) {
			// if any exceptions happen during deleting an item from order, navigate the use
			// to the main page
			logger.error("Exception during Delete Item:" + e);

			// delete any stale data from DB
			if ((orderId != null) && (orderId.longValue() != 0)) {
				orderService.deleteOrder(orderId);
			}

			return navigateToHomePage(model, orderId, session);
		}
	}

	/**
	 * This method handles requests coming to the application’s context path
	 * (/payment) by redirecting to the view named payment.html. This captures the
	 * payment details entered by the user for placing the order. Order Id and total
	 * are displayed on the payment page by retrieving from session
	 *
	 * @return view to display payment.html
	 */
	@RequestMapping("/payment")
	public String getPayment(Model model) {
		logger.debug("Payment Details");
		// need to create a paymentVO and set in model to capture the payment details
		model.addAttribute("paymentVO", new PaymentVO());
		return "payment";
	}

	/**
	 * This method handles requests coming to the application’s context path
	 * (/final) by redirecting to the view named final.html. This method will
	 * process the payment and finalize the order
	 *
	 * @param paymentVO model attribute to hold the payment details from UI
	 * @param model     to set attributes to show on UI
	 * @param req       HttpServletRequest for handling http requests
	 * @param res       HttpServletResponse for handling http responses
	 * @return String view to display final.html
	 * @throws Exception
	 */
	@RequestMapping(value = "/final", method = RequestMethod.POST)
	public String getFinalPage(@ModelAttribute("paymentVO") PaymentVO paymentVO, Model model, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		logger.debug("Final - Processing Payment start");

		// Get the Session object for the HttpServletRequest object to maintain session
		// attributes
		HttpSession session = req.getSession();

		// retrieve the orderId from session
		Long orderId = (Long) session.getAttribute("orderId");
		logger.debug("Order id:" + orderId);
		try {
			// process and save the payment details
			orderService.updatePaymentDetails(orderId, paymentVO);
			logger.debug("Final - Processing Payment end");

			// navigate the use to the order summary page
			return "final";
		} catch (Exception e) {
			// if any exceptions happen during deleting an item from order, navigate the use
			// to the main page
			logger.error("Exception during processing payment:" + e);

			// delete any stale data from DB
			if ((orderId != null) && (orderId.longValue() != 0)) {
				orderService.deleteOrder(orderId);
			}

			return navigateToHomePage(model, orderId, session);
		}
	}

	/**
	 * This is a reusable method that sets attributes for navigating the user to
	 * Home Page index.html
	 *
	 * @param model   used for setting data from table to be used on view index.html
	 * @param orderId primary key of the order
	 * @param session HttpSession for removing attributes
	 * @return String view to display index.html
	 * @throws Exception
	 */
	private String navigateToHomePage(Model model, Long orderId, HttpSession session) throws Exception {
		// remove any stale date in session attributes
		session.removeAttribute("orderId");
		session.removeAttribute("orderTotal");

		// re-initialize the object that holds the items added to order
		model.addAttribute("espadaVO", new EspadaVO());

		// load the full menu and navigate the user to main page index.html
		model.addAttribute("menu", menuService.retrieveMenu());
		return "index";
	}

}

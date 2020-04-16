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
import com.elespada.model.Menu;
import com.elespada.repo.MenuRepository;
import com.elespada.service.MenuService;
import com.elespada.service.OrderService;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	MenuService menuService;

	@Autowired
	OrderService orderService;

	@RequestMapping("index")
	public String getIndex(Model model, HttpServletRequest req, HttpServletResponse res) {
		logger.debug("Menu Display Start");
		HttpSession session = req.getSession();
		session.removeAttribute("orderId");
		session.removeAttribute("orderTotal");
		model.addAttribute("menu", menuRepository.findAll());
		model.addAttribute("espadaVO", new EspadaVO());
		logger.debug("Menu Display End");
		return "/index";
	}

	@RequestMapping(value = "/reviewOrder", method = RequestMethod.POST)
	public String reviewOrder(@ModelAttribute("espadaVO") EspadaVO espadaVO, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		logger.debug("Review Order Start");
		try {
			Long orderId = orderService.createOrder().getOrderId();
			logger.debug("Order Start:" + orderId);
			HttpSession session = req.getSession();
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
			return "/index";
		}
	}

	@RequestMapping("/deleteItem/{menuId}")
	public String deleteItem(@PathVariable("menuId") Long menuId, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		logger.debug("Delete Item Start");
		try {
			HttpSession session = req.getSession();
			Long orderId = (Long) session.getAttribute("orderId");
			logger.debug("Deleting menu from order with id:"+orderId+", item:"+menuRepository.findById(menuId));
			List<Long> menuIds = orderService.deleteItemfromOrder(orderId, menuId);
			List<Menu> remainingItems = menuService.getMenuListByLongIds(menuIds);
			if(!remainingItems.isEmpty()) {
				Float orderTotal = orderService.computeOrderTotal(orderService.findOrderById(orderId));
				logger.debug("orderTotal::"+orderTotal);
				session.setAttribute("orderTotal", orderTotal);
				model.addAttribute("menu", remainingItems);
				logger.debug("Review Order End");
				return "/reviewOrder";
			} else {
				logger.debug("No items remaining in order, deleting order and returning to Menu");
				orderService.deleteOrder(orderId);
				session.removeAttribute("orderId");
				session.removeAttribute("orderTotal");
				model.addAttribute("espadaVO", new EspadaVO());
				model.addAttribute("menu", menuRepository.findAll());
				return "redirect:/index";
			}
		} catch(Exception e) {
			logger.error("Exception during Delete Item:" + e);
			return "/index";
		}
	}

}

package com.elespada.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elespada.VO.EspadaVO;
import com.elespada.repo.MenuRepository;
import com.elespada.service.MenuService;
import com.elespada.service.OrderService;

@Controller
public class IndexController {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	MenuService menuService;

	@Autowired
	OrderService orderService;

	@RequestMapping("index")
	public String getIndex(Model model, HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		session.removeAttribute("orderId");
		model.addAttribute("menu", menuRepository.findAll());
		model.addAttribute("espadaVO", new EspadaVO());
		return "index";
	}

	@RequestMapping(value = "/reviewOrder", method = RequestMethod.POST)
	public String reviewOrder(@ModelAttribute("espadaVO") EspadaVO espadaVO, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		if (!StringUtils.isEmpty(espadaVO.getMenuIds())) {
			Long orderId = orderService.createOrder().getOrderId();
			HttpSession session = req.getSession();
			session.setAttribute("orderId", orderId);
			orderService.createOrderDetails(espadaVO.getMenuIds(), orderId);
			model.addAttribute("menu", menuService.getMenuListbyIds(espadaVO.getMenuIds()));
			return "reviewOrder";
		} else {
			return "index";
		}
	}

	@RequestMapping("/deleteItem/{menuId}")
	public String deleteItem(@PathVariable("menuId") Long menuId, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		HttpSession session = req.getSession();
		Long orderId = (Long) session.getAttribute("orderId");
		if (!StringUtils.isEmpty(orderId) || !StringUtils.isEmpty(menuId)) {
			List<Long> menuIds = orderService.deleteItemfromOrder(orderId, menuId);
			model.addAttribute("menu", menuService.getMenuListByLongIds(menuIds));
			return "reviewOrder";
		} else {
			return "index";
		}
	}

}

package com.elespada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elespada.VO.EspadaVO;
import com.elespada.repo.MenuRepository;
import com.elespada.service.MenuService;

@Controller
public class IndexController {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	MenuService menuService;

	@RequestMapping("index")
	public String getIndex(Model model) {
		model.addAttribute("menu", menuRepository.findAll());
		model.addAttribute("espadaVO", new EspadaVO());
		return "index";
	}

	@RequestMapping(value = "/reviewOrder", method = RequestMethod.POST)
	public String paymentSummary(@ModelAttribute("espadaVO") EspadaVO espadaVO, Model model) {
		if (!StringUtils.isEmpty(espadaVO.getMenuIds())) {
			model.addAttribute("menu", menuService.getMenuListbyIds(espadaVO.getMenuIds()));
		} else {
			model.addAttribute("menu", menuRepository.findAll());
		}

		return "reviewOrder";
	}

}

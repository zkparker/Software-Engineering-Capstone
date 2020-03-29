package com.elespada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elespada.model.Menu;
import com.elespada.repo.MenuRepository;

@Controller
public class IndexController {
	
	@Autowired
	MenuRepository repository;
	
	@RequestMapping("index")
	public String getIndex(Model model) {
		model.addAttribute("menu", repository.findAll());
		return "index";
	}

}

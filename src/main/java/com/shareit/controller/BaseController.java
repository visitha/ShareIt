package com.shareit.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shareit.dao.DonatorDAO;
import com.shareit.model.Donator;

@Controller
public class BaseController {

	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

	        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	        DonatorDAO donatorDAO = (DonatorDAO) context.getBean("donatorDAO");
	        
	        Donator customer = new Donator("Visitha", 28);
	        donatorDAO.insert(customer);
	        
	        Donator donator = new Donator("Buddhiprabha", 25);
	        donatorDAO.insert(donator);
	 
	        Donator customer1 = donatorDAO.findByCustomerId(1);
	        System.out.println(customer1.getName());

	        
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", customer1.getName());
		logger.debug("[welcome] counter : {}", customer1.getName());

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		logger.debug("[welcomeName] counter : {}", counter);
		return VIEW_INDEX;

	}

}
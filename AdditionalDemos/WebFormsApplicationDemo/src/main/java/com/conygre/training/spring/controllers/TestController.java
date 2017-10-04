package com.conygre.training.spring.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.conygre.training.spring.controllers.validations.CompactDiscVO;
import com.conygre.training.spring.controllers.validations.SayHello;

@Controller
public class TestController {
	
	@RequestMapping("/sayHello")
	public ModelAndView sayHello()  {
		return new ModelAndView("hello", "helloModel", new com.conygre.training.spring.controllers.validations.SayHello());
	}
	@RequestMapping("/addName")
	public ModelAndView submitName(@ModelAttribute ("helloModel") SayHello hello, BindingResult result) {    
		
		// adding the name to a database first and then .....
		
		return new ModelAndView("name", "display", hello);
	}
}

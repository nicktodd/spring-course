package com.conygre.training.spring.controllers;

import com.conygre.training.spring.controllers.validations.CompactDiscVO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes
public class CompactDiscController {



	@RequestMapping(value = "/addCD", method = RequestMethod.POST)
	public ModelAndView addCompactDisc(
			@Valid @ModelAttribute("compactDiscModel") CompactDiscVO disc,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return new ModelAndView("compactdisc", "compactDiscModel", disc);// "redirect:compactdisc.htm
		}
			session.setAttribute("disc", disc);
		
		System.out.println(disc.getTitle() + " by " + disc.getArtist()
				+ " has been added.");
		return new ModelAndView("confirm", "compactDiscModel", disc);// "redirect:compactdisc.html";
	}

	@RequestMapping("/compactdisc")
	public ModelAndView showCompactDiscs() {
		// first argument is JSP file name, second is model name, third is the
		// model object
		return new ModelAndView(
				"compactdisc",
				"compactDiscModel",
				new com.conygre.training.spring.controllers.validations.CompactDiscVO());
	}

}
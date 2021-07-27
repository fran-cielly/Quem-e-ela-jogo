package br.com.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Home {
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
}

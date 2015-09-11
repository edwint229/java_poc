package org.edwin.vote.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	@RequestMapping(value = "/notfound")
	public String handle404() {
		return "errors/404";
	}
	
	@RequestMapping(value = "/error")
	public String handleError() {
		return "errors/503";
	}
}

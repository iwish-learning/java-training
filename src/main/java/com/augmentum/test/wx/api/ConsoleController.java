package com.augmentum.test.wx.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsoleController {
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		System.out.println("this is test case");
		return "index";
	}
}

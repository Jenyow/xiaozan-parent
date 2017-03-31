package com.xiaozan.web.jsp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

}
package com.xiaozan.web.xiaozan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 在Spring Boot应用中，任何Spring @RestController 默认应该渲染为JSON响应
 */
@RestController
public class LoginController {
	
	@RequestMapping(value = "/")
    public String home() {   
        return "index";
    }
	
}

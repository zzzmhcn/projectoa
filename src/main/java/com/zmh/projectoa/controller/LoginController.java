package com.zmh.projectoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}

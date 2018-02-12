package com.zmh.projectoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: ZMH 
 * @Date: 2018/2/9 22:48
 * 登陆Controller
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}

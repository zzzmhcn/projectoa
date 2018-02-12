package com.zmh.projectoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Author: ZMH 
 * @Date: 2018/2/9 22:49
 * 用于存放主页以及相关页面的Controller
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/calendar")
    public String calendar(){
        return "calendar";
    }
    @RequestMapping(value = "/form")
    public String form(){
        return "form";
    }
    @RequestMapping(value = "/404")
    public String error404(){
        return "404";
    }
}

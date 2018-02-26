package com.zmh.projectoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Author: ZMH 
 * @Date: 2018/2/9 22:49
 * 主页的Controller
 */
@Controller
public class IndexController {
    /**
     * 主页
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * 没有权限访问
     */
    @RequestMapping(value = "/403")
    public String error403(){
        return "403";
    }
}

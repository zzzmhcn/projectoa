package com.zmh.projectoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ChengShanyunduo
 * 2018/2/14
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    /**
     * 创建用户页面
     * @return
     */
    @RequestMapping(value = "/user_create")
    public String createView(){
        return "user_create";
    }


}

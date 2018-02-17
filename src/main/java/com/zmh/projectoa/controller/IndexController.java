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
     * 日历页面
     */
    @RequestMapping(value = "/calendar")
    public String calendar(){
        return "calendar";
    }

    /**
     * 人员信息
     */
    @RequestMapping(value = "/userinfo")
    public String userinfo(){
        return "userinfo";
    }

    /**
     * 个人信息
     */
    @RequestMapping(value = "/user")
    public String user(){
        return "user";
    }

    /**
     * 通知公告
     */
    @RequestMapping(value = "/notice")
    public String notice(){
        return "notice";
    }

    /**
     * 站内信箱
     */
    @RequestMapping(value = "/message")
    public String message(){
        return "message";
    }

    /**
     * 站内信箱
     */
    @RequestMapping(value = "/logs")
    public String logs(){
        return "logs";
    }

}

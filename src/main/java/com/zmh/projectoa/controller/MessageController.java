package com.zmh.projectoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zmh
 * @date 2018/2/1521:41
 * 站内信
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
    /**
     * 站内信箱
     */
    @RequestMapping(value = "/message")
    public String message(){
        return "message";
    }
}

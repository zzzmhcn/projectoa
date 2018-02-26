package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zmh
 * @date 2018/2/1521:41
 * 站内信
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    UsersService usersService;

    /**
     * 站内信箱
     */
    @RequestMapping(value = "/message")
    public String message(){
        return "message";
    }


    /**
     *  返回特别定制的所有用户信息给用户选择站内信发给谁
     */
    @RequestMapping(value = "/getAllUser")
    @ResponseBody
    public ReturnDto getAllUser(){
       return usersService.getAllUser();
    }


}

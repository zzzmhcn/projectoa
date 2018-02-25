package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Userinfo;
import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.service.UserinfoService;
import com.zmh.projectoa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ChengShanyunduo
 * 2018/2/25
 */
@Controller
@RequestMapping("/userinfo")
public class UserinfoController {

    @Autowired
    UserinfoService userinfoService;

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/getUserinfo")
    @ResponseBody
    public ReturnDto getUserinfo(){
        //id应该再session中取，id为user表中id
        Integer id = 7;
        Userinfo userinfo = userinfoService.getUserinfoByUserId(id);
        return ReturnDto.buildSuccessReturnDto(userinfo);
    }

    @RequestMapping(value = "/saveUserinfo")
    @ResponseBody
    public ReturnDto saveUserinfo(Userinfo userinfo){
        //id应该再session中取，id为user表中id
        Integer id = 7;
        userinfo.setUserId(id);
        int result = userinfoService.saveUserinfo(userinfo);
        if (result == 1){
            return  ReturnDto.buildSuccessReturnDto("保存成功");
        }else {
            return ReturnDto.buildFailedReturnDto("保存失败，请联系管理员");
        }
    }

    @RequestMapping(value = "/getSelf")
    @ResponseBody
    public ReturnDto getSelf(){
        //用户id从session中取，是user表中的id
        Integer id = 7;
        Users user = usersService.detailUser(id);
        return ReturnDto.buildSuccessReturnDto(user);
    }
}

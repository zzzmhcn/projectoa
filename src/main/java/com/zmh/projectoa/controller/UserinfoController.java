package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Userinfo;
import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.service.UserinfoService;
import com.zmh.projectoa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    UsersService usersService;    /**
     * 个人
     */
    @RequestMapping(value = "/userinfo")
    public String userinfo(){
        return "userinfo";
    }



    @RequestMapping(value = "/getUserinfo")
    @ResponseBody
    public ReturnDto getUserinfo(HttpServletRequest request) {
        //id应该再session中取，id为user表中id
        Integer id = (Integer) request.getSession().getAttribute("userID");
        Userinfo userinfo = userinfoService.getUserinfoByUserId(id);
        return ReturnDto.buildSuccessReturnDto(userinfo);
    }

    @RequestMapping(value = "/saveUserinfo")
    @ResponseBody
    public ReturnDto saveUserinfo(Userinfo userinfo, HttpServletRequest request) {
        //id应该再session中取，id为user表中id
        Integer id = (Integer) request.getSession().getAttribute("userID");
        userinfo.setUserId(id);
        int result = userinfoService.saveUserinfo(userinfo);
        if (result == 1) {
            return ReturnDto.buildSuccessReturnDto("保存成功");
        } else {
            return ReturnDto.buildFailedReturnDto("保存失败，请联系管理员");
        }
    }

    @RequestMapping(value = "/getSelf")
    @ResponseBody
    public ReturnDto getSelf(HttpServletRequest request) {
        //用户id从session中取，是user表中的id
        Integer id = (Integer) request.getSession().getAttribute("userID");
        Users user = usersService.detailUser(id);
        return ReturnDto.buildSuccessReturnDto(user);
    }

    @RequestMapping(value = "/changePassWord")
    public String changePassWord() {
        return "changePassWord";
    }

    @RequestMapping(value = "/setNewPassWord")
    @ResponseBody
    public ReturnDto setPassWord(@RequestParam("oldpassword")String oldpassword,
        @RequestParam("newpassword")String newpassword,
        @RequestParam("repeatpassword")String repeatpassword,
        HttpServletRequest request) {
        //用户id从session中取，是user表中的id
        Integer id = (Integer) request.getSession().getAttribute("userID");
        //验证旧密码是否正确环节

        //验证新密码是否合法环节 首先防止null或者空字符串蒙混过关 并且再次确认两次输入一致
        if(newpassword != null && !"".equals(newpassword) && newpassword == repeatpassword){
            //验证新密码是否合法

            //更新新密码

            //返回修改成功
            return ReturnDto.buildSuccessReturnDto("success");
        }
        //默认返回错误
        return ReturnDto.buildFailedReturnDto("error");
    }
}

package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: ZMH
 * @Date: 2018/2/9 22:48
 * 登陆Controller
 */
@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class.getName());
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login/check")
    //@ResponseBody
    public String check(@RequestParam("username")String username, @RequestParam("password")String password){
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
                Users user = (Users)currentUser.getPrincipals().getPrimaryPrincipal();
                Integer id = user.getId();
                user = new Users();
                user.setLastLoginTime(new Date());
                usersService.editUser(id,user);
            }catch (IncorrectCredentialsException ae){
                logger.error("登录验证不通过 : 账号或密码不正确！ username:"+username+" password:"+password);
            }
            catch (AuthenticationException ae){
                logger.error("登录验证不通过 : " + ae.getMessage());
            }
        }
        return "redirect:/index";
        //return ReturnDto.buildSuccessReturnDto("");
    }
}

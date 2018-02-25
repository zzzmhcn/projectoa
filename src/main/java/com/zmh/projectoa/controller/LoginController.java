package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZMH
 * @Date: 2018/2/9 22:48
 * 登陆Controller
 */
@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class.getName());

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login/check")
    //@ResponseBody
    public String confirm(@RequestParam("username")String username, @RequestParam("password")String password,
                             HttpServletRequest request){
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
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

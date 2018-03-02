package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Userinfo;
import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.service.RedisService;
import com.zmh.projectoa.service.UserinfoService;
import com.zmh.projectoa.service.UsersService;
import com.zmh.projectoa.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(LoginController.class.getName());

    @Autowired
    UserinfoService userinfoService;

    @Autowired
    UsersService usersService;

    @Autowired
    RedisService redisService;

    /**
     * 默认头像
     */
    public static final Integer defaultHeadImage = 9;
    /**
     * 个人
     */
    @RequestMapping(value = "/userinfo")
    public String userinfo() {
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
        //头像保存到redis
        redisService.setValue("headImage_" + id, String.valueOf(userinfo.getHeadImage()));
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
    public ReturnDto setPassWord(@RequestParam("oldpassword") String oldpassword,
                                 @RequestParam("newpassword") String newpassword,
                                 @RequestParam("repeatpassword") String repeatpassword,
                                 HttpServletRequest request) {
        //用户id从session中取，是user表中的id
        Integer id = (Integer) request.getSession().getAttribute("userID");
        //验证旧密码是否正确环节
        Users user = usersService.detailUser(id);
        if (!user.getPassword().equals(MD5Util.string2MD5(oldpassword))) {
            logger.error("旧密码不正确");
            return ReturnDto.buildFailedReturnDto("旧密码不正确");
        }
        System.out.println(newpassword + "\t" + repeatpassword);
        //验证新密码是否合法环节 首先防止null或者空字符串蒙混过关 并且再次确认两次输入一致
        if (newpassword != null && !"".equals(newpassword) && newpassword.equals(repeatpassword)) {
            //验证新密码是否合法
            if (!newpassword.matches(".*[a-zA-Z0-9]+.*") || newpassword.length() > 20 || newpassword.length() < 6) {
                logger.error("新密码不合法");
                return ReturnDto.buildFailedReturnDto("新密码不合法");
            }
            //更新新密码
            Users temp = new Users();
            temp.setPassword(MD5Util.string2MD5(newpassword));
            int i = usersService.editUser(id, temp);
            System.out.println(i + " " + MD5Util.string2MD5(newpassword));
            if (i > 0) {
                //返回修改成功
                return ReturnDto.buildSuccessReturnDto("success");
            }
        }
        logger.error("未知的异常");
        //默认返回错误
        return ReturnDto.buildFailedReturnDto("error");
    }

    /**
     * 根据 id 返回头像num
     */
    @RequestMapping(value = "/getHeadImageNumByID")
    @ResponseBody
    public ReturnDto getHeadImageNumByID(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("userID");
        String num = redisService.getValue("headImage_" + id);
        Integer result = defaultHeadImage;//先给个默认值
        try {
            result = Integer.parseInt(num);
        } catch (NumberFormatException e) {
        }
        return ReturnDto.buildSuccessReturnDto(result);
    }

    /**
     * 根据 id 返回头像num
     * 这里先获取redis中best的ID，是最佳员工的userID
     * 再获取这个人的头像
     * 那么这个人如果没设置过头像 redis的headImage_+他的ID是没有内容的
     * 这样的情况下给默认值1 就是用默认的头像01.jpg
     */
    @RequestMapping(value = "/getBestImageNum")
    @ResponseBody
    public ReturnDto getBestImageNum() {
        String num = redisService.getValue("headImage_" + redisService.getValue("best"));
        Integer result = defaultHeadImage;//先给个默认值
        try {
            if(num != null && !"".equals(num))
                result = Integer.parseInt(num);
        } catch (NumberFormatException e) {
        }
        return ReturnDto.buildSuccessReturnDto(result);
    }

    /**
     *  获取最佳员工姓名
     */
    @RequestMapping(value = "/getBestUserName")
    @ResponseBody
    public ReturnDto getBestUserName() {
        String result = "暂无";
        try {
            String num = redisService.getValue("best");
            if(num != null && !"".equals(num))
                result = usersService.detailUser(Integer.parseInt(num)).getRealname();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return ReturnDto.buildSuccessReturnDto(result);
    }

}

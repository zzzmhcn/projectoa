package com.zmh.projectoa.controller;

import com.github.pagehelper.PageInfo;
import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Userinfo;
import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.service.UserinfoService;
import com.zmh.projectoa.service.UsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Objects;

/**
 * Created by ChengShanyunduo
 * 2018/2/14
 */
@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UserinfoService userinfoService;

    /**
     * 人员
     */
    @RequestMapping(value = "/user")
    public String user(){
        return "user";
    }

    /**
     * 创建用户页面
     * @return
     */
    @RequestMapping(value = "/user_create")
    public String createView(){
        return "user_create";
    }

    /**
     * 创建职工
     * @param users
     * @return
     */
    @RequestMapping(value = "/userCreate")
    @ResponseBody
    public ReturnDto createUser(Users users){
        if (Objects.isNull(users.getUsername())){
            return ReturnDto.buildFailedReturnDto("请输入用户名");
        }
        if (Objects.isNull(users.getRealname())){
            return ReturnDto.buildFailedReturnDto("请输入员工姓名");
        }
        if (Objects.isNull(users.getDepartmentId())){
            return ReturnDto.buildFailedReturnDto("请选择部门");
        }
        if (Objects.isNull(users.getPositionId())){
            return ReturnDto.buildFailedReturnDto("请选择职位");
        }

        Users user = usersService.queryUserByUsername(users);
        if (!Objects.isNull(user)){
            return ReturnDto.buildFailedReturnDto("用户名已存在");
        }

        int count = usersService.createUser(users);

        if (count == 1){
            return ReturnDto.buildSuccessReturnDto("创建成功");
        }else {
            return ReturnDto.buildFailedReturnDto("创建失败");
        }
    }

    /**
     * 用户列表
     * @param users
     * @return
     */
    @RequestMapping(value = "/userList")
    @ResponseBody
    public ReturnDto userList(Users users){
        PageInfo list = usersService.userList(users);
        return ReturnDto.buildSuccessReturnDto(list);
    }

    /**
     * 修改用户页面
     * @param
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editView(){
        return "user_edit";

    }

    /**
     * 用户回显
     * @param id
     * @return
     */
    @RequestMapping(value = "/detailUser/{id}")
    @ResponseBody
    public ReturnDto detailUser(@PathVariable(name="id") Integer id){
        Users user = usersService.detailUser(id);
        return ReturnDto.buildSuccessReturnDto(user);
    }

    /**
     * 修改用户
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/userEdit/{id}")
    @ResponseBody
    public ReturnDto editUser(@PathVariable(name="id") Integer id, Users user){
        int result = usersService.editUser(id, user);
        if (result == 1){
            return ReturnDto.buildSuccessReturnDto("修改成功");
        }else {
            return ReturnDto.buildFailedReturnDto("修改失败");
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/userDelete/{id}")
    @ResponseBody
    public ReturnDto userDelete(@PathVariable(name="id") Integer id){
        int result = usersService.deleteUser(id);
        if (result == 1){
            return  ReturnDto.buildSuccessReturnDto("删除成功");
        }else {
            return ReturnDto.buildFailedReturnDto("删除失败，请联系管理员");
        }
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/userReverse/{id}")
    @ResponseBody
    public ReturnDto userReverse(@PathVariable(name="id") Integer id){
        int result = usersService.reverseUser(id);
        if (result == 1){
            return  ReturnDto.buildSuccessReturnDto("重置成功");
        }else {
            return ReturnDto.buildFailedReturnDto("重置失败，请联系管理员");
        }
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public ReturnDto getUser(@RequestParam("userID")Integer id){
        Users user = usersService.detailUser(id);
        return ReturnDto.buildSuccessReturnDto(user);
    }

    @RequestMapping(value = "/userinfo/{id}")
    public String userinfoPage(){
        return "userinfo_detail";
    }

    @RequestMapping(value = "/userinfo/detail/{id}")
    @ResponseBody
    public ReturnDto userinfoDetail(@PathVariable(name="id") Integer id){
        Userinfo userinfo = userinfoService.getUserinfoByUserId(id);
        return ReturnDto.buildSuccessReturnDto(userinfo);
    }

}

package com.zmh.projectoa.controller;

import com.github.pagehelper.PageInfo;
import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.service.UsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editUser(){
        return "user_edit";

    }

    @RequestMapping(value = "/detailUser/{id}")
    @ResponseBody
    public ReturnDto detailUser(@PathVariable(name="id") Integer id){
        Users user = usersService.detailUser(id);
        return ReturnDto.buildSuccessReturnDto(user);
    }

}

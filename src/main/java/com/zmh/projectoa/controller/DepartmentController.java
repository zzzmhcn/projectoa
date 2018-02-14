package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Departments;
import com.zmh.projectoa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ChengShanyunduo
 * 2018/2/14
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /**
     * 查询部门
     * @return
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ReturnDto queryDepartment(){
        List<Departments> departments = departmentService.queryDepartment();
        return ReturnDto.buildSuccessReturnDto(departments);
    }

}

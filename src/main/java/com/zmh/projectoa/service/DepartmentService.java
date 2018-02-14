package com.zmh.projectoa.service;

import com.zmh.projectoa.mapper.DepartmentsMapper;
import com.zmh.projectoa.model.Departments;
import com.zmh.projectoa.model.DepartmentsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ChengShanyunduo
 * 2018/2/14
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentsMapper departmentsMapper;

    /**
     * 查询部门
     * @return
     */
    public List<Departments> queryDepartment(){
        DepartmentsExample departmentsExample = new DepartmentsExample();
        return departmentsMapper.selectByExample(departmentsExample);
    }
}

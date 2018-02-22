package com.zmh.projectoa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmh.projectoa.mapper.UserinfoMapper;
import com.zmh.projectoa.mapper.UsersMapper;
import com.zmh.projectoa.model.Userinfo;
import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.model.UsersExample;
import com.zmh.projectoa.util.MD5Util;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by ChengShanyunduo
 * 2018/2/14
 */
@Service
public class UsersService {

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    UserinfoMapper userinfoMapper;

    /**
     * 创建职工
     * @param users
     * @return
     */
    public int createUser(Users users){
        String password = MD5Util.string2MD5("123456");
        users.setPassword(password);

        int count = usersMapper.insertSelective(users);

        if (count != 1){
            return 0;
        }

        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUsernameEqualTo(users.getUsername());
        Users user = queryUserByUsername(users);

        Userinfo userinfo = new Userinfo();
        userinfo.setUserId(user.getId());

        count = userinfoMapper.insertSelective(userinfo);

        return count;
    }

    /**
     * 根据用户名查找用户
     * @param users
     * @return
     */
    public Users queryUserByUsername(Users users){
        return usersMapper.queryUserByUsername(users.getUsername());
    }

    /**
     * 分页查询
     * @param userDto
     * @return
     */
    public PageInfo userList(Users userDto){
        userDto.setIsDel("0");

        PageHelper.startPage(1, 10);
        List<Users> list = usersMapper.queryBySelective(userDto);
        PageInfo page = new PageInfo(list);

        return page;
    }

    /**
     * 用户回显
     * @param id
     * @return
     */
    public Users detailUser(Integer id){
        Users user = usersMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 修改用户
     * @param id
     * @param user
     * @return
     */
    public int editUser(Integer id, Users user){
        user.setUsername(null);
        user.setId(id);
        int result = usersMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public int deleteUser(Integer id){
        Users user = new Users();
        user.setId(id);
        user.setIsDel("1");

        Userinfo userinfo = userinfoMapper.queryUserinfoByUserid(id);
        userinfo.setIsDel("1");
        int result = usersMapper.updateByPrimaryKeySelective(user);
        int result2 = userinfoMapper.updateByPrimaryKeySelective(userinfo);
        if (result == 1 && result2 == 1){
            return 1;
        }
        return 0;
    }

}

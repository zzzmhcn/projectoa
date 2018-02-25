package com.zmh.projectoa.service;

import com.zmh.projectoa.mapper.UserinfoMapper;
import com.zmh.projectoa.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ChengShanyunduo
 * 2018/2/25
 */
@Service
public class UserinfoService {
    @Autowired
    UserinfoMapper userinfoMapper;

    public Userinfo getUserinfoByUserId(Integer id){
        Userinfo userinfo = userinfoMapper.queryUserinfoByUserid(id);
        return userinfo;
    }

    public int saveUserinfo(Userinfo userinfo){
        Integer id = userinfoMapper.queryUserinfoByUserid(userinfo.getUserId()).getId();
        userinfo.setId(id);
        int result = userinfoMapper.updateByPrimaryKeySelective(userinfo);
        return result;
    }
}

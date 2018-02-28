package com.zmh.projectoa.mapper;

import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.model.UsersExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {
    long countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users queryUserByUsername (@Param("username") String username);

    List<Users> queryBySelective(Users users);

    List<Map<String,String>> queryAll(Integer id);

    List<Users> queryAllUsers();
}
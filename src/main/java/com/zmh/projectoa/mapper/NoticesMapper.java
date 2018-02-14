package com.zmh.projectoa.mapper;

import com.zmh.projectoa.model.Notices;
import com.zmh.projectoa.model.NoticesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoticesMapper {
    long countByExample(NoticesExample example);

    int deleteByExample(NoticesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Notices record);

    int insertSelective(Notices record);

    List<Notices> selectByExampleWithBLOBs(NoticesExample example);

    List<Notices> selectByExample(NoticesExample example);

    Notices selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Notices record, @Param("example") NoticesExample example);

    int updateByExampleWithBLOBs(@Param("record") Notices record, @Param("example") NoticesExample example);

    int updateByExample(@Param("record") Notices record, @Param("example") NoticesExample example);

    int updateByPrimaryKeySelective(Notices record);

    int updateByPrimaryKeyWithBLOBs(Notices record);

    int updateByPrimaryKey(Notices record);
}
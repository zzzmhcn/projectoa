package com.zmh.projectoa.mapper;

import com.zmh.projectoa.model.Calendar;
import com.zmh.projectoa.model.CalendarExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CalendarMapper {
    long countByExample(CalendarExample example);

    int deleteByExample(CalendarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Calendar record);

    int insertSelective(Calendar record);

    List<Calendar> selectByExample(CalendarExample example);

    Calendar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Calendar record, @Param("example") CalendarExample example);

    int updateByExample(@Param("record") Calendar record, @Param("example") CalendarExample example);

    int updateByPrimaryKeySelective(Calendar record);

    int updateByPrimaryKey(Calendar record);
}
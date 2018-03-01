package com.zmh.projectoa.mapper;

import com.zmh.projectoa.model.Messages;
import com.zmh.projectoa.model.MessagesExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessagesMapper {
    long countByExample(MessagesExample example);

    int deleteByExample(MessagesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Messages record);

    int insertSelective(Messages record);

    List<Messages> selectByExampleWithBLOBs(MessagesExample example);

    List<Messages> selectByExample(MessagesExample example);

    List<Map<String,String>> selectByreceiveID(Integer ID);

    List<Map<String,String>> selectByIDs(List<Integer> IDs);

    List<Map<String,String>> selectLastOneByReceiveID(Integer receiveID);

    Messages selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Messages record, @Param("example") MessagesExample example);

    int updateByExampleWithBLOBs(@Param("record") Messages record, @Param("example") MessagesExample example);

    int updateByExample(@Param("record") Messages record, @Param("example") MessagesExample example);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKeyWithBLOBs(Messages record);

    int updateByPrimaryKey(Messages record);
}
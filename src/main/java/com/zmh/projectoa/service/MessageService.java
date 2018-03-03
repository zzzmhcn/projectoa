package com.zmh.projectoa.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmh.projectoa.mapper.MessagesMapper;
import com.zmh.projectoa.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zmh
 * @date 2018/2/2621:58
 */
@Service
public class MessageService {
    @Autowired
    private MessagesMapper messagesMapper;

    public int insertMessage(Messages messages) {
        messagesMapper.insertSelective(messages);
        return messages.getId();
    }
    /**
     * 传入未读ID数组 返回未读信息 (Redis里含有的ID再Mysql中)
     */
    public List<Map<String,String>> selectByIDs(List<Integer> IDs){
        return messagesMapper.selectByIDs(IDs);
    }

    /**
     * 传入接受者ID 返回所有此人的信息 (Mysql中)
     */
    public PageInfo selectByreceiveID(Integer ID, Integer pageNum){
        PageHelper.startPage(pageNum, 10);
        List<Map<String, String>> list = messagesMapper.selectByreceiveID(ID);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public Messages selectByID(Integer id){
        return messagesMapper.selectByPrimaryKey(id);
    }

    public List<Map<String,String>> getLastMessage(Integer receiveID){
        return messagesMapper.selectLastOneByReceiveID(receiveID);
    }
}

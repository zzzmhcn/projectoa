package com.zmh.projectoa.service;

import com.zmh.projectoa.mapper.MessagesMapper;
import com.zmh.projectoa.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

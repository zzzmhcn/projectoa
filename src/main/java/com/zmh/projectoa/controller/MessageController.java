package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Messages;
import com.zmh.projectoa.service.MessageService;
import com.zmh.projectoa.service.RedisService;
import com.zmh.projectoa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author zmh
 * @date 2018/2/1521:41
 * 站内信
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    UsersService usersService;
    @Autowired
    MessageService messageService;
    @Autowired
    RedisService redisService;
    /**
     * 站内信箱
     */
    @RequestMapping(value = "/message")
    public String message(){
        return "message";
    }

    /**
     * 站内信箱
     */
    @RequestMapping(value = "/send_message")
    @ResponseBody
    public ReturnDto sendMessage(@RequestParam("userIDs")String userIDs, @RequestParam("title")String title,
                                 @RequestParam("message")String message, HttpServletRequest request){
        String userID[] = userIDs.split(",");
        Integer sendId = (Integer) request.getSession().getAttribute("userID");
        for (String receiveId : userID) {
            Messages messages = new Messages();
            messages.setSendId(sendId);
            messages.setReceiveId(Integer.parseInt(receiveId));
            messages.setTitle(title);
            messages.setMessage(message);
            int messageID = messageService.insertMessage(messages);
            String redisValue = redisService.getValue("message_"+sendId);
            if(Objects.isNull(redisValue) || "null".equals(redisValue))
                redisValue = "";
            redisValue += messageID+",";
            redisService.setValue("message_"+receiveId,redisValue);
        }
        return ReturnDto.buildSuccessReturnDto("success");
    }


    /**
     *  返回特别定制的所有用户信息给用户选择站内信发给谁
     */
    @RequestMapping(value = "/getAllUser")
    @ResponseBody
    public ReturnDto getAllUser(){
       return usersService.getAllUser();
    }


}

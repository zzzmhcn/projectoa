package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Messages;
import com.zmh.projectoa.service.MessageService;
import com.zmh.projectoa.service.RedisService;
import com.zmh.projectoa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public String message() {
        return "message";
    }
    /**
     * 站内信详情
     */
    @RequestMapping(value = "/message_dtl")
    public String message_dtl() {
        return "message_dtl";
    }

    /**
     * 发送站内信
     */
    @RequestMapping(value = "/send_message")
    @ResponseBody
    public ReturnDto sendMessage(@RequestParam("userIDs") String userIDs, @RequestParam("title") String title,
                                 @RequestParam("message") String message, HttpServletRequest request) {
        String userID[] = userIDs.split(",");
        Integer sendId = (Integer) request.getSession().getAttribute("userID");
        for (String receiveId : userID) {
            Messages messages = new Messages();
            messages.setSendId(sendId);
            messages.setReceiveId(Integer.parseInt(receiveId));
            messages.setTitle(title);
            messages.setMessage(message);
            int messageID = messageService.insertMessage(messages);
            String unReadMessageIDs = redisService.getValue("message_" + receiveId);
            if (Objects.isNull(unReadMessageIDs) || "null".equals(unReadMessageIDs))
                unReadMessageIDs = String.valueOf(messageID);
            else
                unReadMessageIDs += "," + messageID;
            redisService.setValue("message_" + receiveId, unReadMessageIDs);
        }
        return ReturnDto.buildSuccessReturnDto("success");
    }

    /**
     * 返回特别定制的所有用户信息给用户选择站内信发给谁
     */
    @RequestMapping(value = "/getAllUser")
    @ResponseBody
    public ReturnDto getAllUser(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("userID");
        return usersService.getAllUser(id);
    }

    /**
     * 获取本人未读信息
     */
    @RequestMapping(value = "/getUnReadMessages")
    @ResponseBody
    public ReturnDto getUnReadMessages(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("userID");
        //这里的思路是去redis取message_+'本人id'为key的value 里面包含所有未读messageID
        String unReadMessageIDs = redisService.getValue("message_" + id);
        //取出来是空直接跳过 标准格式是10,22,44 代表未读messageID
        if (!Objects.isNull(unReadMessageIDs) && !"null".equals(unReadMessageIDs)) {
            List<Integer> list = new ArrayList<>();
            for (String messsageID:unReadMessageIDs.split(",")) {
                list.add(Integer.parseInt(messsageID));
            }
            //返回一个list<map> map包括发件人姓名 和 message的 id titile
            List<Map<String, String>> selectByIDs = messageService.selectByIDs(list);
            return ReturnDto.buildSuccessReturnDto(selectByIDs);
        }
        return ReturnDto.buildFailedReturnDto("数据异常");
    }

    /**
     * 本人所有接收到的信
     */
    @RequestMapping(value = "/getMessages")
    @ResponseBody
    public ReturnDto getMessages(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("userID");
        return ReturnDto.buildSuccessReturnDto(messageService.selectByreceiveID(id));
    }

    @RequestMapping(value = "/message/{id}")
    @ResponseBody
    public ReturnDto checkMessage(@PathVariable(name="id") Integer id){
        return ReturnDto.buildSuccessReturnDto(messageService.selectByID(id));
    }
}

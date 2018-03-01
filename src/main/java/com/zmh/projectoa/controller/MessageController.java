package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Messages;
import com.zmh.projectoa.service.MessageService;
import com.zmh.projectoa.service.RedisService;
import com.zmh.projectoa.service.UsersService;
import com.zmh.projectoa.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "/message_dtl/{id}")
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
        if (!Objects.isNull(unReadMessageIDs) && !"null".equals(unReadMessageIDs) && unReadMessageIDs.length() > 0) {
            List<Integer> list = JSONUtil.String2List(unReadMessageIDs);
            //返回一个list<map> map包括发件人姓名 和 message的 id titile
            List<Map<String, String>> selectByIDs = messageService.selectByIDs(list);
            return ReturnDto.buildSuccessReturnDto(selectByIDs);
        }
        return ReturnDto.buildFailedReturnDto("数据异常");
    }

    /**
     * 设为已读
     * 从redis中剔除这条
     */
    @RequestMapping(value = "/setIsRead")
    @ResponseBody
    public ReturnDto setIsRead(@RequestParam("id") Integer messageID, HttpServletRequest request) {
        Integer userID = (Integer) request.getSession().getAttribute("userID");
        List<Integer> list = new ArrayList<>();
        String unReadMessageIDs = redisService.getValue("message_" + userID);
        //取出来是空直接跳过 标准格式是10,22,44 代表未读messageID
        if (!Objects.isNull(unReadMessageIDs) && !"null".equals(unReadMessageIDs)) {
            list = JSONUtil.String2List(unReadMessageIDs);
        }
        if(!list.contains(messageID))
            return ReturnDto.buildFailedReturnDto("这条信息不存在");
        list.remove(messageID);
        unReadMessageIDs = JSONUtil.List2String(list);
        redisService.setValue("message_" + userID, unReadMessageIDs);
        return ReturnDto.buildSuccessReturnDto("success");
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

    /**
     * 返回某一条详细信息
     * 新增判断这条信息是否属于这个人
     */
    @RequestMapping(value = "/getMessageDtl")
    @ResponseBody
    public ReturnDto checkMessage(@RequestParam("id") Integer id, HttpServletRequest request) {
        Integer userID = (Integer) request.getSession().getAttribute("userID");
        Messages message = messageService.selectByID(id);
        if(userID != null && message != null && !"".equals(userID)
                && userID.intValue() == message.getReceiveId().intValue())
            return ReturnDto.buildSuccessReturnDto(message);

        return ReturnDto.buildFailedReturnDto("没有权限");
    }

    /**
     * 传入ID 返回发件人
     */
    @RequestMapping(value = "/getSendUserName")
    @ResponseBody
    public ReturnDto getSendUserName(@RequestParam("id") Integer id) {
        return ReturnDto.buildSuccessReturnDto(usersService.detailUser(id).getRealname());
    }

    /**
     * 最后一条信息
     */
    @RequestMapping(value = "/getLastMessage")
    @ResponseBody
    public ReturnDto getLastMessage(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("userID");
        return ReturnDto.buildSuccessReturnDto(messageService.getLastMessage(id));
    }
}

package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Notices;
import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.service.NoticeService;
import com.zmh.projectoa.service.RedisService;
import com.zmh.projectoa.service.UsersService;
import com.zmh.projectoa.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zmh
 * @date 2018/2/1521:41
 * 公告板
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UsersService usersService;

    /**
     * 通知公告
     */
    @RequestMapping(value = "/notice")
    public String notice() {
        return "notice";
    }

    /**
     * 新建公告
     */
    @RequestMapping(value = "/notice_create")
    public String createNotice() {
        return "notice_create";
    }

    /**
     * 发送站内信
     * 这里接收到站内信后
     * 存数据库
     * 加入所有人的redis
     */
    @RequestMapping(value = "/send_notice")
    @ResponseBody
    public ReturnDto sendNotice(@RequestParam("title") String title, @RequestParam("notice") String notice, HttpServletRequest request) {
        Integer userID = (Integer) request.getSession().getAttribute("userID");
        Notices notices = new Notices();
        notices.setSendId(userID);
        notices.setTitle(title);
        notices.setNotice(notice);
        //这里返回的是数据库里的noticeID
        Integer noticeID = noticeService.insertNotice(notices);
        //再存入所有人的redis
        List<Users> usersList = usersService.getAllUsers();
        for (Users user : usersList) {
            List<Integer> list = JSONUtil.String2List(redisService
                    .getValue("notice_" + user.getId()));
            list.add(noticeID);
            redisService.setValue("notice_" + user.getId(), JSONUtil.List2String(list));
        }
        return ReturnDto.buildSuccessReturnDto("success");
    }

    /**
     * 获取本人未读信息
     */
    @RequestMapping(value = "/getUnReadNotices")
    @ResponseBody
    public ReturnDto getUnReadNotices(HttpServletRequest request) {
        Integer userID = (Integer) request.getSession().getAttribute("userID");
        //这里的思路是去redis取message_+'本人id'为key的value 里面包含所有未读messageID
        String unReadNotices = redisService.getValue("notice_" + userID);
        //取出来是空直接跳过 标准格式是10,22,44 代表未读messageID
        List<Integer> list = JSONUtil.String2List(unReadNotices);
        //返回一个list<map> map包括发件人姓名 和 notice id titile
        if(list != null && list.size() > 0){
            List<Map<String, String>> selectByIDs = noticeService.selectByIDs(list);
            return ReturnDto.buildSuccessReturnDto(selectByIDs);
        }
        return ReturnDto.buildFailedReturnDto("数据异常");
    }
}

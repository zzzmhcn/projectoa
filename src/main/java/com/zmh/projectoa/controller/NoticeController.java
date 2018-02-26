package com.zmh.projectoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zmh
 * @date 2018/2/1521:41
 * 公告板
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    /**
     * 通知公告
     */
    @RequestMapping(value = "/notice")
    public String notice(){
        return "notice";
    }

    /**
     * 新建公告
     */
    @RequestMapping(value = "/notice_create")
    public String createNotice(){
        return "notice_create";
    }
}

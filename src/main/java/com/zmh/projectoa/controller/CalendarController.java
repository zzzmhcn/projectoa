package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalendarController {
    @Autowired
    private RedisService redisService;
    /**
     * 日历
     */
    @RequestMapping(value = "/calendar")
    public String calendar(){
        return "calendar";
    }

    /**
     * 缓存日志备注
     * key是 calendar加userID
     * value是 JSON格式备注
     */
    @RequestMapping(value = "/calendarSetValue")
    @ResponseBody
    public ReturnDto calendarSetValue(@RequestParam("key")String key,@RequestParam("value")String value){

        redisService.setValue(key, value);
        return ReturnDto.buildSuccessReturnDto();
    }

    /**
     * 获取日志备注
     * key是 calendar加userID
     * return是 JSON格式备注
     */
    @RequestMapping(value = "/calendarGetValue")
    @ResponseBody
    public ReturnDto calendarGetValue(@RequestParam("key")String key){
        String value = redisService.getValue(key);
        if(value != null){
            return ReturnDto.buildSuccessReturnDto(value);
        }else{
            return ReturnDto.buildFailedReturnDto("value is null");
        }
    }

}

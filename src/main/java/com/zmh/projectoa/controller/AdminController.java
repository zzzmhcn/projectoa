package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.util.ReadFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zmh
 * @date 2018/2/18 16:24
 * 在页面标签栏里显示日志文件
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    //这两个参数从application.properties获取
    @Value("${logback.filepath}")
    private String filePath;
    @Value("${logback.charset}")
    private String charSet;

    /**
     * 获取所有日志文件的文件名
     * @return
     */
    @RequestMapping(value = "/getFileNames")
    @ResponseBody
    public ReturnDto getFileNames(){
        return ReadFileUtil.getFileName(filePath);
    }

    /**
     * 获取所有日志文件的文件名
     * @return
     */
    @RequestMapping(value = "/readFiles")
    @ResponseBody
    public ReturnDto readFiles(@RequestParam("fileName")String fileName){
        return ReadFileUtil.readFileByLines(filePath,fileName,charSet);
    }


    /**
     * 日志
     */
    @RequestMapping(value = "/logs")
    public String logs(){
        return "logs";
    }
    /**
     * 系统监控
     */
    @RequestMapping(value = "/springbootadmin")
    public String springbootadmin(){
        return "springbootadmin";
    }
}

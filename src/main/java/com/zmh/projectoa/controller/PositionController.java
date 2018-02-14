package com.zmh.projectoa.controller;

import com.zmh.projectoa.dto.ReturnDto;
import com.zmh.projectoa.model.Positions;
import com.zmh.projectoa.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ChengShanyunduo
 * 2018/2/14
 */
@Controller
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    /**
     * 查询职位
     * @return
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ReturnDto queryPosition(){
        List<Positions> positions = positionService.queryPosition();
        return ReturnDto.buildSuccessReturnDto(positions);
    }
}

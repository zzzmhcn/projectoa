package com.zmh.projectoa.service;

import com.zmh.projectoa.mapper.PositionsMapper;
import com.zmh.projectoa.model.Positions;
import com.zmh.projectoa.model.PositionsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ChengShanyunduo
 * 2018/2/14
 */
@Service
public class PositionService {

    @Autowired
    PositionsMapper positionsMapper;

    /**
     * 查询职位
     * @return
     */
    public List<Positions> queryPosition(){
        PositionsExample positionsExample = new PositionsExample();
        List<Positions> positions = positionsMapper.selectByExample(positionsExample);
        return positions;
    }
}

package com.zmh.projectoa.service;


import com.zmh.projectoa.mapper.NoticesMapper;
import com.zmh.projectoa.model.Notices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zmh
 * @date 2018/2/2621:58
 */
@Service
public class NoticeService {
    @Autowired
    private NoticesMapper noticesMapper;

    public int insertNotice(Notices notices){
        noticesMapper.insertSelective(notices);
        return notices.getId();
    }

    /**
     * 获取具体某条公告
     */
    public Notices getNotice(Integer noticeID){
        return noticesMapper.selectByPrimaryKey(noticeID);
    }

    /**
     * 获取本人所有公告
     * 这个功能不分人 大家都收到一样
     */
    public List<Notices> getAllNotices(){
        return noticesMapper.selectAllNotice();
    }

    /**
     * 传入未读ID数组 返回未读信息 (Redis里含有的ID再Mysql中)
     */
    public List<Map<String,String>> selectByIDs(List<Integer> IDs){
        return noticesMapper.selectByIDs(IDs);
    }
}

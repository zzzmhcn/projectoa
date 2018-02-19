package com.zmh.projectoa.cache;

import java.util.Set;

/**
 * @author zmh
 * @date 2018/2/1912:00
 */
public class RedisCache {
    /**
     * 用户ID 作为存和读的唯一标示
     */
    private Integer userId;
    /**
     * 未读站内信ID 作为通知未读信息使用
     */
    private Set<String> unReadMessagesId;
    /**
     * 未读公告ID 功能同上
     */
    private Set<String> unReadNoticesId;


    public RedisCache() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<String> getUnReadMessagesId() {
        return unReadMessagesId;
    }

    public void setUnReadMessagesId(Set<String> unReadMessagesId) {
        this.unReadMessagesId = unReadMessagesId;
    }

    public Set<String> getUnReadNoticesId() {
        return unReadNoticesId;
    }

    public void setUnReadNoticesId(Set<String> unReadNoticesId) {
        this.unReadNoticesId = unReadNoticesId;
    }
}

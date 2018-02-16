package com.zmh.projectoa.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zmh
 * @date 2018/2/1610:49
 */
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

}

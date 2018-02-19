package com.zmh.projectoa.util;


import com.zmh.projectoa.cache.RedisCache;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zmh
 * @date 2018/2/1610:49
 */
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    public void testObj() throws Exception{
        RedisCache redisCache = new RedisCache();
        Set<String> set = new HashSet<>();
        set.add("0");
        set.add("1");
        set.add("2");
        redisCache.setUnReadMessagesId(set);
        redisCache.setUnReadNoticesId(set);
        redisCache.setUserId(0);

    }

}

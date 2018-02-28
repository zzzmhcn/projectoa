package com.zmh.projectoa.util;

import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    /**
     * 传入Redis里未读ID的String 返回List
     * 默认格式 1,2,3,4
     */
    public static List<Integer> String2List(String string) {
        List<Integer> list = new ArrayList<>();
        if (string != null && !"".equals(string))
            for (String temp : string.split(",")) {
                if (temp != null && !"".equals(temp))
                    list.add(Integer.parseInt(temp));
            }
        return list;
    }

    /**
     * 传入Redis里未读ID的String 返回List
     */
    public static String List2String(List<Integer> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            Integer integer = list.get(i);
            sb.append(integer);
            if (i != list.size() - 1)
                sb.append(",");
        }
        return sb.toString();
    }
}

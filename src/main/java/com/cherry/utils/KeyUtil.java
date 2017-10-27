package com.cherry.utils;

import java.util.Random;

/**
 * 生成随机码
 * Created by Administrator on 2017/10/21.
 */
public class KeyUtil {


    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000; //生成6位随机数

        return System.currentTimeMillis() + String.valueOf(number);
        //考虑到多线程同步 还需做synchronized处理
    }
}

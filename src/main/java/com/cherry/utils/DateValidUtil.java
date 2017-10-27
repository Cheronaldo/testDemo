package com.cherry.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 检测验证码是否在有效期内
 * Created by Administrator on 2017/10/24.
 */
public class DateValidUtil {

    public static boolean ifDateValid(String genTime){
        boolean b =false;

        Date date = null;


        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);

        try{
            date = sdf.parse(genTime);
            Long oldTime = date.getTime();
            Long currentTime = System.currentTimeMillis();
            //有效期设置为30分钟
            if(currentTime - oldTime <= 1800000){
                b = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return b;
    }

}

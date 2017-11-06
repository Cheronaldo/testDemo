package com.cherry.utils;

/**
 * Created by Administrator on 2017/10/31.
 */
public class SqlUtil {

    public static synchronized String genSql(){

        String snCode = SqlDomain.getSnCode();
        String regCode = SqlDomain.getRegCode();

        String genSql = "select * from device_verify where 1=1";

        if(snCode != null && snCode != ""){
            genSql += "AND sn_code='"+snCode +"'";
        }
        if(regCode != null && regCode !=""){
            genSql += "AND reg_code='" + regCode +"'";
        }
        return genSql;


    }


}

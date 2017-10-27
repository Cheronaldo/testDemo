package com.cherry.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/10/24.
 */
@Entity
public class DeviceVerify {

    @Id
    private String id;  //主键 由系统生成（时间戳+6位随机数）

    private String snCode; //设备SN码

    private String regCode; //校验码

    private String genTime; //校验码生成时间



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getGenTime() {
        return genTime;
    }

    public void setGenTime(String genTime) {
        this.genTime = genTime;
    }
}

package com.cherry.enums;

/**
 * 设备状态
 * Created by Administrator on 2017/10/21.
 */
public enum DeviceStatusEnum {

    ON(0,"在线"),
    OFF(1,"离线")
    ;

    private  Integer code;
    private String message;

    DeviceStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

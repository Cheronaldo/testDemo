package com.cherry.domain;

import com.cherry.enums.DeviceStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/10/21.
 */
@Entity
public class DeviceInfo {

    @Id
    private String snCode;          //设备SN码

    private String deviceOwner;     //设备所属用户

    private String deviceType;     //设备型号

    private Integer deviceStatus = DeviceStatusEnum.OFF.getCode();   //设备状态  0 表示在线 默认为 1（离线）

    private String deviceAddress;   //设备部署地址

    private String deviceLng;       //设备经度

    private String deviceLat;       //设备纬度

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getDeviceOwner() {
        return deviceOwner;
    }

    public void setDeviceOwner(String deviceOwner) {
        this.deviceOwner = deviceOwner;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getDeviceLng() {
        return deviceLng;
    }

    public void setDeviceLng(String deviceLng) {
        this.deviceLng = deviceLng;
    }

    public String getDeviceLat() {
        return deviceLat;
    }

    public void setDeviceLat(String deviceLat) {
        this.deviceLat = deviceLat;
    }
}

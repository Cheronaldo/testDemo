package com.cherry.dto;

/**
 * 返回给前端的字段并不是 DeviceInfo 中的所有字段，需进行对象转换
 * Created by Administrator on 2017/10/21.
 */
public class DeviceDTO {

    private String deviceType;     //设备型号

    private Integer deviceStatus;   //设备状态  0 表示在线 默认为 1（离线）

    private String deviceAddress;   //设备部署地址

    private String deviceLng;       //设备经度

    private String deviceLat;       //设备纬度

    public DeviceDTO(){}

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

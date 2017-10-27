package com.cherry.service;

import com.cherry.domain.DeviceInfo;
import com.cherry.domain.DeviceVerify;
import com.cherry.dto.DeviceDTO;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */
public interface DeviceInfoService {

    //设备注册
    DeviceInfo deviceRegister(DeviceInfo deviceInfo);

    //通过用户身份查询设备列表 并转换为DeviceDTO list 返回给前端
    List<DeviceDTO> findByDeviceOwner(String deviceOwner);

    //通过snCode查询最新一条记录
    DeviceVerify findLatestBySnCode(String snCode);

}

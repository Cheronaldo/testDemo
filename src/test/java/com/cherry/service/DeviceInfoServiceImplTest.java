package com.cherry.service;

import com.cherry.domain.DeviceInfo;
import com.cherry.domain.DeviceVerify;
import com.cherry.dto.DeviceDTO;
import com.cherry.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/10/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceInfoServiceImplTest {

    @Autowired
    private DeviceInfoServiceImpl deviceInfoService;

    @Test
    public void deviceRegister() throws Exception {

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setSnCode(KeyUtil.genUniqueKey());//获取随机的SN码
        deviceInfo.setDeviceOwner("中国移动");
        deviceInfo.setDeviceType("HMI");
        deviceInfo.setDeviceAddress("武汉大学");
        deviceInfo.setDeviceLng("114.368107");
        deviceInfo.setDeviceLat("30.543083");

        DeviceInfo result = deviceInfoService.deviceRegister(deviceInfo);
        Assert.assertNotNull(result);


    }

    @Test
    public void findByDeviceOwner() throws Exception {
        List<DeviceDTO> result = deviceInfoService.findByDeviceOwner("亿维自动化");
        Assert.assertNotEquals(0,result.size());

    }

    @Test
    public void findLatestOne(){
        DeviceVerify result = deviceInfoService.findLatestBySnCode("1509435289989129179");
        Assert.assertNotNull(result);
    }

}
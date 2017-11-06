package com.cherry.repository;

import com.cherry.domain.DeviceInfo;
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
public class DeviceInfoRepositoryTest {

    @Autowired
    private DeviceInfoRepository repository;

    @Test
    public void addDevice(){

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setSnCode(KeyUtil.genUniqueKey());//获取随机的SN码
        deviceInfo.setDeviceOwner("中国移动");
        deviceInfo.setDeviceType("HMI");
        deviceInfo.setDeviceAddress("武汉市武昌火车站");
        deviceInfo.setDeviceLng("114.324393");
        deviceInfo.setDeviceLat("30.534197");

        DeviceInfo result = repository.save(deviceInfo);

        Assert.assertNotNull(result);

    }

    @Test
    public void findTopByDeviceOwner(){
        DeviceInfo result = repository.findTopByDeviceOwner("亿维自动化");
        Assert.assertNotNull(result);
    }


    @Test
    public void findFirstByDeviceOwner(){
        DeviceInfo result = repository.findFirstByDeviceOwner("亿维自动化");
        Assert.assertNotNull(result);
    }

    @Test
    public void findLatestOne(){
        DeviceInfo result = repository.findLatestOne("亿维自动化");
        Assert.assertNotNull(result);
    }

//    @Test
//    public void findByDeviceOwnerOrderBySnCodeDesc(){
//        DeviceInfo result = repository.findByDeviceOwnerOrderBySnCodeDesc("亿维自动化");
//        Assert.assertNotNull(result);
//    }


    @Test
    public void findByDeviceOwner() throws Exception {

        List<DeviceInfo> result = repository.findByDeviceOwner("亿维自动化");
        Assert.assertNotEquals(0,result.size());

    }



}
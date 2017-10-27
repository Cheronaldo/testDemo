package com.cherry.controller;

import com.alibaba.fastjson.JSONObject;
import com.cherry.domain.DeviceInfo;
import com.cherry.domain.DeviceVerify;
import com.cherry.dto.DeviceDTO;
import com.cherry.service.DeviceInfoService;
import com.cherry.utils.DateValidUtil;
import com.cherry.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/21.
 */
@RestController
@RequestMapping("/device")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    /**
     * 设备注册
     * @param deviceAddress
     * @param deviceLng
     * @param deviceLat
     * @throws IOException
     */
    @PostMapping("/register")
    public Map<String,String> deviceRegister(
                              @RequestParam("snCode") String snCode,
                              @RequestParam("regCode") String regCode,
                              @RequestParam("deviceAddress") String deviceAddress,
                              @RequestParam("deviceLng") String deviceLng,
                              @RequestParam("deviceLat") String deviceLat) throws IOException {

        //目前测试就只传这几个字段，后期字段多了，可使用表单验证处理
        Map<String,String> map = new HashMap<>();

        //1.验证sn码是否有对应的校验码存在
            DeviceVerify deviceVerify = deviceInfoService.findLatestBySnCode(snCode);
            if(deviceVerify != null){
                //2.验证校验码是否一致
                if(regCode.equals(deviceVerify.getRegCode())){
                    //3.是否在有效期内
                    if(DateValidUtil.ifDateValid(deviceVerify.getGenTime())){

                        DeviceInfo deviceInfo = new DeviceInfo();
                        deviceInfo.setSnCode(snCode);
                        deviceInfo.setDeviceOwner("亿维自动化");
                        deviceInfo.setDeviceType("HMI");
                        deviceInfo.setDeviceAddress(deviceAddress);
                        deviceInfo.setDeviceLng(deviceLng);
                        deviceInfo.setDeviceLat(deviceLat);

                        try {
                            deviceInfoService.deviceRegister(deviceInfo);
                            //设备注册成功
                            map.put("result","0");
                        }catch (Exception e){
                            //后期可自定义异常类
                            e.printStackTrace();
                            //设备注册失败
                            map.put("result","4");
                        }
                    }else {
                        //校验码过期
                        map.put("result","3");
                    }


                }else {
                    //校验码错误
                    map.put("result","2");
                }


            }else {
                //该设备不存在或未在设备上生成校验码
                map.put("result","1");
            }


        return map;
    }

    /**
     * 通过用户身份查找设备列表
     * @param deviceOwner
     * @return
     */
    @PostMapping("/list")
    public Object list(@RequestParam(value = "deviceOwner",defaultValue = "亿维自动化") String deviceOwner){

        JSONObject result = new JSONObject();

        List<DeviceDTO> deviceDTOList = deviceInfoService.findByDeviceOwner(deviceOwner);

        result.put("result",deviceDTOList);
        return result;


       // return deviceInfoService.findByDeviceOwner(deviceOwner);


//        Map<String,Object> map = new HashMap<>();
//        map.put("result","0");
//        return map;

    }


}

package com.cherry.service;

import com.cherry.converter.DeviceInfo2DeviceDTOConverter;
import com.cherry.domain.DeviceInfo;
import com.cherry.domain.DeviceVerify;
import com.cherry.dto.DeviceDTO;
import com.cherry.repository.DeviceInfoRepository;
import com.cherry.repository.DeviceVerifyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */
@Service
@Slf4j
public class DeviceInfoServiceImpl implements DeviceInfoService{

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Autowired
    private DeviceVerifyRepository deviceVerifyRepository;

    @Override
    public DeviceInfo deviceRegister(DeviceInfo deviceInfo) {
        return deviceInfoRepository.save(deviceInfo);
    }

    @Override
    public List<DeviceDTO> findByDeviceOwner(String deviceOwner) {

        List<DeviceInfo> deviceInfoList = deviceInfoRepository.findByDeviceOwner(deviceOwner);

        List<DeviceDTO> deviceDTOList = DeviceInfo2DeviceDTOConverter.convert(deviceInfoList);

        return  deviceDTOList;

    }

    @Override
    public DeviceVerify findLatestBySnCode(String snCode) {
        return deviceVerifyRepository.findLatestBySnCode(snCode);
    }
}

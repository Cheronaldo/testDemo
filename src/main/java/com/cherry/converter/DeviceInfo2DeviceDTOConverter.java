package com.cherry.converter;

import com.cherry.domain.DeviceInfo;
import com.cherry.dto.DeviceDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用于需要将 DeviceInfo list对象  转换为 DeviceDTO list对象
 * Created by Administrator on 2017/10/21.
 */
public class DeviceInfo2DeviceDTOConverter {

    public static DeviceDTO convert(DeviceInfo deviceInfo){

        DeviceDTO deviceDTO = new DeviceDTO();
        BeanUtils.copyProperties(deviceInfo,deviceDTO);
        return deviceDTO;

    }

    public static List<DeviceDTO> convert(List<DeviceInfo> deviceInfoList){

        //Java8 lambda表达式
        return  deviceInfoList.stream().map(e ->
                convert(e))
                .collect(Collectors.toList());

    }


}

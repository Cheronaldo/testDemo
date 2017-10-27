package com.cherry.repository;

import com.cherry.domain.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo,String>{

    //通过设备所属用户来查询该用户下的所有设备
    List<DeviceInfo> findByDeviceOwner(String deviceOwner);

    //查询的是第一条
    DeviceInfo findTopByDeviceOwner(String deviceOwner);

    //查询的是第一条
    DeviceInfo findFirstByDeviceOwner(String deviceOwner);

//    @Query("select * from device_info WHERE device_owner=:deviceOwner ORDER BY sn_code DESC ")
//    DeviceInfo findByDeviceOwnerOrderBySnCodeDesc(@Param("deviceOwner") String deviceOwner);

    //查询的是最后一条
    @Transactional
    @Query(value = "select * from device_info where device_info.device_owner = ?1 order by device_info.sn_code desc limit 1",nativeQuery = true)
    DeviceInfo findLatestOne(String deviceOwner);


}

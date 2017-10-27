package com.cherry.repository;

import com.cherry.domain.DeviceVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface DeviceVerifyRepository extends JpaRepository<DeviceVerify,String>{

    //通过snCode查询最新一条记录
    //device_verify.id 加不加前面的  device_verify. 结果都一样 加上更好 后面再深入研究
    @Transactional
    @Query(value = "select * from device_verify where device_verify.sn_code = ?1 order by device_verify.id desc limit 1",nativeQuery = true)
    DeviceVerify findLatestBySnCode(String snCode);


}

package com.cherry.repository;

import com.cherry.domain.DeviceVerify;
import com.cherry.utils.KeyUtil;
import com.cherry.utils.SqlUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface DeviceVerifyRepository extends JpaRepository<DeviceVerify,String>{

    //通过snCode查询最新一条记录
    //device_verify.id 加不加前面的  device_verify. 结果都一样 加上更好 后面再深入研究
    String sql = "select * from device_verify where device_verify.sn_code = ?1 order by device_verify.id desc limit 1";

    @Transactional
    @Query(value = sql,nativeQuery = true)
    DeviceVerify findLatestBySnCode(String snCode);

    //查询记录数
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM device_verify WHERE device_verify.sn_code = ?1",nativeQuery = true)
    int findNumberBySnCode(String snCode);


    //String s1 = "1509435289989129179";
    //String sql1 = "SELECT * FROM device_verify WHERE device_verify.sn_code = '"+s1+"'";
    String sql2 = SqlUtil.genSql();

    //多条件查询
    @Transactional
    @Query(value =sql ,nativeQuery = true)
    List<DeviceVerify> findListBySnCodeAndRegCode();
//    List<DeviceVerify> findListBySnCodeAndRegCode(String snCode,String regCode);

}

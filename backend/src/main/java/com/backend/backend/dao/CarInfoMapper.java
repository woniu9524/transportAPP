package com.backend.backend.dao;

import com.backend.backend.entity.CarInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CarInfoMapper {
    int deleteByPrimaryKey(String carId);

    int insert(CarInfo record);

    CarInfo selectByPrimaryKey(String carId);

    List<CarInfo> selectAll();
    @Select("select * from car_info where car_owner = #{userId}")
    List<CarInfo> selectAllByUserId(String userId);

    int updateByPrimaryKey(CarInfo record);
}
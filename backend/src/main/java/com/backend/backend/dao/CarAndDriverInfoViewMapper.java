package com.backend.backend.dao;

import com.backend.backend.entity.CarAndDriverInfoView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CarAndDriverInfoViewMapper {
    int insert(CarAndDriverInfoView record);

    List<CarAndDriverInfoView> selectAll();

    @Select("select * from car_and_driver_info_view where driver_id = #{userId}")
    List<CarAndDriverInfoView> selectAllByUserId(String userId);
}
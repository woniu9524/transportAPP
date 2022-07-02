package com.backend.backend.dao;

import com.backend.backend.entity.CargoOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CargoOrderMapper {
    int deleteByPrimaryKey(String contractId);

    int insert(CargoOrder record);

    CargoOrder selectByPrimaryKey(String contractId);
    @Select("select * from cargo_order where car_id = #{carId}")
    List<CargoOrder> selectByCarId(String carId);

    List<CargoOrder> selectAll();

    int updateByPrimaryKey(CargoOrder record);
}
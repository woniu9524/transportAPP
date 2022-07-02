package com.backend.backend.dao;

import com.backend.backend.entity.CargoOrderView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CargoOrderViewMapper {
    int insert(CargoOrderView record);

    List<CargoOrderView> selectAll();
}
package com.backend.backend.service.impl;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.dao.CarAndDriverInfoViewMapper;
import com.backend.backend.dao.CarInfoMapper;
import com.backend.backend.dao.CargoOrderMapper;
import com.backend.backend.entity.CarInfo;
import com.backend.backend.entity.CargoOrder;
import com.backend.backend.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    @Autowired
    private CarAndDriverInfoViewMapper carAndDriverInfoViewMapper;
    @Autowired
    private CargoOrderMapper cargoOrderMapper;
    @Override
    public CommonResult<?> getCarInfo(String userId) {
        try {
            return CommonResult.success(carAndDriverInfoViewMapper.selectAll());
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.failed("获取车辆信息失败");
        }
    }

    @Override
    public CommonResult<?> updateOrder(CargoOrder cargoOrder) {
        //查询订单是否存在
        CargoOrder cargoOrder1 = cargoOrderMapper.selectByPrimaryKey(cargoOrder.getContractId());
        //如果不存在，则插入订单
       try {
           if(cargoOrder1 == null){
               cargoOrderMapper.insert(cargoOrder);
           }else{
               cargoOrderMapper.updateByPrimaryKey(cargoOrder);
           }
           return CommonResult.success("提交成功");
       }catch (Exception e){
           return CommonResult.failed("提交报表失败");
       }
    }

    @Override
    public CommonResult<?> getOrder(String carId) {
        try {
            List<CargoOrder> cargoOrder = cargoOrderMapper.selectByCarId(carId);
            return CommonResult.success(cargoOrder);
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.failed("获取失败");
        }

    }
}


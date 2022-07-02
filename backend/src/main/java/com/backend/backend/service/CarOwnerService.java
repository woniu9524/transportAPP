package com.backend.backend.service;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.entity.CargoOrder;

public interface CarOwnerService {
    CommonResult<?> getCarInfo(String userId);

    CommonResult<?> updateOrder(CargoOrder cargoOrder);

    CommonResult<?> getOrder(String carId);
}

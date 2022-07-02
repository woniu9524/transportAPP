import request from "../../utils/request";
// 获取车辆信息验
export const getCarInfo = (data) => request({ url: '/carInfo', method: "get", data, })
//提交报表
export const updateOrder = (data) => request({ url: '/updateOrder', method: "post", data, })
//获取报表
export const getOrder = (data) => request({ url: '/getOrder', method: "post", data, })

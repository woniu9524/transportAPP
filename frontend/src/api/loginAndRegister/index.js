import request from "../../utils/request";
// 登录验证接口
export const addLogin = (data) => request({ url: '/login', method: "post", data, })
//注册接口
export const addUser = (data) => request({ url: '/register', method: "post", data, })
export const isLogin = (data) => request({ url: '/isLogin', method: "get", data, })

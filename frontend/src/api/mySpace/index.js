import request from "../../utils/request";
// 上传头像
export const addHeadPhoto = (data) => request({ url: '/headPhoto', method: "post", data, })
//加载头像
export const loadHeadPhoto = (data) => request({ url: '/headPhoto', method: "get", data, })
//获取个人信息
export const getUserInfo = (data) => request({ url: '/userInfo', method: "get", data, })
//修改个人信息
export const updateUserInfo = (data) => request({ url: '/userInfo', method: "post", data, })



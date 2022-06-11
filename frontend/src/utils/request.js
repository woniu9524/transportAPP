import axios from "axios";
const request= axios.create({
   baseURL:"http://localhost:8000",
    //TODO 请求拦截

    //TODO 响应拦截
});

export default request;

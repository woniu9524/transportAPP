import axios from 'axios';
import Toast from 'vant/lib/toast';

//根据环境变量区分baseURL
let baseURL = "";
switch (process.env.NODE_ENV) {
    case "production":
        baseURL = "http://47.96.114.92:8080";
        // baseURL = "http://localhost:8080";
        break;
    case "test":
        baseURL = "http://localhost:8080";
        break;
    default:
        baseURL = "http://localhost:8080";
}
const instance = axios.create({
    baseURL: baseURL,
    // timeout:5000,
    withCredentials: false
});


// request interceptor
instance.interceptors.request.use(
    (config) => {
        // do something before request is sent
        config.headers = {
            ...config.headers,
            "Access-Control-Allow-Origin": "*",
        };
        let token = localStorage.getItem("token");//读取token
        if (token) {
            // let each request carry token
            config.headers = {
                ...config.headers,
                token: token
            };
        }

        return config;
    },
    (error) => {
        // do something with request error
        console.log(error); // for debug
        return Promise.reject(error);
    },
);

// response interceptor
instance.interceptors.response.use(
    /**
     * If you want to get http information such as headers or status
     * Please return  response => response
     */

    /**
     * Determine the request status by custom code
     * Here is just an example
     * You can also judge the status by HTTP Status Code
     */
    (response) => {
        const res = response.data;
        // if the custom code is not 200, it is judged as an error.
        if (res.code !== 200) {
            // 412: Token expired;
            // if (res.code === 412) {
            //     // store.dispatch('user/userLogout');
            // }
            // return Promise.reject(res.message || 'Error');
            return res;
        } else {
            //这个是登录时候会返回token，保存起来
            if (res.data.token){
                localStorage.setItem("token", res.data.token);
            }

            return res;
        }
    },
    (error) => {
        console.log('err' + error);
        // Toast(error.message);
        return Promise.reject(error.message);
    },
);

function request({method = "get", url, data = {}, params = {}}) {
    return instance({
        method,
        url,
        data,
        params,
    })
}

export default request


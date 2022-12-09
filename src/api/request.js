//二次封装axios
import axios from "axios"
//引入进度条插件  start开始 done结束
import nprogress from "nprogress";
//引入进度条样式
import "nprogress/nprogress.css"


let request=axios.create({
    baseURL:'http://127.0.0.1',
    timeout:5000,
})

//添加请求拦截器
request.interceptors.request.use(config=>{
    // 在发送请求之前做些什么
    nprogress.start();
     return config;
},err=>{
    // 对请求错误做些什么
    return new Promise.reject(err);
})

//添加响应拦截器
request.interceptors.response.use(res=>{
    // 对响应数据做点什么
    nprogress.done();
    return res;
},err=>{
    alert(err.message);
    return new Promise.reject(err);
})

export default request;
//配置echart的map
import axios from "axios"
//引入进度条插件  start开始 done结束
import nprogress from "nprogress";
//引入进度条样式
import "nprogress/nprogress.css"
let requests=axios.create({
    timeout:1000,
});
// 添加请求拦截器
requests.interceptors.request.use((config)=> {
    // 在发送请求之前做些什么
    nprogress.start();
    return config;
  },(error)=> {
    // 对请求错误做些什么
    return Promise.reject(error);
  });
// 添加响应拦截器
requests.interceptors.response.use((response)=> {
    // 对响应数据做点什么
    nprogress.done();
    return response;
  }, (error)=> {
    // 对响应错误做点什么
    return Promise.reject(error);
  });

  export default requests;
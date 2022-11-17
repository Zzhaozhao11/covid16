import Vue from 'vue'
import App from './App.vue'
Vue.config.productionTip = false
//引入vuex
import store from './store';
//引入vuerouter
import VueRouter from 'vue-router'
import router from "./router/index"
Vue.use(VueRouter);
//引入并使用ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import { Loading } from 'element-ui';
Vue.use(ElementUI);
//引入hover.css
import "hover.css"
//引入animate.css
import animate from "animate.css"
Vue.use(animate);
//引入echarts
import * as echarts from "echarts";
//引入封装axios的api
import * as http from "@/api";

new Vue({
   //配置全局事件总线
   beforeCreate() {
    //配置全局事件总线
    Vue.prototype.$bus = this;
    //通过Vue.prototype原型对象,将全部请求函数挂载到原型对象身上[VC:就可以使用请求函数]
    Vue.prototype.$http = http;
    //在vue原型上绑定vcharts
    Vue.prototype.$echarts=echarts;
    //element
    Vue.prototype.$element=ElementUI;
  },
  router,
  store,
  render: h => h(App),
}).$mount('#app')

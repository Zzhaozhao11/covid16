//引入vuerouter
import VueRouter from 'vue-router';
//引入路由信息
import routes from './routes';
//引入elementui消息提示
import { Message } from 'element-ui';
//引入vuex仓库
import store from '@/store';
//重写push/replace
let originPush=VueRouter.prototype.push;
let originReplace=VueRouter.prototype.replace;
VueRouter.prototype.push=function(location,resolve,reject){
    if(resolve&&reject){
        originPush.call(this,location,resolve,reject);
    }else{
        originPush.call(this,location,()=>{},()=>{});
    }
}
VueRouter.prototype.replace=function(location,resolve,reject){
    if(resolve&&reject){
        originReplace.call(this,location,resolve,reject);
    }else{
        originReplace.call(this,location,()=>{},()=>{});
    }
}
const router=new VueRouter({
    routes
})
//全局路由守卫
router.beforeEach((to,from,next)=>{
    let topath=to.path;
    if(topath.includes('message')){//首页直接放行
        next();
        return;
    }   
    let IsLogin=store.state.myself.IsLogin;
    if(!IsLogin&&(topath.includes('myself')||topath.includes('admin'))){  //没有登录时
        Message.error('请先登录');
        return;
    }
    let power=store.state.myself.power;  //权限
    if(power=='超级管理员'){  //超级管理员直接放行
        next();
    }else if(power=='管理员'&&!topath.includes('adminsystem')){  //普通管理员
         next();
    }else if(power=='普通用户'&&topath.includes('myself')){
        next();
    }else
    Message.error('权限不足!');
})
//对外暴露
export default router;
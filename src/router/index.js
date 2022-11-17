//引入vuerouter
import VueRouter from 'vue-router'

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

export default new VueRouter({
    routes:[
        {
           path:'/',
           redirect:'/message'
        },
        {
            path: '/message',
            redirect: '/message/china'
        },
        {
            path:'/message',
            name:'message',
            component:()=>import('../pages/message'),
            children:[
               {
                path:'world',
                name:'world',
                component:()=>import('../pages/message/world')
               },
               {
                path:'china',
                name:'china',
                component:()=>import('../pages/message/china')
               },
               {
                path:'tianjin',
                name:'tianjin',
                component:()=>import('../pages/message/tianjin')
               }
            ],
        },
        {
            path:'/myself',
            name:'myself',
            component:()=>import('../pages/myself')
        },
        {
            path:'/set',
            name:'set',
            component:()=>import('../pages/set')
        },
        {
            path:'/adminquezhen',
            name:'quezhen',
            component:()=>import('../pages/admin/manage/quezhen')
        },
        {
            path:'/adminzhiyu',
            name:'zhiyu',
            component:()=>import('../pages/admin/manage/zhiyu')
        },
        {
            path:'/adminsiwang',
            name:'siwang',
            component:()=>import('../pages/admin/manage/siwang')
        },
        {
            path:'/adminmijie',
            name:'mijie',
            component:()=>import('../pages/admin/manage/mijie')
        },
        {
            path:'/adminsystem',
            name:'system',
            component:()=>import('../pages/admin/manage/system')
        },
        {
            path:'/admincard',
            name:'cardmessage',
            component:()=>import('../pages/admin/cardmessage')
        },

    ]
})
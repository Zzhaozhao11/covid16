//引入验证token的接口
import {ReqVerifyToken,ReqLogin,ReqSelectByName} from '@/api/mod/login'

export default {
    namespaced:true,
    actions:{
      async VerifyToken({commit,state,dispatch}){  //验证token的方法
         if(!state.token){
            return Promise.reject(new Error('没有token'));
         }
          let res=await ReqVerifyToken(state.token);
          if(res.data.code!=200){
            return Promise.reject(new Error('token错误'));
          }
          let power=res.data.data.权限;
          let name=res.data.data.用户名;
          //成功的情况
         commit('SETPOWER',power); //设置权限
         commit('SUCCESSLOGIN');  //改变登录状态
         commit('SETUSERNAME', name);   //向xuex仓库发送用户名
      },

      async Login({commit,state,dispatch},{name,password}){  //登录方法
         const res = await ReqLogin(name, password);  //axios发送登录请求
         if(res.data.code!=200)
         return Promise.reject(new Error('账号或密码错误！'));
         commit('SETTOKEN',res.data.data);
         await dispatch('VerifyToken');
      }
    },
    mutations:{
       CHANGECARD(state){
          state.ShowCard=!state.ShowCard;
       },
       CHANGELOGIN(state){
         state.IsLogin=!state.IsLogin;
       },
       SUCCESSLOGIN(state){
         state.IsLogin=true;
       },
       SETUSERNAME(state,val){
          state.Username=val;
       },
       SETPOWER(state,val){  
         state.power=val;
       },
       EXITLOGIN(state){  //退出登录
         localStorage.removeItem('TOKEN');
         state.IsLogin=false;
         state.power='';
         state.Username='';
         state.token='';
       },
       SETTOKEN(state,val){  //设置token
         localStorage.setItem('TOKEN',val);
         state.token=val;
       }
    },
    state:{
       ShowCard:false, //是否显示打卡界面
       IsLogin:false, //是否登录成功
       power:'',  //权限
       Username:'',  //用户姓名
       token:localStorage.getItem('TOKEN')||'',  //token
    },
    getters:{
       
    }
}
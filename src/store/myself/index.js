export default {
    namespaced:true,
    actions:{

    },
    mutations:{
       CHANGECARD(state){
          state.ShowCard=!state.ShowCard;
       },
       CHANGELOGIN(state){
         state.IsLogin=!state.IsLogin;
       },
       SETUSERNAME(state,val){
          state.Username=val;
       },
       SETPOWER(state,val){
         state.power=val;
       },
       EXITLOGIN(state){
         state.IsLogin=false;
         state.power='';
         state.Username='';
       }
    },
    state:{
       ShowCard:false, //是否显示打卡界面
       IsLogin:false, //是否登录成功
       power:'',  //权限
       Username:'',  //用户姓名
    },
    getters:{
       
    }
}
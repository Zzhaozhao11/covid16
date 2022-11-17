export default {
    namespaced:true,
    actions:{

    },
    mutations:{
       CHANGECARD(state){
          state.ShowCard=!state.ShowCard;
       }
    },
    state:{
       ShowCard:false, //是否显示打卡界面
    },
    getters:{
       
    }
}
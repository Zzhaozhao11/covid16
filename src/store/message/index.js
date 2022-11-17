import {reqGetWorldMap,reqGetTianjinMap,reqGetChinaMap} from "@/api"
export default {
    namespaced:true,
    actions:{
      async  SetWroldMap({ state, dispatch, commit },val){
           if(Object.keys(state.WorldMap)!=0)
           return;
           let res=await reqGetWorldMap();
           commit('SETWORLDMAP',res.data);    
       },
       async  SetTianjinMap({ state, dispatch, commit },val){
         if(Object.keys(state.TianjinMap)!=0)
         return;
         let res=await reqGetTianjinMap();
         commit('SETTIANJINMAP',res.data);    
     },
     async  SetChinaMap({ state, dispatch, commit },val){
      if(Object.keys(state.ChinaMap)!=0)
      return;
      let res=await reqGetChinaMap();
      commit('SETCHINAMAP',res.data);    
  },
    },
    mutations:{
       SETWORLDMAP(state,val){
         state.WorldMap=val;
       },
       SETTIANJINMAP(state,val){
         state.TianjinMap=val;
       },
       SETCHINAMAP(state,val){
        state.ChinaMap=val;
      }
    },
    state:{
       WorldMap:{},  //世界地图json数据
       TianjinMap:{},   //天津地图json数据
       ChinaMap:{}, //中国地图json数据
       worldval:[],     //世界疫情信息
       tianjinval:[],  //天津疫情信息
       chinaval:[]   //中国疫情信息
    },
    getters:{
       
    }
}
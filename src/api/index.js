//统一管理项目前部的接口
import requests from "./request";
import mapreq from "./configmap";

//地图相关
export const reqGetWorldMap=()=>mapreq({url:'/json/world.json',method:'get'});
export const reqGetTianjinMap=()=>mapreq({url:'/json/tianjin.json'});
export const reqGetChinaMap=()=>mapreq({url:'/json/china.json'});
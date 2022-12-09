//统一管理项目前部的接口
import requests from "./request";
import mapreq from "./configmap";
//将模块请求的接口函数统一导入
import * as admin from './mod/admin';
import * as card from './mod/card';
import * as login from './mod/login';
//将模块请求的接口函数统一暴露
export {admin,card,login};

//地图相关
export const reqGetWorldMap=()=>mapreq({url:'/json/world.json',method:'get'});
export const reqGetTianjinMap=()=>mapreq({url:'/json/tianjin.json'});
export const reqGetChinaMap=()=>mapreq({url:'/json/china.json'});
//获取地图信息
export const reqGetWorldMapMes=()=>requests({url:'/echarts/epidemic',method:'get'});

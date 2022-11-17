//该文件用于创建vuex中的store核心
 
//引入vue
import Vue from 'vue';
//引入vuex
import Vuex from 'vuex';
//应用vuex
Vue.use(Vuex);

//引入小仓库
import bar from './bar'
import message from './message';
import myself from './myself';
//创建并暴露store
export default new Vuex.Store({
    modules:{
        bar,
        message,
        myself,
    }
})
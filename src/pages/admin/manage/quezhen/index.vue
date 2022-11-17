<template>
    <div class="main">
      <el-row class="top" type="flex" :gutter="20">
       <el-col :offset="18" :span="4"><el-input placeholder="请输入名字" prefix-icon="el-icon-search" v-model="search"></el-input></el-col> 
        <el-col :offset=".5" :span="1"><el-button type="primary">搜索</el-button></el-col>
      </el-row>
      <tableVue></tableVue>
      <div class="pag">
      <pageVue :ChangePage="ChangePage" :curpage="1" :total="30" :pageSize="10"></pageVue>
      </div>
      
    </div>
</template>
<script>
import pageVue from '@/components/page.vue';
import tableVue from './table'
export default {
    name:'people',
    components:{
        tableVue,pageVue
    },
    data(){
        return {
            curpage:1,
            maxpagenum:10,
            allpage:100,
            search:'',
        }
    },
    methods:{
       ChangePage(nowpage){
        this.curpage=nowpage;
       } 
    }
}
</script>
<style lang="css" scoped>
    .main {
        height: 48.5rem;
        width: 100%;
    }
    .top {
        display: flex;
        align-items: center;
        width: 100%;
        height: 4rem;
    }
    .pag {
        margin: 3.25rem;
        display: flex;
        justify-content: center;
    }
    .page {
        height: 18.75rem;
    }
</style>
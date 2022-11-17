<template>
    <div >
   <div class="tmain">
    <i class="el-icon-s-fold but hvr-pulse" @click="ChangeLB"></i>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
  <el-breadcrumb-item :to="{ path: '/' }">疫情管理系统</el-breadcrumb-item>
  <!-- <el-breadcrumb-item>活动管理</el-breadcrumb-item>
  <el-breadcrumb-item>活动列表</el-breadcrumb-item>
  <el-breadcrumb-item>活动详情</el-breadcrumb-item> -->
</el-breadcrumb>
   </div>

    </div>
</template>
<script>
export default {
    name:'TopBar',
    methods:{
        //用于缩小左菜单
        ChangeLB(e){
           e.target.classList.toggle('el-icon-s-fold');
           e.target.classList.toggle('el-icon-s-unfold');
           this.$store.commit('bar/FOLDLEFTBAR');
        }
    }
}
</script>
<style lang="css" scoped>
    .tmain {
        width: 100%;
        height: 2.8125rem;
        overflow: hidden;
        box-shadow: 0 .025rem .125rem #666;
        background-image: linear-gradient(to right, #ececec, #e1e0f0, #d3d5f5, #c1cafa, #abc1ff);
    }
    .breadcrumb {
        margin-top: .9rem;
        margin-left: 6rem;
    }
    .but {
        float: left;
        margin-top: .6rem;
        margin-left: 2rem;
        font-size: 1.6rem;
        transition: all 1s;
    }
    .but:hover {
        color: skyblue;
    }
</style>
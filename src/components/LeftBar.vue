<template>
    <div>

<el-menu default-active="1-4-1" class="el-menu-vertical-demo" :collapse="$store.state.bar.IsFoldLeftBar">
    <!-- 展开侧边栏显示的图标 -->
      <div class="big" v-show="!$store.state.bar.IsFoldLeftBar" @click="ToLogin"> 
          <el-avatar icon="el-icon-user-solid" :src="isShowPic" class="icon hvr-grow" ></el-avatar>
      <span class="message">{{message}}</span>
      </div>

    
    
    <!-- 收起侧边栏显示的图标 -->
    <div @click="ToLogin">
      <el-avatar icon="el-icon-user-solid" :src="isShowPic"  class="icon_small hvr-grow" v-show="$store.state.bar.IsFoldLeftBar"></el-avatar>
    </div>
  
        <el-menu-item index="1" id="message" @click="ChangeRouterMes">
    <i class="el-icon-menu"></i>
    <span slot="title" >疫情信息</span>
  </el-menu-item>
     


  <el-menu-item index="2" :disabled="false" id="myself" @click="ChangeRoutermy">
    <i class="el-icon-document"></i>
    <span slot="title" >我的</span>
  </el-menu-item>

  <el-submenu index="3">
    <template slot="title">
      <i class="el-icon-location"></i>
      <span slot="title">管理员</span>
    </template>
    <div class="gly">
      <el-menu-item index="1-1" @click="ChangeRouterCard">打卡管理</el-menu-item>
      <el-menu-item index="1-4" @click="ChangeRoutermijie">密切接触者管理</el-menu-item>
      <el-menu-item index="1-2" @click="ChangeRouterquezhen">确诊患者管理</el-menu-item>
      <el-menu-item index="1-3" @click="ChangeRouterzhiyu">治愈管理</el-menu-item>
      <el-menu-item index="1-5" @click="ChangeRoutersiwang">死亡管理</el-menu-item>
    </div>

   
  </el-submenu>
  



  <el-menu-item index="4" id="set" @click="ChangeRoutersystem">
    <i class="el-icon-setting"></i>
    <span slot="title">超级管理员</span>
  </el-menu-item>
</el-menu>
 
    </div>
</template>
<script>
import { mapState } from 'vuex';
export default {
    data() {
      return {
      };
    },
    methods: {
        ToLogin(){  //登录界面
          if(this.IsLogin){
            this.$confirm('检测到您已登录账号，是否退出？', '退出登录', {
          distinguishCancelAndClose: true,
          confirmButtonText: '确认退出',
          cancelButtonText: '放弃'
        }).then(()=>{
          this.$store.commit('myself/EXITLOGIN');  //修改vuex仓库
          this.$router.replace({
            name:'china'
          })  
          this.$message({
            type: 'success',
            message: '退出成功'
          })
        })
          }else
           this.$store.commit('bar/SHOWLOG');
        },
        ChangeRouterMes(){
             this.$router.push({name:'china'})
        },
        ChangeRouterSet(){
             this.$router.push({name:'set'})
        },
        ChangeRoutermy(){
             this.$router.push({name:'myself'})
        },
        ChangeRouterpeople(){
             this.$router.push({name:'people'})
        },
        ChangeRouterCard(){
             this.$router.push({name:'cardmessage'})
        },
        ChangeRoutersystem(){
             this.$router.push({name:'system'})
        },
        ChangeRoutersiwang(){
             this.$router.push({name:'siwang'})
        },
        ChangeRoutermijie(){
             this.$router.push({name:'mijie'})
        },
        ChangeRouterzhiyu(){
             this.$router.push({name:'zhiyu'})
        },
        ChangeRouterquezhen(){
             this.$router.push({name:'quezhen'})
        },
        
    },
    computed:{
       ...mapState('myself',['IsLogin','Username']),
       message(){
          if(this.IsLogin===true){
             return `欢迎您! ${this.Username}`;
          }else
          return '请点击头像登录';
       },
       isShowPic(){  //是否显示头像
        if(this.$store.state.myself.IsLogin)
           return 'https://img1.baidu.com/it/u=3714444538,3832091059&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500'; 
           else
           return '';
        },
    },

}
</script>
<style lang="css" scoped>
.bbox {
  
}
.el-menu-vertical-demo {
    float: left;
    z-index: 2;
 }
     .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 12.5rem;
    min-height: 100vh;
    height: 100%;
    background-image: linear-gradient(to bottom, #ececec, #e1e0f0, #d3d5f5, #c1cafa, #abc1ff);
  }
  .el-menu--collapse {
    height: 100%;
    min-height: 100vh;
    background-image: linear-gradient(to bottom, #ececec, #e1e0f0, #d3d5f5, #c1cafa, #abc1ff);
  }
  .big {
    position: relative;
  }
  .gly {
    background-color: #d3d5f5;
  }
  .icon {
    cursor:pointer;
    margin-top:  3.25rem;
    margin-left: 3.8rem;
    margin-bottom: 4rem;
    height: 5rem;
    width: 5rem;
    font-size: 2rem;
    line-height: 5rem;
  }
  .message {
     position: absolute;
     top:80%;
     left:50%;
     transform: translate(-50%,-50%);
     font-size: 1rem;
     width: 100%;
     text-align: center;
  }
  .icon_small {
    cursor:pointer;
     margin-top: 1rem;
     margin-left: 1rem;
     margin-bottom:1rem;
     height: 2rem;
     width: 2rem;
     line-height: 2rem;
  }
</style>
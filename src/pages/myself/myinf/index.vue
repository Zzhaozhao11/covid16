<template>
    <div>
      <el-descriptions class="margin-top" title="用户信息" :column="3"  border v-loading="myinfIsLoading">
    <template slot="extra">
    </template>
    <el-descriptions-item class="box">
      <template slot="label">
        <i class="el-icon-user" ></i>
        账号
      </template>
      {{myinfMess.username}}
    </el-descriptions-item>
    <el-descriptions-item >
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        手机号
      </template>
      {{myinfMess.phone}}
    </el-descriptions-item>
    <el-descriptions-item >
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        id
      </template>
      {{myinfMess.id}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        权限
      </template>
      {{myinfMess.power}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        备注
      </template>
      无
    </el-descriptions-item>
  </el-descriptions>
 
    </div>
</template>
<script>
export default {
  data(){
        return {
           myinfIsLoading:true,
           myinfMess:{},
        }
    },
  mounted(){
      this.GetMyinf();
    },
    methods:{
       async GetMyinf(){ //向服务器请求个人信息
            this.myinfIsLoading=true;
            const mes=await this.$http.login.ReqSelectByName(this.$store.state.myself.Username);
            if(!mes||mes.status!=200||mes.data.code!=200)
            return;
            this.myinfMess=mes.data.data[0];
            this.myinfIsLoading=false;
        }
    },
}
</script>
<style lang="css" scoped>
 .box {
  background-color: red;
 }
</style>
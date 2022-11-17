<template>
    <div class="box">
        <div class="title">登录</div>
        <el-input class="user input" placeholder="请输入账号" v-model="userid" clearable></el-input>
        <div class="password input"> <el-input class="password" placeholder="请输入密码" v-model="password" show-password></el-input></div>
        <div class="change">
            <el-button type="warning" class="reg" @click="ChangeReg">切换注册</el-button>
            <el-button type="primary" class="but">提交登录</el-button>
        </div>
        
    </div>
</template>
<script>
export default {
    name:'',
    data(){
        return{
            password:'',
            userid:''
        }
    },
    methods:{
        ChangeReg(){
            this.$store.commit('bar/CHANGEREG');
        }
    }
}
</script>
<style lang="css" scoped>
   .box {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
   }
   .title {
    font-size: 2.125rem;
    FILTER: glow(strength=4)mask(color=#E1E4EC)
   }
   .input {
    width: 70%;
    margin-top: 3.25rem;
   }
   .change {
    margin-top:3rem;
    margin-bottom: 2rem;
    width: 70%;

   }
   .reg {
    width: 45%;
    
   }
   .but {
    width: 45%;
      float: right;
   }
   .password {
   
   }
</style>
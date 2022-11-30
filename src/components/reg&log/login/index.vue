<template>
    <div class="box">
        <div class="title">登录</div>
        <el-input class="user input" placeholder="请输入账号" v-model="userid" clearable></el-input>
        <div class="password input"> <el-input class="password" placeholder="请输入密码" v-model="password" show-password></el-input></div>
        <div class="change">
            <el-button type="warning" class="reg" @click="ChangeReg">切换注册</el-button>
            <el-button type="primary" class="but" @click="login">提交登录</el-button>
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
        },
        async login(){  //登录
           const res= await this.$http.login.ReqLogin(this.userid,this.password);  //axios发送登录请求
           if(res.data.code===200){  //登录成功
            this.$element.Message({
                message: '登录成功',
                type: 'success'
            })  
            this.$store.commit('bar/SHOWLOG');  //关闭登录界面
            this.$store.commit('myself/CHANGELOGIN');  //改变登录状态
            this.$store.commit('myself/SETUSERNAME',this.userid);   //向xuex仓库发送用户名
            //置空表单
            this.password='';
            this.userid='';
           }else{
            //置空表单
            this.password='';
            this.userid='';
            this.$element.Message.error('登录失败');
           }
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
<template>
    <div class="box">
        <div class="title">注册</div>
        <el-form size="mini" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="68px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="username">
    <el-input  v-model="ruleForm.username"></el-input>
  </el-form-item>
          <el-form-item label="密码" prop="password">
    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="确认密码" prop="checkPass">
    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="手机号" prop="phone">
    <el-input v-model="ruleForm.phone"></el-input>
  </el-form-item>
  <el-form-item label="公司" prop="company">
    <el-input v-model="ruleForm.company"></el-input>
  </el-form-item>
  <el-form-item label="权限" prop="power">
    <el-radio-group v-model="ruleForm.power">
      <el-radio label="普通用户"></el-radio>
      <el-radio label="管理员"></el-radio>
      <el-radio label="超级管理员"></el-radio>
    </el-radio-group>
  </el-form-item>
  <el-form-item>
  
 </el-form-item>
</el-form>
<div class="buts">
        <el-button type="warning" class="reg" @click="ChangeReg">切换登录</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
    <el-button type="primary" class="but" @click="submitForm('ruleForm')">提交</el-button>
    </div>
    </div>
</template>
<script>
import throttle from 'lodash/throttle'
export default {
    name: '',
    data() {
        const phoneValidator = (rule, value, callback) => {
      if (/^1[3456789]\d{9}$/.test(value)) { // 利用正则表达式校验手机号
        callback()
      } else {
        callback(new Error('请输入正确手机号'))
      }
    };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          username:'',
          password: '',
          checkPass: '',
          phone: '',
          company:'',
          power:'',
        },
        rules: {
          username: [
          { required: true, trigger: 'blur', message: '用户名不能为空' },
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          phone: [
          { required: true, trigger: 'blur', message: '手机号不能为空' },
          { min: 11, max: 11, trigger: 'blur', message: '请输入11位手机号码' },
          { validator: phoneValidator, trigger: 'blur' }
          ],
          power:[
            {required: true, message: '请选择权限', trigger: 'change'}
          ]
        }
      };
    },
    mounted(){
      window.addEventListener('keydown', (e) =>{  //键盘监听事件
            if(!this.$store.state.bar.IsShowLog)  //只有在注册登录界面时才会触发
            return;
      if(e.key==='Enter'){   //当按下enter键时提交表单
        if(this.$store.state.bar.Isreg){  //当处于注册界面时
            this.submitForm('ruleForm');
        }
      } 
    })
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.Addreg();
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      ChangeReg(){
        this.resetForm('ruleForm');
            this.$store.commit('bar/CHANGEREG');
        },
        Addreg:throttle(async function(){
          const res=await this.$http.login.ReqRegister(this.ruleForm).catch(err=>{
          this.$element.Message.error('服务器错误');
        });
        if(res.data&&res.data.code===200){
          this.$element.Message({
          message: '注册成功',
          type: 'success'
        });
        this.ChangeReg();
        }else{
          this.$element.Message.error('注册失败');
        }
        },1500),
    }
}
</script>
<style lang="css" scoped>
.box {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding:0 3.125rem;
}
.title {
    margin-top: 1rem;
    font-size: 2.125rem;
    margin-bottom: 1rem;
    
}
.demo-ruleForm {
  margin-right: 1rem;
  margin-top: 1rem;

}
.buts {
   margin-top: -1rem;
   margin-bottom: 1rem;
}

</style>
<template>
    <div>
        <el-form label-position="top"  :rules="rules" :model="form" ref="form">
            <p class="title">每日健康打卡</p>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="现在地址" prop="location">
                <el-input v-model="form.location"></el-input>
            </el-form-item>
            <el-form-item label="健康状况" prop="health">
                <el-radio-group v-model="form.health">
                    <el-radio label="健康"></el-radio>
                    <el-radio label="头晕感冒发热"></el-radio>
                    <el-radio label="昏迷"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="体温" prop="temperature">
                <el-input v-model="form.temperature"></el-input>
            </el-form-item>
            <el-form-item label="是否到达过高风险地区" prop="danger">
                <el-radio-group v-model="form.danger">
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="是否接触过疑似或者确诊病例" prop="contact">
                <el-radio-group v-model="form.contact"> 
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="备注" >
                <el-input type="textarea" v-model="form.comments"></el-input>
            </el-form-item>
            <el-form-item class="butf">
                <el-button type="primary" class="but" @click="submitForm">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
export default {
    data() {
        return {
          form:{
            name:'',
            location:'',
            health:'',
            temperature:'',
            clockDate:'',
            danger:'',
            contact:'',
            comments:''
          },
          rules:{
            name:[{ required: true, message: '请输入姓名', trigger: 'blur' }],
            location:[{ required: true, message: '请输入现在地址', trigger: 'blur' }],
            health:[{ required: true, message: '请选择健康状况', trigger: 'change' }],
            temperature:[{ required: true, message: '请输入体温', trigger: 'blur' }],
            danger:[{ required: true, message: '请选择是否到达过高风险地区', trigger: 'change' }],
            contact:[{ required: true, message: '请选择是否接触过疑似或者确诊病例', trigger: 'change' }],
          }
        }
    },
    methods: {
        submitForm() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.commit();
          } else {
            return false;
          }
        });
      },
      async commit(){
         this.form.clockDate= new Date().getFullYear() +"-"+ (new Date().getMonth() + 1) + '-' + new Date().getDate();
         let res=await this.$http.card.reqAddCard(this.form);
         console.log(res);
         if(res.data.code!==200){
            this.$message.error('打卡提交失败');
            return;
         }
         for (const key in this.form) {
            this.form[key]='';
         }
         this.$message({type:'success',message:'打卡成功'});
         this.$store.commit('myself/CHANGECARD');
       }
    }
}
</script>
<style lang="css" scoped>
.title {
    width: 100%;
    text-align: center;
    font-size: 1.625rem;
    margin-bottom: 1rem;
}
.butf {
    margin-top: 3rem;
    display: flex;
    justify-content: center;
}
.but {
    width: 12rem;

}
</style>
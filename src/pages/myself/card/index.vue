<template>
  <div style="color: #666;font-size: .875rem;">

    <el-card style="margin-bottom:3rem;">
      欢迎使用打卡功能
      <el-divider />
      每天都要按时打卡哦！
    </el-card>

    <el-card style="margin-top: 1.25rem;margin-bottom: 1.25rem;">
      <div style="margin: 1.25rem 0; font-size: 1.25rem;text-align: center;">{{ new Date().getFullYear() + '年' + (new
          Date().getMonth() + 1) + '月' + new Date().getDate() + '日'
      }}</div>
      <div class="tocard" @click="ShowCard">
        打 卡
      </div>
    </el-card>

  </div>
</template>
  
<script>

export default {
  name: "Sign",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },

  methods: {
    sign() {
      const location = localStorage.getItem("location")
      const username = this.user.username     
    },
    ShowCard(){
       this.$store.commit('myself/CHANGECARD');
    },
  },
  watch:{

  }
}
</script>
  
<style lang="css" scoped>
.tocard {
  width: 15rem;
  height: 15rem;
  line-height: 15rem;
  border-radius: 50%;
  background-color: #1E90FF;
  font-size: 3.125rem;
  color: #fff;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 0 1.875rem rgba(0, 0, 0, .2);
}
.hascard{
  width: 15rem;
  height: 15rem;
  line-height: 15rem;
  border-radius: 50%;
  background-color: yellowgreen;
  font-size: 3.125rem;
  color: #fff;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 0 1.875rem rgba(0, 0, 0, .2);
}
</style>
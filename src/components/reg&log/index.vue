<template>
    <div>
        <transition class="animate__animated"
        enter-active-class="animate__animated animate__fadeInDown"
    leave-active-class="animate__animated animate__fadeOutDown">
            <div class="shelter trans" v-show="$store.state.bar.IsShowLog">
            <div class="box">
                <login class="login" v-show="!$store.state.bar.Isreg"></login>
                <register class="reg" v-show="$store.state.bar.Isreg"></register>
                <span class="close el-icon-close hvr-grow" @click="ToLogin"></span>
            </div>
            
        </div>
        </transition>
      
    </div>
</template>
<script>
import login from './login'
import register from './register'
export default {
    name: '',
    components: {
        login, register
    },
    data(){
        return{
          
        }
    },
    methods: {
        ToLogin() {  //登录界面
            this.$store.commit('bar/SHOWLOG');
        },
    }
}
</script>
<style lang="css" scoped>
.trans {
    animation-duration: .3s ; 
}
.shelter {
    position: fixed;
    top: 0;
    left: 0;
    height: 100vh;
    width: 100vw;
    z-index: 999;
    background: rgba(0, 0, 0, .6);
}

.box {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    height: 31.25rem;
    width: 24.75rem;
    border-radius: 30px;
    background-color: aliceblue;
    box-shadow: .1rem .4rem .5rem rgba(0,0,0,.5);
    background-image: linear-gradient(to right top, #c9ddfb, #bcd3fc, #b1c8fd, #a9bcfd, #a4b0fc);
}

.close {
    position: absolute;
    top: 1rem;
    right: -4rem;
    height: 3rem;
    width: 3rem;
    text-align: center;
    line-height: 3rem;
    color: aliceblue;
    cursor: pointer;
    font-size: 3rem;
}
.login,
.reg {
    height: 100%;
    width: 100%;
}
</style>
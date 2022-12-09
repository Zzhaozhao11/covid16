<template>
    <div class="box">
        <card class="card"></card>
        <div class="right">
            <myinf class="myinf" ></myinf>
            
                <transition class="animate__animated"
        enter-active-class="animate__bounceIn"
   >
                    <vform v-show="Isshow" class="form"></vform>
                </transition>
                <transition class="animate__animated"
        enter-active-class="animate__animated animate__fadeIn">
                    <img src="./img/hesuan.webp" v-show="!Isshow">
                </transition>               
        </div>
        
    </div>
</template>
<script>
import card from './card'
import myinf from './myinf'
import vform from './form'
export default {
    name:'myself',
    components:{
        card,myinf,vform
    },
    computed:{
        Isshow(){  //切换打卡界面
            if(this.$store.state.myself.ShowCard)
            return true;
            else
            return false;
        }
    }
}
</script>
<style lang="css" scoped>
    .box {
       
        margin-top: 4rem;
        height: 45rem;
        display: flex;
        justify-content: center;
    }
    .card {
        margin-top: 5rem;
        margin-left: 5rem; 
       margin-right: 10rem;
    }
    .right {
       margin-right: 10rem;
    }
    img {
        margin-top: 3.125rem;
        height: 55vh;
        width: 100%;
        border-radius: .25rem;

    }
    .form {
        margin-top: 3.125rem;
        height: 55vh;
        padding:3.125rem;
        box-shadow: 0 0 1.875rem rgba(0, 0, 0, .2);
        overflow-y: scroll;
        background-color: #fff;
        border-radius: .25rem;
    }
    .form::-webkit-scrollbar {
  /*滚动条整体样式*/
  width : 10px;  /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
  }
  .form::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 10px;
  box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.2);
  background   : #a6c5ff;
  }
  .form::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  background   : #ededed;
  }
</style>
<template>
    <div class="box">
       <div class="butbox">
          <button class="but niceButton" id="world" @click="Change">世界疫情</button>
          <button class="but niceButton" id="china" @click="Change">中国疫情</button>
          <button class="but niceButton" id="tianjin" @click="Change">天津疫情</button>
       </div>
       <div class="main">
          <router-view ></router-view>
       </div>
    </div>
</template>
<script>
export default {
    name:'message',
    methods:{
        Change(e){
            this.$router.push({name:e.target.id})
        }
    }

}
</script>
<style lang="css" scoped>
    .box {
         display: flex;
         align-items: center;
         flex-direction: column;
         height: 100%;
    }
    .butbox {
        display: flex;
        height: 6.5rem;
        width: 48.75rem;
        margin-top: 2.5rem;
        align-items: center;
        justify-content: space-around;
        
    }
    .niceButton{
    display: inline-block;
    padding: 15px 25px;
    font-size: 24px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color:#fff;
    background-image: linear-gradient(to right top, #76aeff, #5097ff, #2b7eff, #1162ff, #273fff);
    border: none;
    border-radius: 15px;
    box-shadow: 0 9px #999;
}
.niceButton:hover{
    background-image: linear-gradient(to right top, #4e96ff, #2381ff, #0069ff, #004eff, #102afa);
}
.niceButton:active{
    background-image: linear-gradient(to right top, #4e96ff, #2381ff, #0069ff, #004eff, #102afa);
    box-shadow: 0 5px #666;
    transform:translateY(4px);
}

    .but {
        width: 27%;
        height: 80%;
        
    }

    .main {
        margin-top: 3rem;
        height: 36rem;
        width: 100%;
    }
</style>
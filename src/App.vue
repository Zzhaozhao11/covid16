<template>
  <div id="app">
    <reglog></reglog>
    <LeftBarVue></LeftBarVue>
    <div class="right">
    <TopBarVue></TopBarVue>
      <div class="main">
         <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
//引入组件
import LeftBarVue from './components/LeftBar.vue';
import TopBarVue from './components/TopBar.vue';
import reglog from './components/reg&log';
export default {
  name: 'App',
  components: {
    LeftBarVue,TopBarVue,reglog
  }
}
</script>

<style>
html {
  font-size: 16px;
  background-color: rgb(255,246,250);
  overflow: hidden;
}
#app {
  display: flex;
}
   .right {
    width: 100%;
    min-height: 100vh;
   }
</style>

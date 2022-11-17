<template>
    <div class="box">
       
     <div id="world" ref="world"  v-loading="true"
    element-loading-text="拼命加载中"
    element-loading-background="rgba(0, 0, 0, 0)">
</div>
 
    </div>
</template>
<script>
export default {
    name:'world',
    data() {
    return {
 
    }
  },
  methods:{
  async  Configmap(){
    try{
        await  this.$store.dispatch('message/SetWroldMap');
     await  this.$echarts.registerMap('world',this.$store.state.message.WorldMap);
     const options={
         //标题样式
      title: {
        text: '世界疫情地图',
        x: "center",
        y: '0%',
        textStyle: {
          fontSize: 20,
          color: "#333"
        },
      },
      //这里设置提示框 (鼠标悬浮效果)
      tooltip: {
        //数据项图形触发
        trigger: 'item',
        //提示框浮层的背景颜色。 (鼠标悬浮后的提示框背景颜色)
        backgroundColor: "white",
        //字符串模板(地图): {a}（系列名称），{b}（区域名称），{c}（合并数值）,{d}（无）
        formatter: '地区：{b}<br/>现有确诊病例：{c}'
      },
      //视觉映射组件
      visualMap: {
        top: 'center',
        left: 'left',
        // 数据的范围
        min: 10,
        max: 100,
        text: ['高', '低'],
        realtime: true,  //拖拽时，是否实时更新
        calculable: true,  //是否显示拖拽用的手柄
        inRange: {
          // 颜色分布
          color: ['white', 'Tomato', 'orangered']
        }
      },
      series: [
        {
          name: '确诊',
          type: 'map',
          map:'world',
          roam: true,
          //是否开启鼠标缩放和平移漫游
          itemStyle: {
            //地图区域的多边形 图形样式
            normal: {
              //是图形在默认状态下的样式
              label: {
                show: true,//是否显示标签
                textStyle: {
                  color: "black"
                },
                fontSize:7,
              }
            },
            zoom: 5,
            //地图缩放比例,默认为1
            emphasis: {
              //是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
              label: {show: true}
            }
          },
          top: '5%',
          left: '20%',
          data: [{name:'China',value:1000}]
        }
      ]
        }
     var chartDom = this.$refs.world;
    var myChart =await this.$echarts.init(chartDom);
    myChart.setOption(options);
    }catch(err){
       alert(err.message);
    }
  }
},
  mounted(){
    this.Configmap();
 } 
}

</script>
<style lang="css" scoped>
   .box {
    height: 100%;
    width: 100%;
   }
   #world {
    height: 100%;
    width: 100%;
    font-size: 6.25rem;
    line-height: 1rem;
   }
</style>
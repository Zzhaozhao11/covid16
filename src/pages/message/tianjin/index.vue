<template>
  <div>
    <el-row style="margin-top: 40px">
      <el-col :span="12">
        <div
          style="width: 500px; margin: 0 auto; padding: 20px; box-shadow: 0 5px 15px -5px rgba(0,0,0,.5); border-radius: 10px;">
          <div style="text-align: center; margin-bottom: 20px; font-size: 24px">
            <b>天津疫情实时数据</b>
          </div>
          <div class="out" style="display: flex; flex-wrap:wrap; justify-content: flex-start;">

            <div class="item" style="background-color: lightgray">
              <div class="item_title">累计确诊</div>
              <div class="item_num" style="color: Brown">{{ total.confirm ? total.confirm : '未更新' }}</div>
              <div class="item_num item_num_plus">较昨日 <span style="color: Brown">+{{ today.confirm }}</span></div>
            </div>
            <div class="item" style="background-color: lightpink">
              <div class="item_title">累计死亡</div>
              <div class="item_num" style="color: #333">{{ total.dead ? total.dead : '未更新' }}</div>
              <div class="item_num item_num_plus">较昨日 <span style="color: #333">+{{ today.dead }}</span></div>
            </div>
            <div class="item" style="background-color: lightgreen">
              <div class="item_title">累计治愈</div>
              <div class="item_num" style="color: green">{{ total.heal ? total.heal : '未更新' }}</div>
              <div class="item_num item_num_plus">较昨日 <span style="color: green">+{{ today.heal }}</span></div>
            </div>
          </div>
        </div>
      </el-col>

      <el-col :span="12">
        <div id="main" style="width: 95%; height: 30.125rem" ref="tianjin" v-loading="true" element-loading-text="拼命加载中"
          element-loading-background="rgba(0, 0, 0, 0)"></div>
      </el-col>

    </el-row>
  </div>
</template>
<script>
export default {
  name: 'world',
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      total: {},
      today: {},
      extData: {}
    }
  },
  methods: {
    async Configmap() {
      try {
        await this.$store.dispatch('message/SetTianjinMap');
        await this.$echarts.registerMap('tianjin', this.$store.state.message.TianjinMap);
        const optionstianjin = {
          //标题样式
          title: {
            text: '天津疫情地图',
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
              map: 'tianjin',
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
                    }
                  }
                },
                zoom: 5,
                //地图缩放比例,默认为1
                emphasis: {
                  //是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
                  label: { show: true }
                }
              },
              top: '5%',
              left: '30%',
              data: []
            }
          ]
        }
        let chartDomtj = this.$refs.tianjin;
        let myCharttianjin = await this.$echarts.init(chartDomtj);
        // 获取数据
        this.$http.reqGetWorldMapMes().then(result => {
          let res = result.data;
          console.log(res.data.areaTree[2].children[23].children);
          this.total = res.data.chinaTotal.total
          this.today = res.data.chinaTotal.today
          this.extData = res.data.chinaTotal.extData
          let data = []
          res.data.areaTree[2].children[23].children.forEach(item => {
            data.push({ name: item.name, value: item.total.confirm - item.total.dead - item.total.heal })
            optionstianjin.series[0].data = data
          })
          myCharttianjin.setOption(optionstianjin);
        })
      } catch (err) {
        alert(err.message);
      }
    }
  },
  mounted() {
    this.Configmap();
  }
}
</script>
<style lang="css" scoped>
.item {
  text-align: center;
  width: 9.375rem;
  height: 9.375rem;


  margin-right: .3125rem;
  margin-bottom: .3125rem;
}

.item:nth-child(3n) {
  margin-right: 0;
}

.out {
  overflow: hidden;
}

.item_title {
  font-size: 1rem;
  font-weight: bold;
  margin-top: 1.25rem;
}

.item_num {
  margin-top: .625rem;
  font-size: 1.875rem;
  font-weight: bold;
}

.item_num_plus {
  font-size: 1rem;
  color: #666;
}
</style>
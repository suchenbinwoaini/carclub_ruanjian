<template>
  <el-row>
    <el-col :span="12">
      <div id="main" style="width: 500px; height: 400px"></div>
    </el-col>
    <el-col :span="12">
      <div id="pie" style="width: 500px; height: 400px"></div>
    </el-col>
  </el-row>
</template>

<script>
import * as echarts from "echarts";
import request from "@/utils/request";

export default {
  name: "Mcharts",
  data(){
    return{
    }
  },
  mounted() {
    var option = {
      xAxis: {
        type: 'category',
        data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          itemStyle : { normal: {label : {show: true}}},   //重点是这句加上了后可以显示数字。不然不显示
          data: [],
          type: 'line'
        },
        {
          data: [],
          type: 'bar'
        }
      ]
    };
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    request.get("/money/mcharts",{
    }).then(res => {
      console.log(res.data)
      option.series[0].data = res.data
      option.series[1].data = res.data
      myChart.setOption(option)
    })
    var pieOption = {
      title: {
        text: '各季度缴费金额统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          type: 'pie',
          radius: '60%',
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{d}%'
            }
          },
          data: [],  // 填空
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };

    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);

    request.get("/money/mcharts").then(res => {

      pieOption.series[0].data = [
        {name: "一月", value: res.data[0]},
        {name: "二月", value: res.data[1]},
        {name: "三月", value: res.data[2]},
        {name: "四月", value: res.data[3]},
        {name: "五月", value: res.data[4]},
        {name: "六月", value: res.data[5]},
        {name: "七月", value: res.data[6]},
        {name: "八月", value: res.data[7]},
        {name: "九月", value: res.data[8]},
        {name: "十月", value: res.data[9]},
        {name: "十一月", value: res.data[10]},
        {name: "十二月", value: res.data[11]},

      ]
      pieChart.setOption(pieOption)
    })
  }
}
</script>

<style scoped>

</style>
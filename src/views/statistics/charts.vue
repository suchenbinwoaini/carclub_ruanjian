<template>
  <div id="main" style="width: 1200px; height: 600px"></div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts'

export default {
  name: "charts",data(){
    return{
    }
  },
  mounted() {
    var option = {
      xAxis: {
        type: 'category',
        data: []
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

    request.get("/money/chart",{
    }).then(res => {
      var Xdata = [];
      var Sdata = [];
      for (var i = 0; i < res.data.length; i++) {
        var item = res.data[i];
        console.log(item)
        Xdata.push(item.mdate);
        Sdata.push(item.money);
      }
      option.series[0].data = Sdata;
      option.xAxis.data = Xdata;
      myChart.setOption(option)
    })
  }
}
</script>

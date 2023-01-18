<template>
  <div id="container" style="width: 100%;height: 80vh"></div>
</template>

<script>
export default {
  name: "map",
  mounted() {
    var map = new AMap.Map("container", {
      zoom: 13,
      center:[117.058182,39.236622]
    })
    var marker = new AMap.Marker({
      position: new AMap.LngLat(117.058182,39.236622),
      offset: new AMap.Pixel(-10, -10),
      title: '河北工业大学'
    })
    // var clickHandler = function(e) {
    //   alert('['+e.lnglat.getLng()+','+e.lnglat.getLat()+' ]可能需要救援！');
    // };

// 绑定事件
    map.on('click', this.alert1);
    map.add(marker);
  },
  methods:{
    alert1(e){
      this.$message.success("经度为"+e.lnglat.getLng()+"，纬度为"+e.lnglat.getLat()+"，需要救援，hahhaah")
    }
  }
}
</script>

<style scoped>
#container {width:300px; height: 180px; }

</style>
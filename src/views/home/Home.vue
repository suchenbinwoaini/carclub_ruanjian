<template>
  <div>
    <el-carousel :interval="4000" type="card">
      <el-carousel-item v-for="item in 6" :key="item" ></el-carousel-item>
    </el-carousel>
    <div style="font-size: xxx-large;font-weight: bolder;margin-bottom: 25px">欢迎来到汽车俱乐部管理系统</div>
    <div><span style="font-size: xx-large">制作人：宿辰彬</span></div>

  </div>

</template>

<script>


export default {
  name: "Home",
  mounted() {

  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(6n) {
  background: url("../../assets/i1.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.el-carousel__item:nth-child(6n+1) {
  background: url("../../assets/i2.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.el-carousel__item:nth-child(6n+2) {
  background: url("../../assets/i3.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.el-carousel__item:nth-child(6n+3) {
  background: url("../../assets/i4.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.el-carousel__item:nth-child(6n+4) {
  background: url("../../assets/i5.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.el-carousel__item:nth-child(6n+5) {
  background: url("../../assets/i6.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
</style>
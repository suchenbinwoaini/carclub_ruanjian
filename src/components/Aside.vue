<template>
  <el-menu :default-openeds="[]" style="height: 100%;overflow-x: hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px;position: relative;top: 6px;margin-right: 5px">
      <b style="color: white" v-show="!isCollapse">经理后台</b>
    </div>
    <el-menu-item index="/home">
      <i class="el-icon-s-home"></i>
      <span>主页</span>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>公司管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/User">员工管理</el-menu-item>
        <el-menu-item index="/download">文件管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>系统管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/vip">会员管理</el-menu-item>
        <el-menu-item index="/money">缴费管理</el-menu-item>
        <el-menu-item index="/helps">系统帮助</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>车务管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/caract">车务活动</el-menu-item>
        <el-menu-item index="/command">车务提醒</el-menu-item>
        <el-menu-item index="/Traffic">车务待办</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="4">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>统计报表</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/charts">本月营业额统计</el-menu-item>
        <el-menu-item index="/Mcharts">各月营业额统计</el-menu-item>
        <el-menu-item index="/Qcharts">各季度营业额统计</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="5">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>救援管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/maps">呼救管理</el-menu-item>
        <el-menu-item index="/rescuerecords">救援管理</el-menu-item>
        <el-menu-item index="/carinfo">救援车辆信息</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="6">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>旅行管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/travel">旅行记录</el-menu-item>
        <el-menu-item index="/travelinfo">旅行信息</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
<script>
export default {
  name: "Aside",
  props:{
    isCollapse:Boolean,
    logoTextshow:Boolean
  }
}
</script>

<style scoped>

</style>
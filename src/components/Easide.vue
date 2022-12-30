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
      <b style="color: white" v-show="!isCollapse">工作人员后台</b>
    </div>
    <el-menu-item index="/home1">
      <i class="el-icon-s-home"></i>
      <span>主页</span>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-user-solid"></i>
        <span>工作管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/money1">缴费管理</el-menu-item>
        <el-menu-item index="/Traffic1">服务信息</el-menu-item>
        <el-menu-item index="/vip1">会员管理</el-menu-item>
        <el-menu-item index="/helps1">系统帮助</el-menu-item>
        <el-menu-item index="/download">上传下载</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-s-opportunity"></i>
        <span>提醒服务</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/caract1">车务活动</el-menu-item>
        <el-menu-item index="/command1">车务提醒</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="5">
      <template slot="title">
        <i class="el-icon-message-solid"></i>
        <span>救援管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/maps1">呼救管理</el-menu-item>
        <el-menu-item index="/rescuerecords1">救援管理</el-menu-item>
        <el-menu-item index="/carinfo1">救援车辆信息</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="6">
      <template slot="title">
        <i class="el-icon-s-flag"></i>
        <span>旅行管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/travel1">旅行记录</el-menu-item>
        <el-menu-item index="/travelinfo1">旅行信息</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Easide",
  props:{
    isCollapse:Boolean,
    logoTextshow:Boolean
  }
}
</script>

<style scoped>

</style>
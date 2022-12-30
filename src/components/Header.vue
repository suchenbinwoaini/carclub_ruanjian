<template>
  <div style="font-size: 12px;line-height: 60px;display: flex;color: #55b9f3" :style="{'color':theme}">
    <div style="flex: 1; font-size: 22px;" >
      <div style="float: left">
        <span :class="collapseBtnClass" style="cursor: pointer;" @click="collapse" ></span>
        <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
          <el-breadcrumb-item :to="'/Home'">首页</el-breadcrumb-item>
                  <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

    </div>
    <el-dropdown style="width: 100px;cursor: pointer">
      <span>{{ ename }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown" style="width: 100px;text-align: center">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item @click="logout">退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-color-picker
        v-model="theme"
        :predefine="[
      '#409EFF',
      '#1890ff',
      '#304156',
      '#212121',
      '#11a983',
      '#13c2c2',
      '#6959CD',
      '#f5222d'
    ]"
        class="theme-picker"
        popper-class="theme-picker-dropdown"
    />
  </div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass:String,
    collapse:Function,
  },
  data(){
    return{
      theme:"#111111",
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      ename:localStorage.getItem("ename")
    }
  },
  computed: {
    currentPathName () {
      return this.$store.state.currentPathName;　　//需要监听的数据
    }
  },
  methods:{
    logout(){
      this.$router.replace("/")
    }
  }
}
</script>

<style scoped>

</style>
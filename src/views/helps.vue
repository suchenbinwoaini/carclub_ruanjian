<template>
  <el-container>
    <el-container style>
      <el-header style="text-align:left;box-shadow: 0 1px 2px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);height: 330px;font: 16px Medium;line-height: 2">
        <div>
          本系统为汽车俱乐部管理系统，为提高工作效率，向工作人员和管理员提供相关管理功能。<br>
          您可以使用本系统进行如下管理操作：<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">提醒服务</span>（提醒客户该执行的车务活动）<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">会员管理</span>（会员信息增删查改、会员费收缴记录）<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">车务管理</span>（新车上牌代办，保险代办，验证代办，落户代办）<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">救援服务</span>（呼救记录，救援情况记录以及救援车辆管理）<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">旅行服务</span>（旅行信息记录、修改、查询）<br>
          如果您是<span style="font-weight: bolder">经理</span>，还可进行如下操作：<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">统计报表</span>（所有当日、当月、当年的营业额）<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">用户管理</span>（管理本部门的员工信息）<br>
        </div>
      </el-header>
      <el-footer style="text-align:left;height: 300px;line-height:2;box-shadow: 0 1px 2px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);">
        <div style="float: left">
          <span style="font-weight: bolder">您可能遇到的问题：</span><br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">新增信息不显示：</span><br>
          —可能是网络延迟，请多刷新几次。<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">缴费记录错误怎么办?</span><br>
          —删除错误记录，重新添加即可。<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">新用户怎么添加？</span><br>
          —及时联系系统管理员，为新用户设置账户信息<br>
          <span style="font: 20px Extra large;font-weight: bolder;color: rgb(46,117,181)">更多问题：</span><br>
          —请按右侧留言框反馈开发人员<br>
        </div>
        <div style="float: right;width: 500px">
          <el-input
              type="textarea"
              placeholder="请输入内容"
              v-model="textarea"
              maxlength="30"
              show-word-limit
              size="medium"
              style="width: 500px;margin-top: 20px"
          >
          </el-input>
          <el-button @click="submit">
            提交反馈
          </el-button>
        </div>
      </el-footer>

    </el-container>
    <el-aside style="width: 20%;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    font-family: PingFang SC;font: 18px large;line-height: 2;
    ">
      <div style="font: 20px Extra large;line-height: 3.5">
        联系我们
      </div>
      <div>
        联系人：
      </div>
      <div style="color: rgb(46,117,181)">
        宿辰彬
      </div>
      <div>
        联系方式：
      </div>
      <div style="color: rgb(46,117,181)">
        13373215579
      </div>
      <div>
        邮箱地址：
      </div>
      <div style="color: rgb(46,117,181)">
        1131226516@qq.com
      </div>
    </el-aside>

  </el-container>

</template>

<script>
export default {
  name: "help",
  data() {
    return {
      textarea: ''
    }
  },
  methods:{
    submit(){
      if (this.textarea===""){
        this.$message.error("留言框为空")
      }
      else {
        this.textarea=""
        this.$message.success("成功提交")
      }
    }
  }
}
</script>

<style scoped>
</style>
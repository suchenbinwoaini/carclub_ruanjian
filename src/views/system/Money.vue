<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入会员ID号" suffix-icon="el-icon-search" v-model="VIPnum"></el-input>
      <el-input style="width: 200px" placeholder="请输入缴费日期" suffix-icon="el-icon-message" class="ml-5" v-model="Mdate"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          style="margin-left: 5px"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="moneynum" label="缴费编号"></el-table-column>
      <el-table-column prop="vipnum" label="会员ID"></el-table-column>
      <el-table-column prop="mdate" label="缴费日期"></el-table-column>
      <el-table-column prop="money" label="缴费金额"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
<!--          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>-->
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.moneynum)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="90px" size="small">
        <el-form-item label="缴费编号">
          <el-input v-model="form.moneynum" autocomplete="off" placeholder="请输入缴费编号"></el-input>
        </el-form-item>
        <el-form-item label="缴费用户ID">
          <el-input v-model="form.vipnum" autocomplete="off" placeholder="请输入缴费用户ID"></el-input>
        </el-form-item>
        <el-form-item label="落户日期">
          <el-date-picker
              v-model="form.mdate"
              align="left"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="缴费金额">
          <el-input v-model="form.money" autocomplete="off" placeholder="请输入缴费金额"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "Money",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      moneynum:"",
      VIPnum:"",
      Mdate:"",
      Money:"",
      form:{},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/money/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          VIPnum:this.VIPnum,
          Mdate:this.Mdate,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    reset(){
      this.VIPnum = ""
      this.Mdate= ""
      this.load()
    },
    del(id){
      request.delete("/money/"+id).then(res=>{
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.moneynum)  // [{}, {}, {}] => [1,2,3]
      console.log(ids)
      request.post("/money/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    save(){
      request.post("/money",this.form).then(res => {
        if (res) {
          console.log(res)
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
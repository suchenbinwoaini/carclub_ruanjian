<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入旅行编号" suffix-icon="el-icon-search" v-model="travelid"></el-input>
      <el-input style="width: 200px" placeholder="请输入vip编号" suffix-icon="el-icon-message" class="ml-5" v-model="vipnum"></el-input>
      <el-input style="width: 200px" placeholder="请输入车牌号" suffix-icon="el-icon-position" class="ml-5" v-model="carnum"></el-input>
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
      <el-table-column prop="travelid" label="旅行编号" width="80"></el-table-column>
      <el-table-column prop="vipnum" label="会员编号" width="140"></el-table-column>
      <el-table-column prop="traveltime" label="旅行时间" width="120"></el-table-column>
      <el-table-column prop="traveladdress" label="旅行地点" width="120"></el-table-column>
      <el-table-column prop="travelprice" label="旅行费用"></el-table-column>
      <el-table-column prop="carnum" label="车牌号"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.travelid)"
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
      <el-form label-width="80px" size="small">
        <el-form-item label="服务编号">
          <el-input v-model="form.travelid" autocomplete="off" placeholder="请输入服务编号"></el-input>
        </el-form-item>
        <el-form-item label="会员编号">
          <el-input v-model="form.vipnum" autocomplete="off" placeholder="请输入会员编号"></el-input>
        </el-form-item>
        <el-form-item label="旅行时间" placeholder="请输入旅行时间">
          <el-date-picker
              v-model="form.traveltime"
              align="left"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="旅行地点">
          <el-input v-model="form.traveladdress" autocomplete="off" placeholder="请输入旅行地点"></el-input>
        </el-form-item>
        <el-form-item label="旅行费用">
          <el-input v-model="form.travelprice" autocomplete="off" placeholder="请输入旅行费用"></el-input>
        </el-form-item>
        <el-form-item label="车牌号">
          <el-input v-model="form.carnum" autocomplete="off" placeholder="请输入车牌号"></el-input>
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
  name: "travellinfo",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      travelid:"",
      vipnum:"",
      traveltime:"",
      traveladdress:"",
      travelprice:"",
      carnum:"",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/travel/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          travelid:this.travelid,
          vipnum:this.vipnum,
          carnum:this.carnum
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    reset(){
      this.travelid = ""
      this.vipnum = ""
      this.carnum = ""
      this.load()
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.travelid)  // [{}, {}, {}] => [1,2,3]
      console.log(ids)
      request.post("/travel/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    save(){
      if (this.form.travelid==null || this.form.vipnum ==null ||
      this.form.traveltime ==null || this.form.carnum==null){
        this.$message.error("添加失败，请完善信息！！")
        return
      }
      else{
        request.post("/travel",this.form).then(res => {
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
    },
    del(id){
      request.delete("/travel/"+id).then(res=>{
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
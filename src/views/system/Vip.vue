<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入vip编号" suffix-icon="el-icon-search" v-model="vipnum"></el-input>
      <el-input style="width: 200px" placeholder="请输入vip名称" suffix-icon="el-icon-message" class="ml-5" v-model="vname"></el-input>
      <el-input style="width: 200px" placeholder="请输入vip身份证号" suffix-icon="el-icon-position" class="ml-5" v-model="vipid"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset" class="ml-5">重置</el-button>
      <el-button type="primary" @click="exp" class="ml-5">导出</el-button>
      <el-upload name="file" action="http://localhost:8088/vip/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入</el-button>
      </el-upload>
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
      <el-table-column prop="vipnum" label="会员ID" width="80"></el-table-column>
      <el-table-column prop="vname" label="会员姓名" width="140"></el-table-column>
      <el-table-column prop="vsex" label="会员性别" width="120"></el-table-column>
      <el-table-column prop="vphone" label="会员电话" width="120"></el-table-column>
      <el-table-column prop="vrank" label="会员等级"></el-table-column>
      <el-table-column prop="vmoney" label="会员余额"></el-table-column>
      <el-table-column prop="vipid" label="会员身份证号"></el-table-column>
      <el-table-column label="操作"  width="250" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.vipid)"
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
        <el-form-item label="会员ID">
          <el-input v-model="form.vipnum" autocomplete="off" placeholder="请输入会员ID"></el-input>
        </el-form-item>
        <el-form-item label="会员姓名">
          <el-input v-model="form.vname" autocomplete="off" placeholder="请输入会员姓名"></el-input>
        </el-form-item>
        <el-form-item label="会员性别">
          <el-input v-model="form.vsex" autocomplete="off" placeholder="请输入会员性别"></el-input>
        </el-form-item>
        <el-form-item label="会员电话">
          <el-input v-model="form.vphone" autocomplete="off" placeholder="请输入会员电话"></el-input>
        </el-form-item>
        <el-form-item label="会员等级">
          <el-input v-model="form.vrank" autocomplete="off" placeholder="请输入会员等级"></el-input>
        </el-form-item>
        <el-form-item label="会员余额">
          <el-input v-model="form.vmoney" autocomplete="off" placeholder="请输入会员余额"></el-input>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="form.vipid" autocomplete="off" placeholder="请输入会员身份证号"></el-input>
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
  name: "Vip",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      vipnum:"",
      vname:"",
      vipid:"",
      Esex:"",
      Eage:"",
      Telephone:"",
      Depnum:"",
      USER:"",
      PASSWORD:"",
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
      request.get("/vip/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          vipnum:this.vipnum,
          vname:this.vname,
          vipid:this.vipid
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
    exp(){
      window.open("http://localhost:8088/vip/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    },
    reset(){
      this.vipnum = ""
      this.vname = ""
      this.vipid = ""
      this.load()
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.vipnum)  // [{}, {}, {}] => [1,2,3]
      console.log(ids)
      request.post("/vip/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    save(){
      request.post("/vip",this.form).then(res => {
        if (res) {
          console.log(res)
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    del(id){
      request.delete("/vip/"+id).then(res=>{
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
.ml-5{
  margin-left: 5px;
}
</style>
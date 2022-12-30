<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入员工名称" suffix-icon="el-icon-search" v-model="Ename"></el-input>
      <el-input style="width: 200px" placeholder="请输入员工编号" suffix-icon="el-icon-message" class="ml-5" v-model="Enum"></el-input>
      <el-input style="width: 200px" placeholder="请输入部门名称" suffix-icon="el-icon-position" class="ml-5" v-model="Depnum"></el-input>
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
      <el-table-column prop="enum" label="ID" width="80"></el-table-column>
      <el-table-column prop="ename" label="员工姓名" width="140"></el-table-column>
      <el-table-column prop="esex" label="性别" width="120"></el-table-column>
      <el-table-column prop="eage" label="年龄" width="120"></el-table-column>
      <el-table-column prop="telephone" label="电话"></el-table-column>
      <el-table-column prop="depnum" label="部门名称"></el-table-column>
      <el-table-column prop="epower" label="员工类型"></el-table-column>
      <el-table-column prop="user" label="登录账户"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
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
              @confirm="del(scope.row.enum)"
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
        <el-form-item label="ID">
          <el-input v-model="form.enum" autocomplete="off" placeholder="请输入员工ID"></el-input>
        </el-form-item>
        <el-form-item label="员工姓名">
          <el-input v-model="form.ename" autocomplete="off" placeholder="请输入员工姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.esex" autocomplete="off" placeholder="请输入员工性别"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.eage" autocomplete="off" placeholder="请输入员工年龄"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.telephone" autocomplete="off" placeholder="请输入员工电话"></el-input>
        </el-form-item>
        <el-form-item label="部门名称">
          <el-input v-model="form.depnum" autocomplete="off" placeholder="请输入部门名称"></el-input>
        </el-form-item>
        <el-form-item label="员工类型">
          <el-input v-model="form.epower" autocomplete="off" placeholder="请输入员工类型"></el-input>
        </el-form-item>
        <el-form-item label="登录账户">
          <el-input v-model="form.user" autocomplete="off" placeholder="请输入登录账户名"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" autocomplete="off" placeholder="请输入登录密码"></el-input>
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
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      Enum:"",
      Ename:"",
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
      request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          Enum:this.Enum,
          Ename:this.Ename,
          Depnum:this.Depnum
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
      this.Ename = ""
      this.Enum = ""
      this.Depnum = ""
      this.load()
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.enum)  // [{}, {}, {}] => [1,2,3]
      console.log(ids)
      request.post("/user/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    save(){
      if (this.form.enum == null){
        this.$message.error("员工ID不能为空")
        return
      }
      if (this.form.ename == null){
        this.$message.error("员工姓名不能为空")
        return
      }
      if (this.form.esex == null){
        this.$message.error("员工性别不能为空")
        return
      }
      if (this.form.eage == null){
        this.$message.error("员工年龄不能为空")
        return
      }
      if (this.form.telephone == null){
        this.$message.error("员工电话不能为空")
        return
      }
      if (this.form.depnum == null){
        this.$message.error("部门编号不能为空")
        return
      }
      if (this.form.user == null){
        this.$message.error("登陆账户不能为空")
        return
      }
      if (this.form.password == null){
        this.$message.error("密码不能为空")
        return
      }
      console.log(this.form)
      request.post("/user/update",this.form).then(res => {
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
      request.delete("/user/user/"+id).then(res=>{
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
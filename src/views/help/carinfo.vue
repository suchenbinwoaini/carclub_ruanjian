<template>
  <div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" style="float: right">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="carnum" label="车牌号"></el-table-column>
      <el-table-column prop="polivynum" label="保单编号"></el-table-column>
      <el-table-column prop="settlestatus" label="落户状态"></el-table-column>
      <el-table-column prop="checkstatus" label="验车状态"></el-table-column>
      <el-table-column prop="checktime" label="验车日期"></el-table-column>
      <el-table-column prop="settletime" label="落户日期"></el-table-column>
      <el-table-column prop="vipnum" label="会员编号"></el-table-column>
      <el-table-column prop="policystatus" label="保险状态"></el-table-column>
      <el-table-column prop="cartype" label="车型"></el-table-column>
      <el-table-column label="操作"  width="350" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)" style="margin-left: 5px">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.carnum)"
          >
            <el-button type="danger" slot="reference" style="margin-left: 5px">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button type="warning" style="margin-left: 5px" @click="download(scope.row.carnum)">下载 <i class="el-icon-link"></i></el-button>
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
        <el-form-item label="车牌号">
          <el-input v-model="form.carnum" autocomplete="off" placeholder="请输入车牌号"></el-input>
        </el-form-item>
        <el-form-item label="保单编号">
          <el-input v-model="form.polivynum" autocomplete="off" placeholder="请输入保单编号"></el-input>
        </el-form-item>
        <el-form-item label="落户状态">
          <el-input v-model="form.settlestatus" autocomplete="off" placeholder="请输入落户状态"></el-input>
        </el-form-item>
        <el-form-item label="验车状态">
          <el-input v-model="form.checkstatus" autocomplete="off" placeholder="请输入验车状态"></el-input>
        </el-form-item>
        <el-form-item label="验车时间">
          <el-date-picker
              v-model="form.checktime"
              align="left"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="落户日期">
          <el-date-picker
              v-model="form.settletime"
              align="left"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="会员编号">
          <el-input v-model="form.vipnum" autocomplete="off" placeholder="请输入会员编号"></el-input>
        </el-form-item>
        <el-form-item label="保险状态">
          <el-input v-model="form.policystatus" autocomplete="off" placeholder="请输入保险状态"></el-input>
        </el-form-item>
        <el-form-item label="车型">
          <el-input v-model="form.cartype" autocomplete="off" placeholder="请输入车型"></el-input>
        </el-form-item>
      </el-form>
      <el-upload
          class="upload-demo"
          drag
          action="D:\WEB\carclub_ruanjian\public\template"
          multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
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
  name: "carinfo",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      carnum:"",
      polivynum:"",
      settlestatus:"",
      checkstatus:"",
      checktime:"",
      settletime:"",
      vipnum:"",
      policystatus:"",
      cartype:"",
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
      request.get("/carinfo/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    download(id) {
      console.log(id)
      request.get("/carinfo/"+id).then(res=>{
        if (res.data==null) {
          this.$message.error("下载失败")
        } else {
          console.log(res.data)
          if (res.data.urlup == null){
            this.$message.error("尚未上传该车辆相关文件")
          }
          else {
            this.$message.success("正在下载")
            window.location.href = res.data.urlup
          }
        }
      })
    },
    del(id){
      request.delete("/carinfo/"+id).then(res=>{
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
    handleSelectionChange(){
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
    save(){
      if (this.Carnum == null){
        this.$message.error("车辆编号不能为空")
        return
      }
      request.post("/carinfo",this.form).then(res => {
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
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
  }
}
</script>

<style scoped>

</style>
<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入工作人员工号" suffix-icon="el-icon-search" v-model="ServeID"></el-input>
      <el-input style="width: 200px" placeholder="请输入会员号" suffix-icon="el-icon-message" class="ml-5" v-model="VIPnum"></el-input>
      <el-input style="width: 200px" placeholder="请输入提醒日期" suffix-icon="el-icon-message" class="ml-5" v-model="Ctime"></el-input>
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
      <el-table-column prop="serveid" label="提醒编号"></el-table-column>
      <el-table-column prop="servename" label="提醒内容"></el-table-column>
      <el-table-column prop="vipnum" label="会员编号"></el-table-column>
      <el-table-column prop="ctime" label="提醒时间"></el-table-column>
      <el-table-column prop="ename" label="工作人员姓名"></el-table-column>
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
              @confirm="del(scope.row.serveid)"
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
        <el-form-item label="提醒编号">
          <el-input v-model="form.serveid" autocomplete="off" placeholder="请输入车务ID"></el-input>
        </el-form-item>
        <el-form-item label="提醒内容">
          <el-input v-model="form.servename" autocomplete="off" placeholder="请输入提醒信息"></el-input>
        </el-form-item>
        <el-form-item label="会员编号">
          <el-input v-model="form.vipnum" autocomplete="off" placeholder="请输入会员编号"></el-input>
        </el-form-item>
        <el-form-item label="提醒时间">
          <el-date-picker
              v-model="form.ctime"
              align="left"
              type="date"
              placeholder="选择日期"
              :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="员工姓名">
          <el-input v-model="form.ename" autocomplete="off" placeholder="请输入工人姓名"></el-input>
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
  name: "Command",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      form:{},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
      ServeID:"",
      Servename:"",
      VIPnum:"",
      Ctime:"",
      Ename:"",
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < Date.now();
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '明天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() + 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周后',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    save(){
      if (this.form.serveid==null){
        this.$message.error("提醒编号不能为空")
        return
      }
      if (this.form.ctime==null){
        this.$message.error("提醒时间不能为空")
        return
      }
      if (this.form.vipnum==null){
        this.$message.error("会员编号不能为空")
        return
      }
      if (this.form.servename==null){
        this.$message.error("提醒内容不能为空")
        return
      }
        request.post("/command",this.form).then(res => {
          if (res) {
            console.log(res)
            this.$message.success("发布成功")
            this.dialogFormVisible = false
            this.load()
          } else {
            this.$message.error("发布失败，请联系管理员")
          }
        })
    },
    del(id) {
      request.delete("/command/"+id).then(res=>{
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.serveid)  // [{}, {}, {}] => [1,2,3]
      console.log(ids)
      request.post("/command/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    load(){
      request.get("/command/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          ServeID:this.ServeID,
          VIPnum:this.VIPnum,
          Cdate:this.Cdate,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    reset(){
      this.ServeID=""
      this.VIPnum=""
      this.Ctime=""
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
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    },
  }

}
</script>

<style scoped>

</style>
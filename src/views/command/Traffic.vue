<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入服务编号" suffix-icon="el-icon-search" v-model="serveid"></el-input>
      <el-input style="width: 200px" placeholder="请输入服务日期" suffix-icon="el-icon-message" class="ml-5" v-model="servetime"></el-input>
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
      <el-table-column prop="serveid" label="服务编号"></el-table-column>
      <el-table-column prop="servename" label="车务名称"></el-table-column>
      <el-table-column prop="servetime" label="服务时间"></el-table-column>
      <el-table-column prop="servecon" label="服务信息"></el-table-column>
      <el-table-column prop="vipnum" label="会员编号"></el-table-column>
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
        <el-form-item label="编号">
          <el-input v-model="form.serveid" autocomplete="off" placeholder="请输入服务编号"></el-input>
        </el-form-item>
        <el-form-item label="车务名称">
          <el-input v-model="form.servename" autocomplete="off" placeholder="请输入车务名称"></el-input>
        </el-form-item>
        <el-form-item label="服务时间" placeholder="请输入服务时间">
          <el-date-picker
              v-model="form.servetime"
              align="left"
              type="date"
              placeholder="选择日期"
              :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="服务内容">
          <el-select v-model="form.servecon" placeholder="请选择需要待办的车务">
            <el-option label="车牌待办" value="车牌待办"></el-option>
            <el-option label="保险待办" value="保险待办"></el-option>
            <el-option label="验证待办" value="验证待办"></el-option>
            <el-option label="落户待办" value="落户待办"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="会员编号">
          <el-input v-model="form.vipnum" autocomplete="off" placeholder="请输入会员编号"></el-input>
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
  name: "Traffic",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
      serveid: "",
      servename: "",
      servetime: "",
      servecon: "",
      vipnum:"",
         pickerOptions: {
              disabledDate(time) {
                return time.getTime() > Date.now();
              },
              shortcuts: [{
                text: '今天',
                onClick(picker) {
                  picker.$emit('pick', new Date());
                }
              }, {
                text: '昨天',
                onClick(picker) {
                  const date = new Date();
                  date.setTime(date.getTime() - 3600 * 1000 * 24);
                  picker.$emit('pick', date);
                }
              }, {
                text: '一周前',
                onClick(picker) {
                  const date = new Date();
                  date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
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
    load(){
      request.get("/traffic/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          serveid:this.serveid,
          servetime:this.servetime,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    del(id){
      request.delete("/traffic/"+id).then(res=>{
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    save(){
      request.post("/traffic",this.form).then(res => {
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
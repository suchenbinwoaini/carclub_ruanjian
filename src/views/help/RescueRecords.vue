<template>
  <div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" style="float: left;margin-bottom: 40px">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="rescueid" label="救援编号"></el-table-column>
      <el-table-column prop="rescuetime" label="救援时间"></el-table-column>
      <el-table-column prop="rescueplace" label="救援地点"></el-table-column>
      <el-table-column prop="rescuestatus" label="救援状态"></el-table-column>
      <el-table-column prop="vipnum" label="会员编号"></el-table-column>
      <el-table-column prop="carid" label="救援车牌号"></el-table-column>
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
              @confirm="del(scope.row.rescueid)"
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
        <el-form-item label="救援编号">
          <el-input v-model="form.rescueid" autocomplete="off" placeholder="请输入救援编号"></el-input>
        </el-form-item>
        <el-form-item label="救援时间">
          <el-date-picker
              v-model="form.rescuetime"
              align="left"
              type="date"
              placeholder="选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="救援地点">
          <el-input v-model="form.rescueplace" autocomplete="off" placeholder="请输入救援地点"></el-input>
        </el-form-item>
        <el-form-item label="救援状态">
          <el-select v-model="form.rescuestatus" placeholder="请选择救援状态">
            <el-option label="已救援" value="已救援"></el-option>
            <el-option label="救援中" value="救援中"></el-option>
            <el-option label="呼救" value="呼救"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="会员编号">
          <el-input v-model="form.vipnum" autocomplete="off" placeholder="请输入会员编号"></el-input>
        </el-form-item>
        <el-form-item label="救援车牌号">
          <el-input v-model="form.carid" autocomplete="off" placeholder="请输入救援车牌号"></el-input>
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
  name: "RescueRecords",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      rescueid:"",
      rescuetime:"",
      rescueplace:"",
      rescuestatus:"",
      vipnum:"",
      carid:"",
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
      request.get("/rescue/page",{
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
    del(id){
      request.delete("/rescue/"+id).then(res=>{
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
      if(this.form.rescueid==null || this.form.rescuetime ==null ||
      this.form.rescueplace == null ||this.form.rescuestatus ==null ||
      this.form.vipnum ==null ||this.form.carid==null){
        this.$message.error("存在必填项未填完，请填完后重新提交")
      }
      else {
        request.post("/rescue",this.form).then(res => {
          if (res) {
            console.log(res)
            this.$message.success("添加成功")
            this.dialogFormVisible = false
            this.load()
          } else {
            this.$message.error("保存失败")
          }
        })
      }
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
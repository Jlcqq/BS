<template>
    <div>
      <div class="card" >
        <el-input v-model="data.key" clearable @input="load" style="width: 200px;margin-right: 10px" placeholder="请输入单号"/>
        <el-button type="warning" plain @click="load(data.key)">搜索</el-button>
        <el-button type="danger" plain  @click="delAll">批量删除</el-button>
      </div>
      <div class="card" style="margin-top: 5px" >
        <h3 class="tab-top">{{router.currentRoute.value.meta.name}}</h3>
        <el-table :data="data.tableData" :row-key="getRowKeys" @selection-change="handleSelectionChange" min-height="250" style="width: 100%">
          <el-table-column prop="bhno" label="单号" />
          <el-table-column prop="xinxiname" label="申请公寓" />
          <el-table-column prop="name" label="申请人姓名" />
          <el-table-column prop="tel" label="联系方式" />
          <el-table-column prop="scnum" label="申请时长(月)" />
          <el-table-column prop="price" label="租金" />
          <el-table-column prop="total" label="合计" />
          <el-table-column prop="fkstatus" label="付款状态" />
          <el-table-column prop="status" label="审核状态" />
          <el-table-column prop="savetime" label="申请日期" />
          <el-table-column  label="操作" width="200" >
            <template #default="scope">
              <template v-if="scope.row.status==='待审核' && scope.row.fkstatus==='已付款'">
              <el-button type="success" plain size="small" @click="Shedit(scope.row.id,'通过')">通过</el-button>
              <el-button type="info" plain size="small" @click="Shedit(scope.row.id,'未通过')">拒绝</el-button>
              </template>
              <template v-if="scope.row.status==='通过'">
                <el-button type="success" plain size="small" @click="Tfedit(scope.row.id,'已退房')">退房</el-button>
                <el-button type="info" plain size="small" @click="sjAdd(scope.row.id)">违规添加</el-button>
              </template>

              <el-button type="danger" plain size="small" @click="Delete(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <div v-if="data.total>0" style="display: flex;justify-content: center;margin: 15px;">
          <el-pagination
              @current-change="load()"
              background
              layout="prev, pager, next"
              v-model:page-size="data.pageSize"
              v-model:current-page="data.pageNum"
              :total="data.total"/>
        </div>
      </div>

      <el-dialog title="违规信息新增" v-model="data.formVisible"  size="60%" :close-on-click-modal="false" destroy-on-close>
        <el-form :model="data.form" ref="formRef" :rules="data.rules" label-width="120px" style="padding: 20px">
          <el-form-item label="违规类型">
            <el-radio-group v-model="data.form.typename">
              <el-radio value="房费预期">房费预期</el-radio>
              <el-radio value="设施损坏">设施损坏</el-radio>
              <el-radio value="擅自转租">擅自转租</el-radio>
              <el-radio value="擅自改造">擅自改造</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="违规内容" prop="content">
            <el-input type="textarea" :rows="4" v-model="data.form.content" placeholder="请输入违规内容"/>
          </el-form-item>

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取 消</el-button>
        <el-button type="primary" @click="tijiao">保 存</el-button>
      </span>
        </template>

      </el-dialog>
    </div>
</template>

<script setup>
import {createApp, onMounted, reactive, ref} from "vue";
import request from '@/utils/request.js'
import {ElMessage, ElMessageBox} from "element-plus";
import qs from "qs";
import E from "wangeditor";
import {sendMsg} from "@/utils/crossTagMsg.js";
import router from "@/router/index.js";
const formRef = ref()
const data = reactive({
  admin: JSON.parse(localStorage.getItem('admin') || '{}'),
  key:'',
  key1:'',
  pageNum: 1,
  pageSize: 10,
  total: 0,
  tableData:[],
  shenqingid:'',
  ids:[],
  formVisible: false,
  form: {},
  rules:{
    content: [{ required: true, message: '违规内容不能为空', trigger: 'blur' },],
  }


})

//信息列表
const load =()=>{
  request.post('shenqingList', qs.stringify({
    adminid:data.adminid || '',
    key:data.key,
    key1:data.key1,
    pageNum: data.pageNum,
    pageSize: data.pageSize,
  })).then(res => {
    data.tableData = res.data.list
    data.total = res.data.total
    sendMsg('shenqing更新',1)
  })
}

//申请审核
const Shedit = (id,status) => {
  request.post('shenqingSh', qs.stringify({
    id:id,
    status:status,
  })).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}
//退房
const Tfedit = (id,status) => {
  request.post('shenqingTf', qs.stringify({
    id:id,
    status:status,
  })).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}
// 违规信息新增
const sjAdd = (shenqingid) => {
  data.form = {
    typename:'房费预期'
  }
  data.shenqingid = shenqingid
  data.formVisible = true
}

// 违规信息新增
const tijiao = () => {
  formRef.value.validate((valid=>{
    if (valid){
      request.post('shujuAdd', qs.stringify({
        id:data.form.id,
        shenqingid:data.shenqingid,
        typename:data.form.typename,
        content:data.form.content,
      })).then(res => {
        if (res.code === '200') {
          ElMessage.success('操作成功')
          data.formVisible = false
          load()
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  }))
}


// 删除
const Delete = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', { type: 'warning' }).then(res => {
    request.post('shenqingDel?id=' + id).then(res => {
      if (res.code === '200') {
        load()
        ElMessage.success('操作成功')
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {})
}

//解决跨分页选中后分页取消的情况
const getRowKeys = (row)=>{
  return row.id;
}

//选中需要删除的对象
const handleSelectionChange = (rows)=>{
  data.ids = rows.map(row=>row.id).join()
}
//批量删除
const delAll=()=>{
  if(data.ids.length === 0){
    ElMessage.warning({message:'请选择需要删除的项',plain:true,grouping: true})
    return false
  }else{
    ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', { type: 'warning' }).then(res => {
      request.post('shenqingDeleteAll',qs.stringify({
        vals:data.ids
      })).then(res => {
        if (res.code === '200') {
          load()
          ElMessage.success('操作成功')
        } else {
          ElMessage.error(res.msg)
        }
      })
    }).catch(err => {})
  }

}

load()
</script>
<style scoped>

</style>

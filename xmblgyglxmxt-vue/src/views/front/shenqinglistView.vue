<template>
    <div>
      <div style="width: 95%;margin: 20px auto">
      <div class="card" >
        <el-input v-model="data.key" clearable style="width: 200px;margin-right: 10px" placeholder="请输入申请单号"/>
        <el-button type="warning" plain @click="load(data.key)">搜索</el-button>
        <el-button type="danger" plain @click="delAll">批量删除</el-button>
      </div>
      <div class="card" style="margin-top: 5px" >
        <el-table :data="data.tableData" :row-key="getRowKeys" @selection-change="handleSelectionChange" min-height="250" style="width: 100%">
          <el-table-column type="selection" :reserve-selection="true" width="55" />
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
          <el-table-column label="操作" >
            <template #default="scope">
              <el-button type="info" v-if="scope.row.fkstatus==='待付款'" plain size="small" @click="FkEdit(scope.row)">付款</el-button>
              <el-button type="success" v-if="scope.row.status==='待审核'" plain size="small" @click="Edit(scope.row)">编辑</el-button>
              <el-button type="danger" plain size="small" @click="Delete(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <div class="card" v-if="data.total>0" style="display: flex;justify-content: center">
          <el-pagination
              @current-change="load()"
              background
              layout="prev, pager, next"
              v-model:page-size="data.pageSize"
              v-model:current-page="data.pageNum"
              :total="data.total"/>
        </div>
      </div>
      </div>
      <el-drawer v-model="data.formVisible" :before-close="handleClose"  size="45%" :with-header="false">
        <h3 style="text-align: center">{{data.title}}</h3>
        <el-form :model="data.form" ref="formRef" :rules="data.rules" label-width="120px" style="padding: 20px">
          <el-form-item label="申请人姓名" prop="name">
            <el-input v-model="data.form.name" placeholder="请输入申请人姓名"/>
          </el-form-item>
          <el-form-item label="联系方式" prop="tel">
            <el-input v-model="data.form.tel" placeholder="请输入联系方式"/>
          </el-form-item>
          <el-form-item label="申请入住时长" prop="scnum">
            <el-input v-model="data.form.scnum" placeholder="请输入申请入住时长"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </span>
        </template>

      </el-drawer>

      <el-dialog title="付款" width="50%" v-model="data.fkformVisible" :close-on-click-modal="false">
        <el-form :model="data.form" >
          <h2 style="text-align: center;color: red">付款金额 {{data.form.total}} 元</h2>
          <div style="display: flex;text-align: center;margin-left: 90px;">
            <div>
              <el-image :src="zfb" style="width: 260px;height: 260px;"/>
              <p style="margin: 0">支付宝</p>
            </div>
            <div>
              <el-image :src="wx" style="width: 260px;height: 260px;"/>
              <p style="margin: 0">微信</p>
            </div>

          </div>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.fkformVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit">保 存</el-button>
      </span>
        </template>
      </el-dialog>

    </div>
</template>

<script setup>
import {createApp, onMounted, onUnmounted, reactive, ref} from "vue";
import router from "@/router/index.js";
import request from '@/utils/request.js'
import {ElMessage, ElMessageBox} from "element-plus";
import qs from "qs";
import E from 'wangeditor'
import {listenMsg} from "@/utils/crossTagMsg.js";
import zfb from '@/assets/imgs/zfb.jpg'
import wx from '@/assets/imgs/wechat2.jpg'

//关闭弹框回调函数
const handleClose = () => {
  ElMessageBox.confirm('是否确认需要关闭当前窗口?')
      .then(() => {
        data.formVisible = false
      })
      .catch(() => {
      })
}
//提交是表单校验
const formRef = ref()
// 文件上传的接口地址
const data = reactive({
  member: JSON.parse(localStorage.getItem('member') || '{}'),
  key:'',
  key1:'',
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form: {},
  tableData:[],
  title:'',
  ids:[],
  rules:{
    name: [{ required: true, message: '申请人呢姓名不能为空', trigger: 'blur' },],
    tel: [{ required: true, message: '联系方式不能为空', trigger: 'blur' },],
    scnum: [{ required: true, message: '申请时长不能为空', trigger: 'blur' },],
  },
  fkformVisible:false
})

//信息列表
const load =()=>{
  request.post('shenqingList', qs.stringify({
    memberid:data.member.id,
    key:data.key,
    pageNum: data.pageNum,
    pageSize: data.pageSize,
  })).then(res => {
    data.tableData = res.data.list
    data.total = res.data.total
  })
}

// 申请信息编辑
const Edit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
  data.title='公寓申请编辑'
}

// 编辑提交
const save = () => {
  formRef.value.validate((valid=>{
    if (valid){
      if(!/^[1][3,4,5,7,8,9][0-9]{9}$/.test(data.form.tel)){ElMessage.error("电话号码格式有误");return ;}
      request.post('shenqingEdit', qs.stringify({
        id:data.form.id,
        name:data.form.name,
        tel:data.form.tel,
        scnum:data.form.scnum,
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
//付款
const FkEdit =(row)=>{
  data.fkformVisible=true
  data.form = JSON.parse(JSON.stringify(row))
}

// 付款提交
const submit = () => {
      request.post('shenqingShedit', qs.stringify({
        id:data.form.id,
        fkstatus:'已付款',
      })).then(res => {
        if (res.code === '200') {
          ElMessage.success('操作成功')
          data.fkformVisible = false
          load()
        } else {
          ElMessage.error(res.msg)
        }
      })
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
  }else {
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

const cancelListen = listenMsg(msginfo=>{
  if (msginfo.type==='shenqing更新'){
    load()
  }
})
onUnmounted(cancelListen)

</script>
<style scoped>
</style>

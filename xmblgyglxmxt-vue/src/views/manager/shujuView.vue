<template>
    <div>
      <div class="card" >
        <el-input v-model="data.key" clearable style="width: 200px;margin-right: 10px" @input="load" placeholder="请输入编号"/>
        <el-button type="warning" plain @click="load(data.key)">搜索</el-button>
<!--        <el-button type="primary" @click="Add">新增</el-button>-->
        <el-button type="danger" plain @click="delAll">批量删除</el-button>
      </div>
      <div class="card" style="margin-top: 5px" >
        <h3 class="tab-top">{{router.currentRoute.value.meta.name}}</h3>
        <el-table :data="data.tableData" :row-key="getRowKeys" @selection-change="handleSelectionChange" min-height="250" style="width: 100%">
          <el-table-column type="selection" :reserve-selection="true" width="55" />
          <el-table-column show-overflow-tooltip prop="bhno" label="申请编号" />
          <el-table-column show-overflow-tooltip prop="xinxiname" label="公寓名称" />
          <el-table-column show-overflow-tooltip prop="membername" label="用户信息" />
          <el-table-column prop="typename" label="违规类型" />
          <el-table-column prop="content" label="违规内容" show-overflow-tooltip width="300px"/>
          <el-table-column  label="操作" width="140" >
            <template #default="scope">
              <el-button type="warning" plain size="small" @click="Edit(scope.row)">编辑</el-button>
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

      <el-dialog title="违规信息编辑" v-model="data.formVisible"  size="60%" :close-on-click-modal="false" destroy-on-close>
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

// 文件上传的接口地址
const uploadimgUrl = import.meta.env.VITE_BASE_URL + '/fles/upload'
//提交时表单校验
const formRef = ref()
const data = reactive({
  admin: JSON.parse(localStorage.getItem('admin') || '{}'),
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
    content: [{ required: true, message: '违规内容不能为空', trigger: 'blur' },],
  }

})

//信息列表
const load =()=>{
  request.post('shujuList', qs.stringify({
    key:data.key,
    key1:data.key1,
    pageNum: data.pageNum,
    pageSize: data.pageSize,
  })).then(res => {
    data.tableData = res.data.list
    data.total = res.data.total
    sendMsg('shuju更新',1)
  })
}


// 信息编辑
const Edit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// 删除
const Delete = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', { type: 'warning' }).then(res => {
    request.post('shujuDel?id=' + id).then(res => {
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
      request.post('shujuDeleteAll',qs.stringify({
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

// 活动信息新增
const tijiao = () => {
  formRef.value.validate((valid=>{
    if (valid){
      request.post('shujuAdd', qs.stringify({
        id:data.form.id,
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

// 处理文件上传的钩子(活动)
const handleAvatarSuccess = (res) => {
  data.form.filename = res.data  // res.data就是文件上传返回的文件路径，获取到路径后赋值表单的属性
}

//判断日期
const comp =()=>{
  let year = new Date().getFullYear(); //年
  let month = new Date().getMonth() +1;//月份
  let day = new Date().getDate();//日
  let time = year + '-' + month + '-' + day
  let dqtime = Date.parse(new Date(time));
  let stime2 = Date.parse(new Date(data.form.ksdate));
  let etime2 = Date.parse(new Date(data.form.jsdate));
  if(dqtime > stime2){
    ElMessage.error('开始日期不能小于当前日期');
    data.form.ksdate=""
  }
  if(etime2 < stime2){
    ElMessage.error('截止日期不能小于开始日期');
    data.form.jsdate=""
  }
}

load()
</script>
<style scoped>

</style>

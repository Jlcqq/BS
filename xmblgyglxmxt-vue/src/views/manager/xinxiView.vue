<template>
    <div>
      <div class="card" >
        <el-input v-model="data.key" clearable style="width: 200px;margin-right: 10px" placeholder="请输入名称"/>
        <el-button type="warning" plain @click="load(data.key)">搜索</el-button>
        <el-button type="primary" @click="Add">新增</el-button>
        <el-button type="danger" plain @click="delAll">批量删除</el-button>
      </div>
      <div class="card" style="margin-top: 5px" >
        <h3 class="tab-top">{{router.currentRoute.value.meta.name}}</h3>
        <el-table :data="data.tableData" :row-key="getRowKeys" @selection-change="handleSelectionChange" min-height="250" style="width: 100%">
          <el-table-column type="selection" :reserve-selection="true" width="55" />
          <el-table-column label="图片">
            <template #default="scope">
              <el-image
                  style="width: 50px; height: 50px"
                  :src="JSON.parse(scope.row.filename)[0]"
                  :preview-src-list="JSON.parse(scope.row.filename)"
                  :preview-teleported="true">
              </el-image>
            </template>
          </el-table-column>
          <el-table-column show-overflow-tooltip prop="name" label="名称" />
          <el-table-column prop="typename" label="分类" />
          <el-table-column prop="price" label="价格" />
          <el-table-column prop="lcnum" label="楼层" />
          <el-table-column prop="cxname" label="朝向" />
          <el-table-column prop="mjnum" label="面积" />
          <el-table-column prop="addr" label="地点" />
          <el-table-column prop="status" label="当前状态" />
          <el-table-column prop="savetime" label="发布日期" />
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
      <!--公寓信息新增-->
      <el-dialog :title="data.title" v-model="data.formVisible"  size="60%" :close-on-click-modal="false" destroy-on-close>
        <el-form :model="data.form" ref="formRef" :rules="data.rules" label-width="120px" style="padding: 20px">
          <el-form-item label="图片(5张)" prop="avatar">
            <el-upload class="upload-demo"
                :action="uploadimgUrl"
                accept=".jpg,.png,.jpeg,.JPG,.PNG"
                multiple
                :limit="5"
                 list-type="picture-card"
                 :on-success="handleImgSuccess"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
                v-model:file-list="data.fileListDTP"
            >
              <el-icon><Plus /></el-icon>
            </el-upload>
          </el-form-item>

          <el-form-item label="名称" prop="name">
            <el-input v-model="data.form.name" placeholder="请输入名称"/>
          </el-form-item>
          <el-form-item label="分类" prop="typeid">
            <el-select v-model="data.form.typeid" placeholder="请选择分类">
              <el-option v-for="protype in data.protypelist" :key="protype.id" :label="protype.name" :value="protype.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="价格(元/月)" prop="price">
            <el-input type="number" v-model="data.form.price" placeholder="请输入价格(元/月)"/>
          </el-form-item>

          <el-form-item label="楼层" prop="lcnum">
            <el-input v-model="data.form.lcnum" placeholder="请输入楼层"/>
          </el-form-item>

          <el-form-item label="朝向">
            <el-radio-group v-model="data.form.cxname">
              <el-radio value="朝东">朝东</el-radio>
              <el-radio value="朝南">朝南</el-radio>
              <el-radio value="朝西">朝西</el-radio>
              <el-radio value="朝北">朝北</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="面积" prop="mjnum">
            <el-input v-model="data.form.mjnum" placeholder="请输入面积"/>
          </el-form-item>
          <el-form-item label="地点" prop="addr">
            <el-input v-model="data.form.addr" placeholder="请输入地点"/>
          </el-form-item>

          <el-form-item label="介绍" prop="content">
            <div id="editor"></div>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </span>
        </template>

      </el-dialog>
      <el-dialog v-model="dialogVisible">
        <img w-full :src="dialogImageUrl" alt="Preview Image" style="width: 100%" />
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
import {Plus} from "@element-plus/icons-vue";

//富文本编辑器
let editor;
function initwangeditor(content) { setTimeout(()=> {
  if(!editor){
    //初始化富文编辑器
    editor = new E("#editor")
    editor.config.placeholder="请输入内容"
    //上传图片
    editor.config.uploadFileName='file'
    editor.config.uploadImgServer=  import.meta.env.VITE_BASE_URL + '/fles/wang/upload?type=img'
    //上传视频
    editor.config.uploadVideoName = 'file'
    editor.config.uploadVideoServer= import.meta.env.VITE_BASE_URL + '/fles/wang/upload?type=video'
    editor.create()
  }
  editor.txt.html(content)
},0)

}
//图片预览放大
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
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
  adminlist:[],
  protypelist:[],
  fileListDTP:[],
  title:'',
  ids:[],
  rules:{
    name: [{ required: true, message: '名称不能为空', trigger: 'blur' },],
    adminid: [{ required: true, message: '员工信息不能为空', trigger: 'blur' },],
    typeid: [{ required: true, message: '分类不能为空', trigger: 'blur' },],
    price: [{ required: true, message: '价格不能为空', trigger: 'blur' },],
    lcnum: [{ required: true, message: '楼层不能为空', trigger: 'blur' },],
    mjnum: [{ required: true, message: '面积不能为空', trigger: 'blur' },],
    addr: [{ required: true, message: '地点不能为空', trigger: 'blur' },],
  },

})
//预览图片
const handlePictureCardPreview = (file) => {
  dialogImageUrl.value = file.url;
  dialogVisible.value = true;
}
//删除上传图片
const handleRemove = (file, fileList)=> {
// 通过url找到index并去除列表
  const indexs = data.fileListDTP.map((item, index) => index).filter(index => data.fileListDTP[index].url === file.url);
  for (let index of indexs) {
    data.fileListDTP.splice(index, 1);
  }
  handleImgSuccess('','',data.fileListDTP)
}

//查询分类列表
const getclist=()=>{
  request.post('protypeListAll').then(res=>{
    data.protypelist = res.data
  })
}

//信息列表
const load =()=>{
  request.post('xinxiList', qs.stringify({
    key:data.key,
    key1:data.key1,
    pageNum: data.pageNum,
    pageSize: data.pageSize,
  })).then(res => {
    data.tableData = res.data.list
    data.total = res.data.total
    sendMsg('xinxi更新',1)
  })
}


// 信息新增
const Add = () => {
  data.form = {
    cxname:'朝南'
  }
  data.formVisible = true
  data.uploadUrl=''
  initwangeditor("")
  editor=''
  data.fileListDTP = []
  data.title='公寓信息新增'
}

// 信息编辑
const Edit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
  data.uploadUrl=data.form.filename
  initwangeditor(row.content?data.form.content:'')
  editor=''
  data.title='公寓信息编辑'
  data.fileListDTP = [];
  // 图片 json字符串转数组
  if (data.form.filename && data.form.filename !== '[]') {
    let imageArray = JSON.parse(data.form.filename);
    imageArray.forEach(image => {
      data.fileListDTP.push({
        'name': image,
        'url': image
      })
    });

  }
}

// 新增或编辑提交
const save = () => {
  formRef.value.validate((valid=>{
    if (valid){
  data.form.content = editor.txt.html()
  request.post('xinxiAdd', qs.stringify({
    id:data.form.id,
    name:data.form.name,
    filename:data.form.filename,
    typeid:data.form.typeid,
    price:data.form.price,
    lcnum:data.form.lcnum,
    cxname:data.form.cxname,
    mjnum:data.form.mjnum,
    addr:data.form.addr,
    content:data.form.content,
  })).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      data.formVisible = false
      load()
      editor=''
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
    request.post('xinxiDel?id=' + id).then(res => {
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
      request.post('xinxiDeleteAll',qs.stringify({
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
/*
// 处理文件上传的钩子
const handleAvatarSuccess = (res) => {
  data.form.filename = res.data  // res.data就是文件上传返回的文件路径，获取到路径后赋值表单的属性
}
*/
//文件上传成功后给filename赋值（多图片）
const handleImgSuccess =(res,file,fileListDTP)=>{

  let imageUrlArray = [];
// 图片有上传
  if (fileListDTP.length > 0){
    fileListDTP.forEach(image => {
      if(image.response?.data===undefined){
        imageUrlArray.push(image.url)
      }else{
        imageUrlArray.push(image.response?.data);
      }
      //imageUrlArray.push(res.data);
    });

    data.form.filename = JSON.stringify(imageUrlArray);
  }
}


load()
getclist()
</script>
<style scoped>

</style>

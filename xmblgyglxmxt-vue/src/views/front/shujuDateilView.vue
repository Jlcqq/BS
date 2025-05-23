<template>
  <div>
    <div style="width: 80%;margin: 10px auto;">
      <div >
        <el-row :gutter="10">
          <el-col :span="18">
            <div  class="card">
              <el-tabs v-model="data.activeName" type="card">
                <el-tab-pane label="基本信息" name="first">
                  <el-card class="box-card">
                    <div class="grid-content bg-purple" style="min-height: 350px;">
                      <el-row :gutter="10">
                        <el-col :span="14"><div class="grid-content ep-bg-purple" style="border: 1px solid #60626657;border-radius: 10px;padding: 3px;">
                          <el-image :src="data.shuju.filename" style="width: 100%;height: 400px;border-radius: 10px;" />
                        </div> </el-col>
                        <el-col :span="10">
                          <div class="grid-content ep-bg-purple" >
                            <el-descriptions style="margin-top: 10px"
                                             :title="data.shuju.name"
                                             :column="1"
                                             size="default">
                              <el-descriptions-item label="发布社团:"><el-tag type="primary" size="small"> {{data.shuju.xinxiname }}</el-tag></el-descriptions-item>
                              <el-descriptions-item label="联系人:"> {{data.shuju.lxrname }}</el-descriptions-item>
                              <el-descriptions-item label="联系方式:"> {{data.shuju.tel}}</el-descriptions-item>
                              <el-descriptions-item label="地 址:"> {{data.shuju.addr}}</el-descriptions-item>
                              <el-descriptions-item label="活动日期:"> {{data.shuju.ksdate}} 至 {{data.shuju.jsdate}}</el-descriptions-item>
                              <el-descriptions-item label="人数上限:"> {{data.shuju.slnum}} -- 剩余名额：<el-tag type="danger">{{data.shuju.synum}}</el-tag> </el-descriptions-item>
                              <el-descriptions-item label="活动积分:">{{data.shuju.jfnum}}</el-descriptions-item>
                              <el-descriptions-item label="浏 览:">{{data.shuju.llnum}}</el-descriptions-item>
                              <el-descriptions-item label="发布日期:">{{data.shuju.savetime}}</el-descriptions-item>
                            </el-descriptions>
                            <div style="float: right;margin-right: 10px;">
                            <template v-if="data.shuju.ksstr!=='small' && data.shuju.jsstr!=='big'">
                              <el-button type="success" size="default" @click="bmAdd(data.shuju.id)" v-if="data.shuju.synum>0">报名申请</el-button>
                              <el-button type="success" disabled size="default"  v-else>无名额</el-button>
                            </template>
                            <template v-if="data.shuju.ksstr==='small' || data. shuju.jsstr==='big'">
                              <el-button type="warning" disabled size="default" v-if="data.shuju.ksstr==='small'" >即将开始</el-button>
                              <el-button type="danger" disabled size="default" v-if="data.shuju.jsstr==='big'" >报名结束</el-button>
                            </template>
                            </div>

                          </div></el-col>
                      </el-row>

                    </div>
                  </el-card>

                  <div class="card" style="min-height: 200px;">
                    <h2 style="text-align: center">活动介绍</h2>
                    <div v-html="data.shuju.content" style="line-height: 2"></div>
                  </div>

                </el-tab-pane>
                <el-tab-pane label="活动交流" name="second" >
                  <div style="min-height: 200px;margin-top: 20px;">
                      <div slot="header" class="clearfix" style="text-align: center;margin: 20px auto;">
                        <h2>交流信息</h2>
                      </div>
                      <div v-for="item in data.commentlist"  style="padding: 10px;">
                        <div style="display: flex;margin-top: 15px;min-height: 100px;">
                          <div>
                            <el-image :src="item.member.img" style="width: 70px;height: 70px;border-radius: 100%;" />
                          </div>
                          <div style="padding-left: 15px;line-height: 2;width: 100%">
                            <div >
                              <strong >{{item.member.username}}</strong>
                              <span style="padding-left: 20px;color: #888888FF"><el-icon><Calendar /></el-icon> {{item.savetime}}</span>
                              <el-link v-if="JSON.stringify(data.member)!=='{}' && data.member.id===item.memberid" style="margin-left: 20px;color: red" @click="del(item.id)" :underline="false">
                                <el-icon><Delete /></el-icon> 删除</el-link>
                              <el-link v-if="JSON.stringify(data.member)!=='{}'" style="margin-left: 20px;" @click="toggleInput(item.id)" icon="el-icon-edit" :underline="false">
                                <el-icon><EditPen /></el-icon> 回复</el-link>
                            </div>
                            <span style="padding-top: 20px;"> {{item.content}}</span>
                            <div style="border-top: 1px dashed rgba(140,147,157,0.84);margin-top: 20px;"></div>
                            <!--  二级回复-->
                            <div v-for="comment in item.hflist"   style="padding-top: 30px">
                              <div style="display: flex;min-height: 80px;">
                                <div>
                                  <img :src="comment.member.img" style="width: 70px;height: 70px;border-radius: 100%;">
                                </div>
                                <div style="padding-left: 15px;line-height: 2;width: 100%">
                                  <div >
                                    <strong >{{comment.member.username}}</strong>
                                    <span style="padding-left: 20px;color: #888888FF"> <el-icon><Calendar /></el-icon> {{item.savetime}}</span>
                                    <el-link v-if="JSON.stringify(data.member)!=='{}' && data.member.id===comment.memberid" style="margin-left: 20px;color: red" @click="del(comment.id)" icon="el-icon-delete" :underline="false">
                                      删除</el-link>
                                  </div>
                                  <span style="padding-top: 20px;"> {{comment.content}}</span><br>
                                  <div style="border-top: 1px dashed rgba(140,147,157,0.84);margin-top: 20px;"></div>
                                </div>
                              </div>
                            </div>
                            <div v-if="item.id===data.currentId" style="margin-top: 10px">
                              <el-input  placeholder="输入回复内容" v-model="data.hfcontent" style="width: 90%" />
                              <el-button type="warning" @click="commAdd(item.id)">提交</el-button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="block" v-if="data.total>0" style="margin-top: 40px;text-align: center;margin-bottom: 20px;display: flex;justify-content: center">
                        <el-pagination
                            @current-change="getcommentlist()"
                            background
                            layout="prev, pager, next"
                            v-model:page-size="data.pageSize"
                            v-model:current-page="data.pageNum"
                            :total="data.total"/>
                      </div>

                      <div >
                        <el-input type="textarea" v-model="data.content" placeholder="输入交流内容"  style="width: 100%;"></el-input>
                        <el-button type="primary" @click="commAdd(0)" style="margin: 10px">提交</el-button>
                      </div>


                  </div>

                </el-tab-pane>
              </el-tabs>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple" style="min-height: 350px;">
              <el-card class="box-card">
                <div slot="header" class="clearfix" style="text-align: center;">
                  <h3>其它信息</h3>
                </div>
                <el-divider border-style="dashed" style="margin: 10px 0" />
                <div v-for="(item,i) in data.shujulist" :key="item.id" v-show="i<6" >
                  <div style="display: flex;margin-top: 15px;">
                    <div>
                      <el-image :src="item.filename" @click="xqdateil(item.id)" style="width: 80px;height: 80px;border-radius: 10px;" />
                    </div>
                    <div style="padding-left: 15px;line-height: 2">
                      <div class="overflowShow" >
                        <a href="javascript:void (0)" @click="xqdateil(item.id)" style="text-decoration: none;color: #545c64">
                          <strong >{{item.name}}</strong>
                        </a>
                      </div>
                      <span style="padding-top: 20px;color: #888888FF"> 积 分: {{item.jfnum}} 分</span>
                    </div>
                  </div>
                  <el-divider border-style="dashed" style="margin: 10px 0" />
                </div>
              </el-card>
            </div>
          </el-col>

        </el-row>
      </div>
    </div>

    <!--活动报名新增-->
    <el-drawer v-model="data.formVisible" :before-close="handleClose"  size="45%" :with-header="false">
      <h3 style="text-align: center">活动报名新增</h3>
      <el-form :model="data.form" ref="formRef" :rules="data.rules" label-width="120px" style="padding: 20px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="data.form.name" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="tel">
          <el-input v-model="data.form.tel" placeholder="请输入联系方式"/>
        </el-form-item>
        <el-form-item label="申请理由" prop="content">
          <el-input type="textarea" :rows="3" v-model="data.form.content" placeholder="请输入申请理由"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </span>
      </template>

    </el-drawer>



  </div>
  
</template>

<script setup>
import {createApp, onMounted, onUnmounted, reactive, ref} from "vue";
import request from '@/utils/request.js'
import {ElMessage, ElMessageBox} from "element-plus";
import qs from "qs";
import {useRouter} from "vue-router";
import {listenMsg} from "@/utils/crossTagMsg.js";
import {Calendar, Delete, EditPen, ShoppingCart, Star} from "@element-plus/icons-vue";
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
//创建路由对象
const route = useRouter()
const data = reactive({
  member:JSON.parse(localStorage.getItem('member') || '{}'),
  id:route.currentRoute.value.query.id,
  key:'',
  pageNum:1,
  pageSize:10,
  total:0,
  shuju:{},
  shujulist:[],
  activeName:'first',
  form:{},
  commentlist:[],
  content:'',
  hfcontent:'',
  currentId:0,
  rules:{
    name: [{ required: true, message: '姓名不能为空', trigger: 'blur' },],
    tel: [{ required: true, message: '联系方式不能为空', trigger: 'blur' },],
    content: [{ required: true, message: '申请理由不能为空', trigger: 'blur' },],
  }
})

const cancelListen = listenMsg(msginfo=>{
  if (msginfo.type==='shuju更新'){
    dateil()
  }
  if (msginfo.type==='comment'){
    getcommentlist()
  }
})
onUnmounted(cancelListen)

//信息详情
const dateil =()=>{
  request.post('shujuDateil', qs.stringify({
    id:data.id,
  })).then(res => {
    data.shuju = res.data.shuju
    data.shujulist = res.data.list
  })
}

const xqdateil=(id)=>{
  location.href = '/front/shujuDateil?id='+id
}

//调出新增窗口
const bmAdd = () =>{
  if(JSON.stringify(data.member)==='{}'){
    ElMessage({showClose: true, message: '登录后申请', type: 'error', grouping:true,})
    return false;
  }
  data.formVisible = true
  data.form = data.member
}

// 申请新增提交
const save = () => {
  formRef.value.validate((valid=>{
    if (valid){
      if(!/^[1][3,4,5,7,8,9][0-9]{9}$/.test(data.form.tel)){ElMessage.error("电话号码格式有误");return ;}
      request.post('baomingAdd', qs.stringify({
        memberid:data.member.id,
        shujuid:data.shuju.id,
        name:data.form.name,
        tel:data.form.tel,
        content:data.form.content,
      })).then(res => {
        if (res.code === '200') {
          ElMessage.success('操作成功')
          data.formVisible = false
          data.form = {}
          load()
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  }))
}

//交流交流信息列表
const getcommentlist =()=>{
  request.post('commentList', qs.stringify({
    shujuid:data.id,
    commentid:0,
    pageNum:data.pageNum,
    pageSize:data.pageSize,
  })).then(res => {
    data.commentlist = res.data.list
    data.total = res.data.total
  })
}
//交流交流信息新增
const commAdd =(commentid)=>{
  if(JSON.stringify(data.member)==='{}'){
    ElMessage({showClose: true, message: '登录后申请', type: 'error', grouping:true,})
    return false;
  }
  if(data.hfcontent!==''){
    data.content = data.hfcontent
  }
  if(data.content===''){ElMessage.error("请输入交流内容");return;}
  request.post('commentAdd', qs.stringify({
    memberid:data.member.id,
    shujuid:data.id,
    commentid:commentid,
    content:data.content,
  })).then(res => {
    if(res.code==='200'){
      ElMessage.success("操作成功")
      getcommentlist()
      dateil()
      data.content=''
      data.hfcontent=''
    }else{
      ElMessage.error(res.msg)
      data.content=''
    }
  })
}
//显示交流回复输入框
const toggleInput =(id)=>{
  data.currentId = id;
  data.content=''
}
//删除交流信息
const del = (id)=>{
  request.post('commentDel', qs.stringify({
    id:id,
  })).then(res => {
    if(res.code==='200'){
      ElMessage.success("操作成功")
      getcommentlist()
    }else{
      ElMessage.error(res.msg)
    }
  })
}

dateil()
getcommentlist()

</script>
<style scoped>

</style>

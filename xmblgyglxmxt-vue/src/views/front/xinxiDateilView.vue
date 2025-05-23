<template>
  <div>
    <div style="width: 95%;margin: 0 auto;">
      <div class="card" style="margin: 10px auto">
        <el-link :underline="false" @click="route.push('/')">
          <h3 style="margin: 0"> 首页</h3>
        </el-link>
        <el-divider direction="vertical" />
        <el-link :underline="false" @click="route.push('/front/xinxilist')">
          <h4 style="margin: 0"> 公寓信息列表</h4>
        </el-link>
        <el-divider direction="vertical" border-style="dashed" />
        <span>{{ route.currentRoute.value.meta.name }}</span>
      </div>

      <div >
        <el-row :gutter="10">
          <el-col :span="17">
            <el-card class="box-card">
              <div class="grid-content bg-purple" style="min-height: 350px;">
                <el-row :gutter="20">
                  <el-col :span="13"><div class="grid-content ep-bg-purple" style="border: 1px solid #60626657;border-radius: 10px;padding: 3px;">
                    <el-image style="width: 100%;height: 400px;border-radius: 10px;"
                        :src="data.imgurl"
                        :preview-src-list="data.fileimgAll"
                        :preview-teleported="true"
                         />
                  </div> </el-col>
                  <el-col :span="11">
                    <div class="grid-content ep-bg-purple" >
                      <el-descriptions style="margin-top: 20px"
                          :title="data.xinxi.name"
                          :column="1"
                          size="large">
                        <el-descriptions-item label="分 类:"> <el-tag size="small">{{data.xinxi.typename }}</el-tag></el-descriptions-item>
                        <el-descriptions-item label="楼 层:"> {{data.xinxi.lcnum }} 层</el-descriptions-item>
                        <el-descriptions-item label="朝 向:">{{data.xinxi.cxname}}</el-descriptions-item>
                        <el-descriptions-item label="面 积:">{{data.xinxi.mjnum}} ㎡</el-descriptions-item>
                        <el-descriptions-item label="地  点:">{{data.xinxi.addr}}</el-descriptions-item>
                        <el-descriptions-item label="公寓状态:"><el-tag size="default" type="primary" :effect="'dark'"><b>{{data.xinxi.status}} </b> </el-tag> </el-descriptions-item>
                        <el-descriptions-item label="价  格:"><el-tag size="default" type="danger" :effect="'dark'"><b>{{data.xinxi.price}} 元/月</b> </el-tag> </el-descriptions-item>
                        <el-descriptions-item label="发布日期:">{{data.xinxi.savetime}}</el-descriptions-item>
                      </el-descriptions>

<!--                      <div style="margin-top: 10px">
                        <el-button type="success" size="default" @click="SqAdd(data.xinxi.id)" style="margin-left: 10px;">
                          申请加入</el-button>
                      </div>-->
                  </div></el-col>
                </el-row>
              </div>
            </el-card>
            <div style="margin-top: 20px;" class="card">
              <el-tabs v-model="data.activeName" type="card">
                <el-tab-pane label="详情介绍" name="first">
                  <div v-html="data.xinxi.content" style="line-height: 1.8"></div>
                </el-tab-pane>
              </el-tabs>
            </div>
          </el-col>

          <el-col :span="7">
            <div class="card" style="min-height:200px;margin-bottom: 10px;text-align: center">
              <h3>公寓申请</h3>
              <el-divider style="margin: 10px 0"/>
              <el-form
                  ref="formRef"
                  style="max-width: 100%"
                  :rules="data.rules"
                  label-width="auto"
                  class="demo-ruleForm"
                  size="default"
                  :model="data.form"
              >
                <el-form-item label="申请人" prop="name">
                  <el-input v-model="data.form.name" placeholder="输入申请人"/>
                </el-form-item>
                <el-form-item label="联系方式" prop="tel">
                  <el-input v-model="data.form.tel" placeholder="输入联系方式"/>
                </el-form-item>
                <el-form-item label="申请入住时长" prop="scnum">
                  <el-input type="number" :min="1" :max="12" v-model="data.form.scnum" placeholder="输入申请入住时长(月)"/>
                </el-form-item>
                <el-form-item style="margin-left: 80px">
                  <el-button v-if="data.xinxi.status==='空闲'" type="primary" @click="save">提 交</el-button>
                  <el-button v-else type="danger" disabled>该公寓已租</el-button>
                </el-form-item>
              </el-form>

            </div>

            <div class="grid-content bg-purple" style="min-height: 350px;">
              <el-card class="box-card">
                <div slot="header" class="clearfix" style="text-align: center;">
                  <h3>其它信息</h3>
                </div>
                <el-divider border-style="dashed" style="margin: 10px 0" />
                <div v-for="(item,i) in data.xinxilist" :key="item.id" v-show="i<6" >
                  <div style="display: flex;margin-top: 15px;">
                    <div>
                      <el-image :src="JSON.parse(item.filename)[0]" @click="xqdateil(item.id)" style="width: 80px;height: 80px;border-radius: 10px;" />
                    </div>
                    <div style="padding-left: 15px;line-height: 2">
                      <div class="overflowShow" >
                        <a href="javascript:void (0)" @click="xqdateil(item.id)" style="text-decoration: none;color: #545c64">
                          <strong >{{item.name}}</strong>
                        </a>
                      </div>
                      <el-tag type="danger" :effect="'dark'"> <b>{{ item.price }} 元/月</b> </el-tag>
                      <div>
                        <el-tag type="info" >{{ item.lcnum }} 层</el-tag>
                        <el-divider direction="vertical" />
                        <el-tag type="info" >{{ item.cxname }}</el-tag>
                        <el-divider direction="vertical" />
                        <el-tag type="info" >{{ item.mjnum }} ㎡</el-tag>
                        <el-divider direction="vertical" />
                        <el-tag type="primary" >{{ item.status }}</el-tag>
                      </div>
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



  </div>
  
</template>

<script setup>
import {createApp, onMounted, onUnmounted, reactive, ref} from "vue";
import request from '@/utils/request.js'
import qs from "qs";
import {useRouter} from "vue-router";
import {ElMessage, ElMessageBox} from "element-plus";
//关闭弹框回调函数
const handleClose = () => {
  ElMessageBox.confirm('是否确认需要关闭当前窗口?')
      .then(() => {
        data.formVisible = false
      })
      .catch(() => {
      })
}
//创建路由对象
const route = useRouter()
//提交是表单校验
const formRef = ref()
const data = reactive({
  member:JSON.parse(localStorage.getItem('member') || '{}'),
  id:route.currentRoute.value.query.id,
  key:'',
  pageNum:1,
  pageSize:10,
  total:0,
  xinxi:{},
  xinxilist:[],
  activeName:'first',
  formVisible: false,
  form:{},
  rules:{
    name: [{ required: true, message: '申请人姓名不能为空', trigger: 'blur' },],
    tel: [{ required: true, message: '联系方式不能为空', trigger: 'blur' },],
    scnum: [{ required: true, message: '申请入住时长不能为空', trigger: 'blur' },],
  },
  imgurl:'',
  fileimgAll:[]

})


//信息详情
const dateil =()=>{
  request.post('xinxiDateil', qs.stringify({
    id:data.id,
  })).then(res => {
    data.xinxi = res.data.xinxi
    data.xinxilist = res.data.list
    data.imgurl = JSON.parse(data.xinxi.filename)[0]
    data.fileimgAll = JSON.parse(data.xinxi.filename)
    if (JSON.stringify(data.member)!=='{}'){
      data.form = data.member
    }
  })

}


const xqdateil=(id)=>{
  location.href = '/front/xinxiDateil?id='+id
}
//调出新增窗口
const SqAdd = () =>{
  if(JSON.stringify(data.member)==='{}'){
    ElMessage({showClose: true, message: '登录后申请', type: 'error', grouping:true,})
    return false;
  }
  data.formVisible = true
  data.form = data.member
}

// 申请新增提交
const save = () => {
  if(JSON.stringify(data.member)==='{}'){
    ElMessage({showClose: true, message: '登录后申请', type: 'error', grouping:true,})
    return false;
  }
  formRef.value.validate((valid=>{
    if (valid){
      if(!/^[1][3,4,5,7,8,9][0-9]{9}$/.test(data.form.tel)){ElMessage.error("电话号码格式有误");return ;}
  request.post('shenqingAdd', qs.stringify({
    memberid:data.member.id,
    xinxiid:data.xinxi.id,
    name:data.form.name,
    tel:data.form.tel,
    scnum:data.form.scnum,
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
dateil()

</script>
<style scoped>

</style>

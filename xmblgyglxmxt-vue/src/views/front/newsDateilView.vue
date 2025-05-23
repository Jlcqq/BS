<template>
  <div>
    <div style="width: 90%;margin: 0 auto" >
      <div class="card" style="margin: 10px auto">
        <el-link :underline="false" @click="router.push('/')">
          <h3 style="margin: 0"> 首页</h3>
        </el-link>
        <el-divider direction="vertical" />
        <el-link :underline="false" @click="router.push('/front/newslist')">
          <h4 style="margin: 0"> 新闻资讯信息列表</h4>
        </el-link>
        <el-divider direction="vertical" border-style="dashed" />
        <span>{{ router.currentRoute.value.meta.name }}</span>
      </div>
      <div style="margin: 20px auto;">
        <el-row :gutter="20">
          <el-col :span="16">
            <div class="card" style="min-height: 350px;" >
              <img :src="data.news.fileimg" style="width: 100%;height: 400px;border-radius: 10px;">
              <h3>{{data.news.title}}</h3>
              <div style="color: #888888FF;">
                <span> <el-icon><Calendar /></el-icon> {{data.news.savetime}}</span>
              </div>
              <div v-html="data.news.content" style="line-height: 1.8"></div>
            </div>
          </el-col>

          <el-col :span="8">
            <div class="grid-content bg-purple" style="min-height: 350px;">
              <el-card class="box-card">
                <div slot="header" class="clearfix" style="text-align: center;">
                  <h3>其它信息</h3>
                </div>
                <el-divider/>
                <div v-for="(item,i) in data.newslist" :key="item.id" v-show="i<6" >
                  <div style="display: flex;margin-top: 15px;">
                    <div>
                      <img :src="item.fileimg" @click="xqdateil(item.id)" style="width: 80px;height: 80px;border-radius: 10px;">
                    </div>
                    <div style="padding-left: 15px;line-height: 2">
                      <div class="overflowShow" >
                        <a href="javascript:void (0)" @click="xqdateil(item.id)" style="text-decoration: none;color: #545c64">
                          <strong >{{item.title}}</strong>
                        </a>
                      </div>
                      <span style="padding-top: 20px;color: #888888FF"> <el-icon><Calendar /></el-icon> {{item.savetime}}</span>
                    </div>
                  </div>
                  <div style="border-top: 1px dashed #eee"></div>

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
import {createApp, onMounted, reactive, ref, watch} from "vue";
import route from '@/router'
import request from '@/utils/request.js'
import {ElMessage, ElMessageBox} from "element-plus";
import qs from "qs";
import {useRouter} from "vue-router";

//创建路由对象
const router = useRouter()
const data = reactive({
  member:JSON.parse(localStorage.getItem('member') || '{}'),
  id:router.currentRoute.value.query.id,
  key:'',
  news:{},
  newslist:[],
})
onMounted(() => {
  if (data.id!==data.id){
    dateil()
  }
})



//信息详情信息
const dateil =()=>{
  request.post('newsDateil', qs.stringify({
    id:data.id,
  })).then(res => {
    data.news = res.data.news
    data.newslist = res.data.list
  })
}

const xqdateil=(id)=>{
  location.href = '/front/newsDateil?id='+id
}

  dateil()

</script>
<style scoped>
.overflowShow {
  width: 300px; /* 设置一个固定的宽度 */
  white-space: nowrap; /* 确保文本在一行内显示 */
  overflow: hidden; /* 超出部分隐藏 */
  text-overflow: ellipsis; /* 超出部分显示省略号 */
}
</style>

<template>
  <div>
    <div  style="width: 95%;margin: 10px auto;text-align: center;" >
      <div >
        <el-card shadow="always" class="front-left">
            <div style="margin: 20px auto">
              <el-input clearable v-model="data.key" size="default" placeholder="请输入名称" style="width: 260px;margin-right: 10px;"></el-input>
              <el-button type="warning" size="default" @click="load(data.key,data.key1)">搜索</el-button>
            </div>
          <div>
            <el-button :size="'small'" type="primary" :plain="data.key1!==undefined" size="default" @click="load('','')" style="margin-bottom: 10px;">全部</el-button>
            <el-button :size="'small'" type="primary" :plain="data.key1!==protype.id" size="default" @click="load('',protype.id)" v-for="(protype,i) in data.protypelist" style="margin-bottom: 10px;">
              {{ protype.name }}
            </el-button>
          </div>
        </el-card>

        <el-row :gutter="0">
          <el-col :span="24">
            <el-row :gutter="15">
              <el-col :span="8" v-for="(item,i) in data.xinxilist" :key="i" style="margin: 10px 0;">
                <el-card shadow="hover" style="border-radius: 10px;">
                  <div class="grid-content bg-purple" >
                    <div class="divimgclass">
                      <el-image class="imgclass" :src="JSON.parse(item.filename)[0]" @click="router.push('/front/xinxiDateil?id='+item.id)" style="width: 100%;height: 305px;border-radius: 10px;" />
                    </div>
                    <div style="text-align: left;padding: 5px 5px;" >
                      <el-tag type="danger" :effect="'dark'"> <b>{{ item.price }} 元/月</b> </el-tag>
                      <el-divider direction="vertical" />
                      <el-tag type="info" >{{ item.typename }}</el-tag>
                      <el-divider direction="vertical" />
                      <el-tag type="info" >{{ item.lcnum }} 层</el-tag>
                      <el-divider direction="vertical" />
                      <el-tag type="info" >{{ item.cxname }}</el-tag>
                      <el-divider direction="vertical" />
                      <el-tag type="info" >{{ item.mjnum }} ㎡</el-tag>
                      <el-divider direction="vertical" />
                      <el-tag type="primary" >{{ item.status }}</el-tag>
                      <div >
                        <el-link :underline="false" @click="router.push('/front/xinxiDateil?id='+item.id)">
                          <h3 style="color: #007500;margin: 10px 0"> {{item.name}}</h3>
                        </el-link>
                      </div>
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-col>

<!--          <el-col :span="6">
            <el-card shadow="always" class="front-left">
              <div style="margin-top: 30px;">
                <h3>搜索</h3>
                <div style="min-height: 80px;">
                  <el-input clearable v-model="data.key" placeholder="请输入名称" style="width: 200px;margin-right: 10px;"></el-input>
                  <el-button type="warning" size="small" @click="load(data.key,data.key1)">搜索</el-button>
                </div>
              </div>
            </el-card>
            <el-card shadow="always" class="front-left" style="margin-top: 10px;">
              <div style="margin-top: 30px;margin-bottom: 30px;">
                <h3>分类搜索</h3>
                <div>
                  <el-button :size="'small'" type="success" :plain="data.key1!==undefined" size="default" @click="load('','')" style="margin-bottom: 10px;">全部</el-button>
                  <el-button :size="'small'" type="success" :plain="data.key1!==protype.id" size="default" @click="load('',protype.id)" v-for="(protype,i) in data.protypelist" style="margin-bottom: 10px;">
                    {{ protype.name }}
                  </el-button>
                </div>
              </div>
            </el-card>

          </el-col>-->
        </el-row>
      </div>
      <div class="block" v-if="data.total>0" style="margin: 20px auto;display: flex;justify-content: center">
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
</template>

<script setup>
import {createApp, onMounted, onUnmounted, reactive, ref} from "vue";
import request from '@/utils/request.js'
import qs from "qs";
import {useRouter} from "vue-router";
import {listenMsg} from "@/utils/crossTagMsg.js";
import {User} from "@element-plus/icons-vue";
const router = useRouter()
const data = reactive({
  member: JSON.parse(localStorage.getItem('member') || '{}'),
  key:'',
  key1:'',
  pageNum: 1,
  pageSize: 6,
  total: 0,
  protypelist:[],
  xinxilist:[],
})

//查询分类列表
const getclist=()=>{
request.post('protypeListAll').then(res=>{
  data.protypelist = res.data
})
}

//信息列表
const load =(key,key1)=>{
  data.key1 = key1 || undefined
  request.post('xinxiList', qs.stringify({
    key:data.key,
    key1:data.key1,
    pageNum: data.pageNum,
    pageSize: data.pageSize,
  })).then(res => {
    data.xinxilist = res.data.list
    data.total = res.data.total
  })
}

onMounted(() => {
  getclist()
  load()
})
const cancelListen = listenMsg(msginfo=>{
  if (msginfo.type==='xinxi更新' || msginfo.type==='type更新'){
    load()
    getclist()
  }
})
onUnmounted(cancelListen)

</script>
<style scoped>

</style>

<template>
  <div>
    <div  style="width: 80%;margin: 5px auto;text-align: center;" >
      <div >
        <el-row :gutter="10" >
          <el-col :span="7" style="margin-top: 8px;">
            <el-card shadow="always" class="front-left">
              <div style="margin-top: 20px;">
                <h3>搜索</h3>
                <div style="min-height: 80px;">
                  <el-input clearable v-model="data.key" placeholder="请输入标题" style="width: 200px;margin-right: 10px;"></el-input>
                  <el-button type="warning" size="default" @click="load(data.key,data.key1)">搜索</el-button>
                </div>
              </div>
            </el-card>
            <el-card shadow="always" class="front-left" style="margin-top: 10px;">
              <div>
                <h3>热门活动</h3>
                <div>
                  <el-divider border-style="dashed" style="margin: 10px 0" />
                  <div v-for="(item,i) in data.phlist" :key="item.id" v-show="i<5" >
                    <div style="display: flex;margin-top: 15px;">
                      <div>
                        <el-image :src="item.filename" @click="router.push('/front/shujuDateil?id='+item.id)" style="width: 80px;height: 80px;border-radius: 10px;" />
                      </div>
                      <div style="padding-left: 15px;line-height: 1;text-align: left">
                        <div class="overflowShow" >
                          <a href="javascript:void (0)" @click="router.push('/front/shujuDateil?id='+item.id)" style="text-decoration: none;color: #545c64">
                            <strong >{{item.name}}</strong>
                          </a>
                        </div>
                        <p style="color: #888888FF">浏览：{{item.llnum}} </p>
                        <span  style="color: #888888FF"><el-icon><Calendar /></el-icon> {{item.savetime}}</span>
                      </div>
                    </div>
                    <el-divider border-style="dashed" style="margin: 10px 0" />
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>

          <el-col :span="17" >
              <div class="card" v-for="(item,i) in data.shujulist" :key="i" style="margin: 8px 0;display: flex;width: 100%;height: 90px;gap: 10px">
                <div>
                  <el-image @click="router.push('shujuDateil?id='+item.id)" :src="item.filename" style="width: 130px;height: 90px;border-radius: 5px"/>
                </div>
                <div style="text-align: left;color: #888888FF;width: 100%">

                  <h4 style="margin: 5px 0;cursor: pointer;color: #606266" @click="router.push('shujuDateil?id='+item.id)">
                    <template v-if="item.ksstr!=='small' && item.jsstr!=='big'">
                      <el-tag  type="success" :effect="'dark'">报名中</el-tag>
                    </template>
                    <template v-if="item.ksstr==='small' || item.jsstr==='big'">
                      <el-tag type="warning" :effect="'dark'" v-if="item.ksstr==='small'">即将开始</el-tag>
                      <el-tag type="danger" :effect="'dark'" v-if="item.jsstr==='big'">报名结束</el-tag>
                    </template>
                    {{item.name}}</h4>
                  <div style="margin-top: 8px">
                    <el-tag type="success">{{item.xinxiname}}</el-tag> |
                    活动日期: 【 {{item.ksdate}} 至 {{item.jsdate}} 】
                  </div>
                  <div style="margin-top: 8px">
                    人数上限: {{item.slnum}} 人 |
                    活动积分: {{item.jfnum}} 分 |
                    浏览: {{item.llnum}} 次 |
                    <span><el-icon><Calendar /></el-icon> {{item.savetime}}</span>
                  </div>
                </div>
              </div>

            <div class="block" v-if="data.total>0" style="margin: 10px auto;display: flex;justify-content: center">
              <el-pagination
                  @current-change="load()"
                  background
                  layout="prev, pager, next"
                  v-model:page-size="data.pageSize"
                  v-model:current-page="data.pageNum"
                  :total="data.total"/>
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
import {listenMsg} from "@/utils/crossTagMsg.js";
import {Calendar} from "@element-plus/icons-vue";
const router = useRouter()
const data = reactive({
  member: JSON.parse(localStorage.getItem('member') || '{}'),
  key:'',
  key1:'',
  pageNum: 1,
  pageSize: 8,
  total: 0,
  shujulist:[],
  phlist:[]
})
//查询浏览排行列表
const getphlist=()=>{
request.post('shujuListPh').then(res=>{
  data.phlist = res.data
})
}
//信息列表
const load =()=>{
  request.post('shujuList', qs.stringify({
    key:data.key,
    key1:data.key1,
    pageNum: data.pageNum,
    pageSize: data.pageSize,
  })).then(res => {
    data.shujulist = res.data.list
    data.total = res.data.total
  })
}

onMounted(() => {
  getphlist()
  load()
})
const cancelListen = listenMsg(msginfo=>{
  if (msginfo.type==='shuju更新'){
    load()
    getphlist()
  }
})
onUnmounted(cancelListen)

</script>
<style scoped>

</style>

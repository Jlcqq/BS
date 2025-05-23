<template>
  <div>
    <div  style="width: 75%;min-height: 350px;margin: 10px auto;" class="card">
      <div style="margin: 20px 10px; " >
        <el-input clearable v-model="data.key" @input="load" placeholder="请输入标题" style="width: 260px;margin-right: 10px;"></el-input>
        <el-button type="warning"  @click="load()">搜索</el-button>
      </div>

      <div style="margin:40px 10px">
        <el-divider />
        <div v-for="(item,i) in data.newslist" :key="item.id">
        <span><a href="javascript:void (0)" @click="router.push('/front/newsDateil?id='+item.id)"> <b > {{item.title}}</b></a></span>
          <div style="float: right">
            <span> <el-icon><Calendar /></el-icon>  {{item.savetime}}</span>
          </div>
          <el-divider />
        </div>

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
import {createApp, onMounted, reactive, ref} from "vue";
import request from '@/utils/request.js'
import qs from "qs";
import {useRouter} from "vue-router";
const router = useRouter()
const data = reactive({
  key:'',
  key1:'',
  pageNum: 1,
  pageSize: 9,
  total: 0,
  newslist:[],
})
//信息列表
const load =()=>{
  request.post('newsList', qs.stringify({
    key:data.key,
    key1:data.key1,
    pageNum: data.pageNum,
    pageSize: data.pageSize,
  })).then(res => {
    data.newslist = res.data.list
    data.total = res.data.total
  })
}

onMounted(() => {
  load()
})

</script>
<style scoped>
.text-overflow {
  width: 380px; /* 设置一个固定的宽度 */
  white-space: nowrap; /* 确保文本在一行内显示 */
  overflow: hidden; /* 超出部分隐藏 */
  text-overflow: ellipsis; /* 超出部分显示省略号 */
}
</style>

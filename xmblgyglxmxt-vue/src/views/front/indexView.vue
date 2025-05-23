<template>
    <div >
      <div >
        <el-row :gutter="0">
          <el-col :span="24">
            <div class="grid-content ep-bg-purple" >
              <el-carousel
                  height="550px"
                  direction="vertical"
                  motion-blur>
                <el-carousel-item  v-for="imgadv in data.imgadvlist" :key="imgadv.id" >
                  <el-image @click="router.push(imgadv.url)" :src="imgadv.fileimg" style="width: 100%;height: 100%;"/>
                </el-carousel-item>
              </el-carousel>
            </div>
          </el-col>
        </el-row>
      </div>

<!--      <div style="width: 80%;margin: 20px auto;">
        <el-row :gutter="10">
          <el-col :span="16"><div class="grid-content ep-bg-purple" >
            <div class="card" style="height: 580px;padding: 0;">
              <div style="display: flex;height: 50px;align-items: center">
                <div style="flex: 1;font-size: 17px;;padding-left: 15px;font-weight: bold">最新活动</div>
                <div style="flex: 1"></div>
                <div style="flex: 1;font-size: 15px;padding-right: 15px;text-align: right;cursor: pointer;" @click="router.push('/front/shujulist')">查看更多 <el-icon><ArrowRight /></el-icon></div>
              </div>
              <el-divider border-style="dashed" style="margin: 0"/>
              <div style="margin: 15px 15px;">
                <el-row :gutter="5">
                  <el-col :span="24" v-for="(item,i) in data.slist" v-show="i<4" :key="item.id">
                    <div class="grid-content ep-bg-purple" style="margin-bottom: 10px;">
                      <el-card shadow="never" style="">
                        <div style="margin: 8px 0;display: flex;width: 100%;height: 90px;gap: 10px">
                          <div>
                            <el-image @click="router.push('shujuDateil?id='+item.id)" :src="item.filename" style="width: 130px;height: 90px;border-radius: 5px"/>
                          </div>
                          <div style="text-align: left;color: #606266;width: 100%">

                            <h4 style="margin: 5px 0;cursor: pointer" @click="router.push('shujuDateil?id='+item.id)">
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
                      </el-card>
                    </div>
                  </el-col>
                </el-row>

              </div>
            </div>
          </div></el-col>

          <el-col :span="8"><div class="grid-content ep-bg-purple" >
            <div class="card" style="height: 580px;padding: 0;">
              <div style="display: flex;height: 50px;align-items: center">
                <div style="flex: 1;font-size: 17px;padding-left: 15px;font-weight: bold">最新公告</div>
                <div style="flex: 1"></div>
                <div style="flex: 1;font-size: 15px;padding-right: 15px;text-align: right;cursor: pointer" @click="router.push('/front/newslist')">查看更多 <el-icon><ArrowRight /></el-icon></div>
              </div>
              <el-divider border-style="dashed" style="margin: 0"/>
              <div style="margin-top: 20px;margin-left: 10px">
                <div v-for="(item,i) in data.newslist" :key="item.id" v-show="i<5" >
                  <div style="display: flex;margin-top: 15px;">
                    <div>
                      <el-image :src="item.fileimg" @click="router.push('newsDateil?id='+item.id)" style="width: 80px;height: 80px;border-radius: 10px;" />
                    </div>
                    <div style="padding-left: 15px;line-height: 2">
                      <div class="overflowShow" >
                        <a href="javascript:void (0)" @click="router.push('newsDateil?id='+item.id)" style="text-decoration: none;color: #545c64">
                          <strong >{{item.title}}</strong>
                        </a>
                      </div>
                      <span style="padding-top: 20px;color: #888888FF"> <el-icon><Calendar /></el-icon> {{item.savetime}}</span>
                    </div>
                  </div>
                  <el-divider border-style="dashed" style="margin: 0"/>

                </div>

              </div>
            </div>
          </div>
          </el-col>
        </el-row>
      </div>-->


      <div >
            <div class="" style="min-height: 580px;width: 95%;margin: 20px auto">
              <div style="min-height: 50px;text-align: center;">
                <h1 style="padding-top: 20px;color:#007500;font-size: 25px">公寓信息</h1>
              </div>
              <div style="margin: 15px 15px;">
                <el-row :gutter="15">
                  <el-col :span="8" v-for="(item,i) in data.xlist" :key="i" v-show="i<9" style="margin: 10px 0;">
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

              </div>
            </div>

      </div>

      <div style="min-height: 500px;background-color: #fafafa;width: 95%;margin: 0 auto">
        <div style="text-align: center;margin: 0 auto">
        <h1 style="padding-top: 40px;color: #007500;font-size: 25px">新闻资讯</h1>
        </div>
        <div>
          <div style="margin: 15px 15px;">
            <el-row :gutter="15">
              <el-col :span="8" v-for="(item,i) in data.newslist" :key="i" v-show="i<9" style="margin: 10px 0;">
                  <div class="grid-content bg-purple" >
                    <div style="text-align: left;padding: 5px 5px;" >
                      <div >
                        <el-link :underline="false" @click="router.push('/front/newsDateil?id='+item.id)">
                          <h3 style="margin: 10px 0"> {{item.title}}</h3>
                        </el-link>
                      </div>
                      <div class="line2">
                        <p v-html="item.content"></p>
                      </div>
                      <div style="display: flex;margin-top: 10px">
                        <p><el-icon><Calendar /></el-icon> {{item.savetime}}</p>
                        <div style="margin-left: 220px">
                        <el-button type="primary" size="default" plain @click="router.push('/front/newsDateil?id='+item.id)">详情 <el-icon><Right /></el-icon></el-button>
                        </div>
                      </div>

                    </div>
                  </div>
              </el-col>
            </el-row>

          </div>
        </div>

      </div>

    </div>
</template>

<script setup>
import {onUnmounted, reactive, ref} from "vue";
import request from "@/utils/request.js";
import qs from "qs";
import {useRouter} from "vue-router";
import {listenMsg} from "@/utils/crossTagMsg.js";
import {ArrowRight, Calendar, Right, User} from "@element-plus/icons-vue";
const router = useRouter()
const data = reactive({
  key:'',
  key1:'',
  imgadvlist:[],
  newslist:[],
  xlist:[],
  slist:[],
})
const cancelListen = listenMsg(msginfo=>{
  if (msginfo.type==='轮播图更新' || msginfo.type==='新闻更新' || msginfo.type==='xinxi更新'  || msginfo.type==='type更新' ){
    getindex()
  }
})
onUnmounted(cancelListen)

//首页信息查询
const getindex =()=>{
  request.post('index', qs.stringify({
    key:data.key,
    key1:data.key1,
    istj:'是',
  })).then(res => {
    data.xlist = res.data.xlist
    data.imgadvlist = res.data.imglist
    data.newslist = res.data.nlist
    data.slist = res.data.slist
  })
}
getindex()
</script>

<style scoped>
.text-overflow {
  width: 400px; /* 设置一个固定的宽度 */
  white-space: nowrap; /* 确保文本在一行内显示 */
  overflow: hidden; /* 超出部分隐藏 */
  text-overflow: ellipsis; /* 超出部分显示省略号 */
}

</style>

<template>
<div>
  <div class="common-layout">
    <el-container>
      <el-header >
        <el-menu
            class="el-menu-demo"
            mode="horizontal"
            text-color="#606266"
            active-text-color="#ff9900"
            :default-active="router.currentRoute.value.path"
            router>
          <div style="display: flex;">
            <div style="display: flex;align-items: center;text-align: center;min-width: 350px;justify-content: center;color: #606266;font-size: 16px;font-weight: bold;margin: 0 40px;">
              白领公寓管理系统
            </div>
          </div>
          <el-menu-item index="/front/index">首页</el-menu-item>
          <el-menu-item index="/front/newslist">新闻资讯</el-menu-item>
          <el-menu-item index="/front/xinxilist">公寓信息</el-menu-item>
          <el-menu-item index="/front/messagelist">反馈建议</el-menu-item>
          <div style="display: flex;float: right" v-if="JSON.stringify(data.member)!=='{}'">
            <div style="display: flex;align-items: center;text-align: center;justify-content: center;font-size: 14px;font-weight: bold;margin-left: 300px;">
              <img style="width: 40px; height: 40px; border-radius: 50%" :src="data.member.img" alt="">
              <el-sub-menu index="2">
                <template #title>{{data.member.username}} / {{data.member.name}}</template>
                <el-menu-item index="/front/shenqinglist"> 公寓申请管理</el-menu-item>
                <el-menu-item index="/front/memberpreson"> 个人信息编辑</el-menu-item>
                <el-menu-item index="/front/memberpass"> 个人密码编辑</el-menu-item>
                <el-menu-item @click="logout"> 安全退出</el-menu-item>
              </el-sub-menu>

            </div>
          </div>

          <div style="display: flex;float: right" v-if="JSON.stringify(data.member)==='{}'">
            <div style="display: flex;align-items: center;text-align: center;justify-content: center;color: #ffffff;font-size: 14px;font-weight: bold;margin-left: 250px;">
              <el-button type="success" @click="router.push('/front/memberLogin')">登录</el-button>
              <el-button type="info" plain @click="router.push('/front/memberReg')">注册</el-button>
            </div>
          </div>

        </el-menu>

      </el-header>
      <el-main style="min-height: calc(100vh - 120px);padding: 0;">
        <RouterView @updatemember="updatemember"/>
      </el-main>
      <el-footer style="background-color: #e1f3d8;height: 60px;padding: 0;">
          <div  style="display: flex;padding-top: 20px;font-size: 16px;font-weight: bold;">
            <div style="text-align: left;margin-left: 20px;flex: 1">
            <a href="/login" target="_blank" style="color: #529b2e;text-decoration: none">后台管理中心</a>
            </div>
            <div style="text-align: right;flex: 2;margin-right: 20px;">
            <el-button type="success" v-for="(links,i) in data.listslist" :key="i">
              <el-link :href="links.url" target="_blank" :underline="false" style="color: white"> {{links.name}}</el-link>

            </el-button>
            </div>
          </div>

        <el-backtop :bottom="100">
          <div style=" height: 100%; width: 100%; background-color: var(--el-bg-color-overlay); box-shadow: var(--el-box-shadow-lighter); text-align: center; line-height: 40px; color: #1989fa;">
            <el-icon><ArrowUpBold /></el-icon></div>
        </el-backtop>
      </el-footer>
    </el-container>
  </div>



</div>


</template>

<script setup>
import router from "@/router/index.js";
import {onUnmounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {ArrowDown, ArrowUpBold, CircleCheck, Lock, SwitchButton, User} from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import qs from "qs";
import {listenMsg} from "@/utils/crossTagMsg.js";
const data = reactive({
  member: JSON.parse(localStorage.getItem('member') || '{}'),
  listslist:[],

})

const updatemember = () => {
  data.member = JSON.parse(localStorage.getItem('member') || '{}')
}
const cancelListen = listenMsg(msginfo=>{
  if (msginfo.type==='links更新'){
    getlinks()
  }
})
onUnmounted(cancelListen)

const logout = () => {
  ElMessage.success('操作成功')
  localStorage.removeItem('member')
  //router.push('/front/memberLogin')
  window.location.href = '/front/memberLogin'
}
const getlinks =() =>{
  request.post('linksListAll', qs.stringify({
  })).then(res => {
    data.listslist = res.data
  })
}

getlinks()
</script>

<style scoped>
.el-menu-demo .el-menu-item.is-active {
  /*border-bottom: 2px solid #ffb31a;*/
  border-bottom: none;
}
/*鼠标移入高亮*/
.el-menu-item:hover{
  color: #ff9900 !important;
  background-color: #007500 !important;
  font-weight: bold;

}
/*设置选中菜单的背景色*/
.el-menu-item.is-active{
  background-color: #007500 !important;
  font-weight: bold !important;

}

.el-sub-menu:hover{
  font-weight: bold !important;
  background-color: white !important;
}

</style>
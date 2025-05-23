<template>
<div>
  <div class="common-layout">
    <el-container>

<!-- 头部区域开始-->
      <el-header  style="height: 60px;border-bottom: 1px solid #529b2e;background-color: #529b2e;color: white" >
        <div style="display: flex;">
          <div style="flex: 1;display: flex;align-items: center;">
            <div style="font-size: 18px;font-weight: bold;cursor: pointer" @click="route.push('/manager/home')">后台管理中心</div>
          </div>
          <div style="flex: 1;text-align: left;padding-left: 15px;padding-top: 20px;">
            <el-icon style="font-size: 20px;" @click="handleCollapse"><Switch /></el-icon>
          </div>

          <div style="flex: 3"></div>
          <div style="display: flex;text-align: right;padding-right: 10px; align-items: center;">
            <img style="margin-top: 10px;width: 40px; height: 40px;margin-right: 10px; border-radius: 50%" :src="data.admin.img" alt="">
            <el-dropdown role="navigation">
                <span class="el-dropdown-link">
                  <b style="color: white">{{data.admin.username}} / {{data.admin.name}}
                  <el-icon class="el-icon--right"><arrow-down /></el-icon></b>
                </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="logout"><el-icon><SwitchButton /></el-icon> 安全退出</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>

          </div>

        </div>
      </el-header >
      <el-container>
        <!-- 左侧导航区域开始-->
        <el-aside :width="data.isWidth" >

          <el-menu style="min-height: calc(100vh - 60px)"
                   active-text-color="#529b2e"
                   background-color="#e1f3d8"
                   class="el-menu-vertical-demo"
                   :default-active="router.currentRoute.value.path"
                   text-color="#000"
                   router
                   :collapse="data.isCollapse"
                   :collapse-transition="false" >
            <el-menu-item index="/manager/home"><el-icon><Grid /></el-icon><span>系统首页</span></el-menu-item>
            <template v-if="data.admin.role==='管理员'">

            <el-sub-menu index="1">
              <template #title><el-icon><Reading /></el-icon><span>基础信息管理</span></template>
                <el-menu-item index="/manager/imgadv">轮播图管理</el-menu-item>
                <el-menu-item index="/manager/news">新闻资讯管理</el-menu-item>
                <el-menu-item index="/manager/links">友情链接管理</el-menu-item>
              <el-menu-item index="/manager/message">反馈建议管理</el-menu-item>
            </el-sub-menu></template>
            <el-sub-menu index="6">
              <template #title><el-icon><Reading /></el-icon><span>业务功能管理</span></template>
              <el-menu-item index="/manager/protype">分类信息管理</el-menu-item>
              <el-menu-item index="/manager/xinxi">公寓信息管理</el-menu-item>
              <el-menu-item index="/manager/shenqing">公寓申请管理</el-menu-item>
              <el-menu-item index="/manager/shuju">违规信息管理</el-menu-item>
            </el-sub-menu>

            <el-sub-menu index="3" v-if="data.admin.role==='管理员'">
              <template #title><el-icon><UserFilled /></el-icon><span>用户信息管理</span></template>
              <el-menu-item index="/manager/admin">员工信息管理</el-menu-item>
              <el-menu-item index="/manager/member">用户信息管理</el-menu-item>
            </el-sub-menu>

            <el-sub-menu index="4">
              <template #title><el-icon><Avatar/></el-icon><span>个人信息管理</span></template>
              <el-menu-item index="/manager/person">个人信息</el-menu-item>
              <el-menu-item index="/manager/passuedit">个人密码</el-menu-item>
            </el-sub-menu>

          </el-menu>
        </el-aside>
        <!-- 主体区域开始-->
        <el-main class="content-container" style="background-color: #f8f8ff; padding: 10px">
        <RouterView @updateUser="updateUser"/>
        </el-main>
      </el-container>
    </el-container>
  </div>

</div>


</template>

<script setup>
import router from "@/router/index.js";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {
  ArrowDown,
  Connection,
  ChatSquare,
  Grid,
  House,
  Reading,
  Operation,
  SwitchButton,
  UserFilled,
  View, Switch, Avatar
} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";
const route = useRouter()
const data = reactive({
  isCollapse:false,
  isWidth:'250px',
  admin: JSON.parse(localStorage.getItem('admin') || '{}')
})
const handleCollapse =()=>{
  data.isCollapse = !data.isCollapse
  data.isWidth = data.isCollapse ? '64px' : '250px'
}
const updateUser = () => {
  data.admin = JSON.parse(localStorage.getItem('admin') || '{}')
}

const logout = () => {
  ElMessage.success('操作成功')
  localStorage.removeItem('admin')
  route.push('/login')
}
</script>

<style scoped>
/*取消原有边框*/
.el-menu{
  border-right: none !important;
}

/*设置设置鼠标移入字体高亮*/
.el-menu-item:hover{
  color: #529b2e !important;
  background-color: #b3e19d !important;
}
/*设置选中菜单的背景色*/
.el-menu-item.is-active{
  border-bottom: 1px solid #529b2e;
  font-weight: bold;
  display:block;

}

/*.el-menu-item.is-active::after {
  content: '';
  width: 45%; !* 设置伪元素的宽度为50% *!
  height: 1px;
  display:block;
  !*margin: 0 auto;*!
  border-bottom: 2px solid #529b2e;
  padding: 1px;
}*/

.el-aside{
  transition: width .5s;/*设置淡出效果*/
  box-shadow: 2px 0 6px rgba(0,21,41,.35);/*设置边框阴影*/
}
.el-dropdown-link {
  cursor: pointer !important;
}
.el-menu-vertical-demo, .content-container {
  overflow: auto; /* 使得内容过多时可滚动 */
  height: calc(100vh - 60px); /* 假设顶部有60px的其他内容，调整数值以匹配实际情况 */
}

</style>
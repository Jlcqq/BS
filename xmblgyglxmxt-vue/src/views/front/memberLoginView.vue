<template>
    <div >
      <div class="login-container" >
        <div class="card" style="min-height: 350px;width: 30%;margin: 100px auto">
          <div style="text-align: center">
            <el-divider><h3>用户登录</h3> </el-divider></div>

          <el-form :model="data.form"  ref="formRef" :rules="data.rules" style="padding: 15px;">
            <el-form-item prop="username">
              <el-input :prefix-icon="User" size="large" v-model="data.form.username" placeholder="请输入账号" />
            </el-form-item>

            <el-form-item prop="password">
              <el-input :prefix-icon="Lock" size="large" v-model="data.form.password" placeholder="请输入密码" show-password />
            </el-form-item>
            <el-form-item>
              <el-button size="large" type="warning" plain style="width: 100%" @click="login">登 录</el-button>
            </el-form-item>
          </el-form>
          <div style="text-align: right;">
            还没有账号？请 <a href="/front/memberReg" style="color: red">点击注册</a>
          </div>
        </div>
      </div>
      </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import router from "@/router/index.js";
import request from "@/utils/request.js";
import qs from "qs";
import {Lock, User} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";

//表单校验
const formRef = ref()
// 后台端口
const uploadimgUrl = import.meta.env.VITE_BASE_URL
const data = reactive({
  key:'',
  form:{},
  rules: {
    username: [{ required: true, message: '请输入账号', trigger: 'blur' },],
    password: [{ required: true, message: '请输入密码', trigger: 'blur' },],
  }
})

//提交登录表单
const login=()=>{
  formRef.value.validate((valid=>{
    if(valid){
      request.post('memberLogin',qs.stringify({
        username:data.form.username,
        password:data.form.password,
      })).then(res=>{
        if(res.code==='200'){
          ElMessage({showClose: true, message: '登录成功', type: 'success'});
          localStorage.setItem("member",JSON.stringify(res.data));
          //router.push('/');
          window.location.href = '/'
        }else {
          ElMessage.error(res.msg)
        }
      })

    }
  }))
}
</script>

<style scoped>
.login-container {
  height:calc(100vh - 120px) ;
  overflow:hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fafafa;
 /* background-image: url("@/assets/imgs/bg.png");*/
  background-size: cover;
}
</style>

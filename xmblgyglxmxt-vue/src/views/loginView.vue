<template>
<div>
  <div class="login-container">
    <div class="login-box">
      <div style="font-weight: bold; font-size: 24px; text-align: center; margin-bottom: 30px; color: #1450aa">
        后 台 管 理 中 心
      </div>
      <el-form :model="data.form"  ref="formRef" :rules="data.rules">
        <el-form-item prop="username">
          <el-input :prefix-icon="User" size="large" v-model="data.form.username" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" size="large" v-model="data.form.password" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item prop="role">
          <el-select size="large" style="width: 100%" v-model="data.form.role">
            <el-option value="" label="请选择用户角色"></el-option>
            <el-option value="管理员" label="管理员"></el-option>
            <el-option value="员工" label="员工"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button size="default" type="success" plain style="width: 100%" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
      <div style="text-align: right;">
        还没有账号？请 <a href="javascript:void (0)" @click="router.push('/register')" style="color: red;">注册</a>
      </div>
    </div>

  </div>

</div>
</template>

<script setup>
import { User, Lock } from "@element-plus/icons-vue";
import {onMounted, reactive, ref} from "vue";
import qs from "qs";
import request from '@/utils/request.js'
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

const router = useRouter()
//表单校验
const formRef = ref()
// 后台端口
const uploadimgUrl = import.meta.env.VITE_BASE_URL
const data = reactive({
  key:'',//验证码唯一KEY值
  form:{role:'管理员'},
  rules: {
    username: [{ required: true, message: '请输入账号', trigger: 'blur' },],
    password: [{ required: true, message: '请输入密码', trigger: 'blur' },],
  }
})
//提交登录表单
const login=()=>{
  formRef.value.validate((valid=>{
    if(valid){
      request.post('adminLogin',qs.stringify({
        username:data.form.username,
        password:data.form.password,
        role:data.form.role,
      })).then(res=>{
        if(res.code==='200'){
          ElMessage({showClose: true, message: '登录成功', type: 'success'});
          localStorage.setItem("admin",JSON.stringify(res.data));
          router.push('/manager');
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
  height: 100vh;
  overflow:hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("@/assets/imgs/bj-05.jpg");
  background-size: 100% 100%;
}
.login-box {
  width: 350px;
  padding: 50px 30px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0,.1);
  background-color: rgba(255, 255, 255, 1);
}
</style>
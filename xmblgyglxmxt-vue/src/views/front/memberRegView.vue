<template>
    <div >
      <div class="login-container">
        <div class="card" style="min-height: 400px;width: 30%;margin: 100px auto;">
          <div style="text-align: center;">
            <el-divider><h3>用户注册</h3> </el-divider></div>
          <el-form :model="data.form" label-width="80px" ref="formRef" :rules="data.rules" style="padding: 15px;">
            <el-form-item label="头像" >
              <el-upload
                  class="avatar-uploader"
                  :action="uploadimgUrl"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
                  accept=".jpg,.png,.jpeg,.JPG,.PNG">
                <img  v-if="data.imageUrl" :src="data.imageUrl" class="avatar">
                <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
              </el-upload>
            </el-form-item>
            <el-form-item label="账号" prop="username">
              <el-input  v-model="data.form.username" placeholder="请输入账号" />
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input  v-model="data.form.password" placeholder="请输入密码" show-password />
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input  v-model="data.form.name" placeholder="请输入姓名" />
            </el-form-item>
            <el-form-item label="性别" >
              <el-radio-group v-model="data.form.sex">
                <el-radio value="男">男</el-radio>
                <el-radio value="女">女</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-input type="number" min="1"  v-model="data.form.age" placeholder="请输入年龄" />
            </el-form-item>
            <el-form-item label="联系方式" prop="tel">
              <el-input  v-model="data.form.tel" placeholder="请输入联系方式" />
            </el-form-item>
              <el-button  type="warning" plain style="width: 100%" @click="login">提 交</el-button>
          </el-form>
          <div style="text-align: right;margin-bottom: 10px;">
            以有账号？<a href="/front/memberLogin" style="color: red">返回登录</a>
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
//文件上传的接口地址
const uploadimgUrl = import.meta.env.VITE_BASE_URL + '/fles/upload'
const data = reactive({
  key:'',
  imageUrl:'',
  form:{sex:'男'},
  rules: {
    username: [{required: true, message: '请输入账号', trigger: 'blur'},],
    password: [{required: true, message: '请输入密码', trigger: 'blur'},],
    name: [{required: true, message: '请输入姓名', trigger: 'blur'},],
    age: [{required: true, message: '请输入年龄', trigger: 'blur'},],
    tel: [{required: true, message: '请输入联系方式', trigger: 'blur'},],
  }
})

//提交表单
const login=()=>{
  formRef.value.validate((valid=>{
    if(valid){
      if(!/^[1][3,4,5,7,8,9][0-9]{9}$/.test(data.form.tel)){ElMessage.error("电话号码格式有误");return ;}
      request.post('memberReg',qs.stringify({
        img:data.imageUrl,
        username:data.form.username,
        password:data.form.password,
        name:data.form.name,
        sex:data.form.sex,
        age:data.form.age,
        tel:data.form.tel,
      })).then(res=>{
        if(res.code==='200'){
          ElMessage.success('注册成功，请登录')
          router.push('/front/memberLogin')
        }else {
          ElMessage.error(res.msg)
        }
      })

    }
  }))
}
// 处理文件上传的钩子
const handleAvatarSuccess = (res) => {
  data.imageUrl = res.data  // res.data就是文件上传返回的文件路径，获取到路径后赋值表单的属性
}
</script>

<style scoped>
.login-container {
  height:calc(100vh) ;
  overflow:hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #b3e19d;
 /* background-image: url("@/assets/imgs/bg.png");*/
  background-size: cover;
}
.avatar-uploader .avatar {
  width: 80px !important;
  height: 80px !important;;
  display: block !important;;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}
.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 80px;
  height: 80px;
  text-align: center;
}
</style>

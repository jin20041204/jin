<template>
  <div style="max-width:900px;margin:40px auto">
    <el-card>
      <h2>登录</h2>
      <el-form :model="form" @submit.prevent="onSubmit">
        <el-form-item>
          <el-input v-model="form.username" placeholder="用户名" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.password" type="password" placeholder="密码" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const form = reactive({ username: '', password: '' })

async function onSubmit() {
  try {
    const res = await axios.post('http://localhost:8080/api/v1/auth/login', form)
    // save token in localStorage
    localStorage.setItem('token', res.data.token || '')
    alert('登录成功')
    router.push('/')
  } catch (e) {
    alert(e.response?.data || '登录失败')
  }
}
</script>

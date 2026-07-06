<template>
  <div style="max-width:900px;margin:24px auto;padding:12px">
    <el-card>
      <h2>AI 情感对话助手</h2>
      <div class="chat-window" style="min-height:300px;max-height:500px;overflow:auto;padding:12px;background:#fff6fb;border-radius:12px;margin-top:12px">
        <div v-for="(m, idx) in messages" :key="idx" :style="m.role==='user'?userStyle:botStyle">
          <div style="display:flex;gap:8px;align-items:flex-start">
            <div v-if="m.role==='bot'" style="width:36px;height:36px;border-radius:8px;background:linear-gradient(135deg,#ff9ac2,#ffd166);display:flex;align-items:center;justify-content:center;color:white">AI</div>
            <div style="flex:1">
              <div style="background:#fff;border-radius:10px;padding:10px;box-shadow:0 6px 18px rgba(0,0,0,0.04)">{{ m.text }}</div>
              <div v-if="m.recommendations" style="margin-top:8px;display:flex;gap:8px;flex-wrap:wrap">
                <div v-for="p in m.recommendations" :key="p.id" class="card" style="width:160px;padding:8px">
                  <img :src="p.imageUrl||placeholder" style="width:100%;height:90px;object-fit:cover;border-radius:8px" />
                  <div style="font-weight:700;margin-top:6px">{{ p.title }}</div>
                  <div style="color:var(--muted)">¥ {{ p.price }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div style="display:flex;gap:8px;margin-top:12px">
        <el-input v-model="input" placeholder="和 AI 说点什么（例如：我今天心情很不好）" @keyup.enter.native="send" />
        <el-button type="primary" @click="send">发送</el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const input = ref('')
const messages = ref([])
const placeholder = 'https://via.placeholder.com/320x180.png?text=商品'

const userStyle = { margin:'8px 0', justifyContent: 'flex-end', display: 'flex' }
const botStyle = { margin:'8px 0', justifyContent: 'flex-start', display: 'flex' }

async function send(){
  if (!input.value.trim()) return;
  messages.value.push({ role: 'user', text: input.value })
  const payload = { message: input.value }
  const token = localStorage.getItem('token')
  try{
    const res = await axios.post('http://localhost:8080/api/v1/chat', payload, { headers: token ? { Authorization: 'Bearer ' + token } : {} })
    const data = res.data
    messages.value.push({ role: 'bot', text: data.reply, recommendations: data.recommendations })
  }catch(e){
    messages.value.push({ role: 'bot', text: '服务错误，请稍后再试' })
  }
  input.value = ''
}
</script>

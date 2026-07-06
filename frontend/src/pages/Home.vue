<template>
  <div style="max-width:1000px;margin:24px auto">
    <div class="app-wrap">
      <div class="header">
        <div class="logo">微购</div>
        <div>
          <div class="title">微购 · 甜蜜生活</div>
          <div style="font-size:12px;color:var(--muted)">以购物为主，周边美食为辅，逛买两不误～</div>
        </div>
      </div>

      <div class="search-box">
        <input class="search-input" v-model="q" placeholder="搜索商品或美食，试试“甜点”" />
        <button class="search-btn" @click="goProducts">搜索</button>
      </div>

      <div style="margin-top:18px;display:flex;gap:12px;align-items:center">
        <router-link to="/products"><div class="badge">去逛商店</div></router-link>
        <router-link to="/map"><div class="badge">附近美食</div></router-link>
        <div style="flex:1"></div>
        <router-link to="/login"><el-button size="small">登录</el-button></router-link>
      </div>

      <div style="margin-top:20px">
        <h3 style="margin:0 0 10px 0">精选推荐</h3>
        <div class="grid">
          <div class="card" v-for="n in 6" :key="n">
            <div class="favorite">💗</div>
            <div class="img">🍰</div>
            <h3>萌甜点 {n}</h3>
            <p>入口即化的小幸运 · 本店招牌</p>
            <div class="price">¥ 28</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const q = ref('')
function goProducts(){
  router.push({ path: '/products', query: { q: q.value } })
}
</script>

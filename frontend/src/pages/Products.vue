<template>
  <div style="max-width:1100px;margin:24px auto;padding:12px">
    <h2>商店</h2>
    <div style="margin:10px 0">
      <el-input v-model="keyword" placeholder="搜索商品" clearable style="max-width:420px" @clear="fetchProducts" @input="debouncedFetch"/>
    </div>

    <div class="grid">
      <div class="card" v-for="p in products" :key="p.id">
        <div :class="['favorite', {favorited: favorites.has(p.id)}]" @click="toggleFav(p)">❤</div>
        <div class="img"> <img :src="p.imageUrl || placeholder" style="max-height:120px;object-fit:cover;border-radius:8px;width:100%"/></div>
        <h3>{{ p.title }}</h3>
        <p>{{ p.description }}</p>
        <div class="price">¥ {{ p.price }}</div>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import debounce from 'lodash/debounce'

const products = ref([])
const favorites = ref(new Set())
const placeholder = 'https://via.placeholder.com/320x180.png?text=萌萌商品'
const keyword = ref('')

async function fetchProducts(){
  try{
    const res = await axios.get('http://localhost:8080/api/v1/products')
    products.value = res.data || []
  }catch(e){
    console.error(e)
    products.value = []
  }
}

function toggleFav(p){
  if (favorites.value.has(p.id)) favorites.value.delete(p.id)
  else favorites.value.add(p.id)
}

const debouncedFetch = debounce(()=>fetchProducts(), 500)

onMounted(()=>{
  fetchProducts()
})
</script>

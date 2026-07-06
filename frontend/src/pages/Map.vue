<template>
  <div style="max-width:1100px;margin:24px auto;padding:12px">
    <h2>附近美食（地图占位）</h2>
    <p>地图功能将在后续提交中使用高德地图 SDK 集成；当前为静态演示。</p>
    <div style="display:flex;gap:12px;margin-top:12px">
      <el-card style="flex:1">
        <h3>商家列表</h3>
        <el-list>
          <el-list-item v-for="s in shops" :key="s.id">
            <div style="display:flex;justify-content:space-between;align-items:center">
              <div>
                <div style="font-weight:700">{{ s.name }}</div>
                <div style="font-size:12px;color:var(--muted)">{{ s.address }}</div>
              </div>
              <el-button size="small" type="primary" @click="favShop(s)">收藏</el-button>
            </div>
          </el-list-item>
        </el-list>
      </el-card>
      <el-card style="width:420px;height:320px;display:flex;align-items:center;justify-content:center">
        <div style="color:var(--muted)">地图占位（将展示商家标注）</div>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const shops = ref([
  { id:1, name: '甜点小铺', address: '朝阳区某街道 12 号' },
  { id:2, name: '萌味拉面', address: '海淀区某大厦 B1' }
])

function favShop(s){
  alert('已收藏：' + s.name)
}
</script>

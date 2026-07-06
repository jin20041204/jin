import { createRouter, createWebHistory } from 'vue-router'
import Login from './pages/Login.vue'
import Home from './pages/Home.vue'
import Products from './pages/Products.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/login', component: Login },
  { path: '/products', component: Products },
  { path: '/map', component: () => import('./pages/Map.vue') },
  { path: '/chat', component: () => import('./pages/Chat.vue') }
]

const router = createRouter({ history: createWebHistory(), routes })
export default router

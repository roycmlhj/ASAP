import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  
  {
    path: '/main/',
    name: 'Main',
    component: () => import('../views/Main.vue')
  },
  {
    path:'/board/create/',
    name: 'CreateStudyBoard',
    component: () => import('../views/CreateStudyBoard.vue')
  },
  {
    path:'/board/',
    name: 'StudyBoard',
    component: () => import('../views/StudyBoard.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

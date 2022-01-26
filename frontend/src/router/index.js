import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/accounts/Login.vue'
import Register from '../views/accounts/Register.vue'
import ProfileEdit from '../views/accounts/ProfileEdit.vue'
import StoryBoard from '../views/study/StudyBoard.vue'
import Main from '../views/study/Main.vue'
import CreateStudyBoard from '../views/study/CreateStudyBoard.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/user/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/user/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/user/modify',
    name: 'ProfileEdit',
    component: ProfileEdit
  },
  {
    path: '/study/studyboard',
    name: 'StoryBoard',
    component: StoryBoard
  },
  {
    path: '/study/main',
    name: 'Main',
    component: Main
  },
  {
    path:'/board/create/',
    name: 'CreateStudyBoard',
    component: CreateStudyBoard
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

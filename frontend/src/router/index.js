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
<<<<<<< HEAD
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
=======
    path: '/main/',
    name: 'Main',
    component: () => import('../views/Main.vue')
>>>>>>> 69723850392e5ec24da03c341aa1ba61e2166c6e
  },
  {
    path:'/board/create/',
    name: 'CreateStudyBoard',
<<<<<<< HEAD
    component: CreateStudyBoard
  },

=======
    component: () => import('../views/CreateStudyBoard.vue')
  },
  {
    path:'/board/',
    name: 'StudyBoard',
    component: () => import('../views/StudyBoard.vue')
  }
>>>>>>> 69723850392e5ec24da03c341aa1ba61e2166c6e
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

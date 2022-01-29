import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/accounts/Login.vue'
import Register from '../views/accounts/Register.vue'
import ProfileEdit from '../views/accounts/ProfileEdit.vue'
import StudyBoard from '../views/study/StudyBoard.vue'
import Main from '../views/study/Main.vue'
import CreateStudyBoard from '../views/study/CreateStudyBoard.vue'
import MyPage from '../views/accounts/MyPage.vue'
import Admin from '../views/admins/Admin.vue'


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
    name: 'StudyBoard',
    component: StudyBoard
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
  {
    path:'/user/detail/',
    name: 'MyPage',
    component: MyPage
  },
  {
    path: '/admin/userlist/',
    name: 'Admin',
    component: Admin
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

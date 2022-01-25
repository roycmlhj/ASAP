import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/accounts/Login.vue'
import Register from '../views/accounts/Register.vue'
import ProfileEdit from '../views/accounts/ProfileEdit.vue'


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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

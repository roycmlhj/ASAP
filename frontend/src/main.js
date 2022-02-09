import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import store from './store'

Vue.config.productionTip = false

Vue.use(BootstrapVue)

Vue.use(IconsPlugin)

Vue.component('font-awesome-icon', FontAwesomeIcon)

library.add(
  fas,
)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

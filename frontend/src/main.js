
import Vue from 'vue';
import App from './App.vue';
// You can change this import to `import router from './starterRouter'` to quickly start development from a blank layout.
import router from './router'
import NowUiKit from './plugins/now-ui-kit';
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import store from './store'

Vue.config.productionTip = false

Vue.use(BootstrapVue)

Vue.use(IconsPlugin)

Vue.component('font-awesome-icon', FontAwesomeIcon)

library.add(
  far,
  fas,
)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

Vue.use(NowUiKit);


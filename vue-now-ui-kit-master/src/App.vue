<template>
  <div id="app">
    <nav class="navbar bg-white navbar-expand-lg navbar-relative mb-0">
      <div class="container">
        <div class="navbar-translate">
          <div class="navbar-translate">
            <p class="navbar-brand" style="color: #5E83BA">ASAP</p>
          </div>
        </div>
        <span v-if="isLogin">
          <div data-color="orange" id="navigation" class="navbar-collapse collapse">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <a href="/study/main" class="nav-link">
                  <i class="now-ui-icons objects_globe"></i>
                  <p>My Study</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/study/studyboard" class="nav-link">
                  <i class="now-ui-icons objects_globe"></i>
                  <p>Study Board</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/user/detail" class="nav-link">
                  <i class="now-ui-icons users_circle-08"></i>
                  <p>Profile</p>
                </a>
              </li>
              <li class="nav-item">
                <a type="button" @click="logout" href="#" class="nav-link">
                  <i class="now-ui-icons ui-1_settings-gear-63"></i>
                  <p>Logout</p>
                </a>
              </li>
            </ul>
          </div>
          </span>
          <span v-else>
            <div data-color="orange" id="navigation" class="navbar-collapse collapse">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <a href="/user/register" class="nav-link">
                    <i class="now-ui-icons users_circle-08"></i>
                    <p>Sign up</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a type="button" @click="logout" href="/user/login" class="nav-link">
                    <i class="now-ui-icons ui-1_settings-gear-63"></i>
                    <p>Login</p>
                  </a>
                </li>
              </ul>
            </div>
          </span>
      </div>
    </nav>
    <router-view @login="isLogin = true"/>
  </div>
</template>


<script>
// import navbar from '@/components/Navbar/Navbar.vue';

export default {
  name: 'App',
  components: {
    // navbar,
  },
  data: function () {
    return {
      isLogin: false,
    }
  },
  methods: {
    logout: function () {
      this.isLogin = false
      localStorage.removeItem('jwt')    // 토큰 값 삭제
      this.$router.push({name: 'Login'})
    },
  },
  created: function () {
    const token = localStorage.getItem('jwt')
    if (token) {
      this.isLogin = true
    }
  }
}
</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Nanum+Gothic:wght@400;700;800&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');
#app {
  font-family: 'Nanum Gothic', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-height: 100vh;
}
a {
  color: #C2D2E9;
}
/* #nav {
  padding: 30px;
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
} */
</style>

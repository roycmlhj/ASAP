	
오전 12:45



<template>
  <div id="app">
    <nav class="navbar bg-white navbar-expand-lg navbar-relative mb-0">
      <div class="container">
        <div class="navbar-translate">
          <div class="navbar-translate">
            <p id="asap" class="navbar-brand" style="color: #5e83ba">ASAP</p>
          </div>
        </div>
        <span v-if="isLogin">
          <div
            data-color="orange"
            id="navigation"
            class="navbar-collapse collapse"
          >
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <a href="/study/main" class="nav-link">
                  <i class="now-ui-icons design-2_ruler-pencil"></i>
                  <p>My Study</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/study/studyboard" class="nav-link">
                  <i class="now-ui-icons business_badge"></i>
                  <p>Study Board</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/user/detail" class="nav-link">
                  <i class="now-ui-icons users_circle-08"></i>
                  <p>Profile</p>
                </a>
              </li>
              <li v-if="isAdmin == 1" class="nav-item">
                <a
                  type="/admin/userlist"
                  @click="logout"
                  href="#"
                  class="nav-link"
                >
                  <i class="now-ui-icons loader_gear"></i>
                  <p>Admin</p>
                </a>
              </li>
              <li class="nav-item">
                <a type="button" @click="logout" href="#" class="nav-link">
                  <i class="now-ui-icons ui-1_simple-remove"></i>
                  <p>Logout</p>
                </a>
              </li>
            </ul>
          </div>
        </span>
        <span v-else>
          <div
            data-color="orange"
            id="navigation"
            class="navbar-collapse collapse"
          >
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <a href="/user/register" class="nav-link">
                  <i class="now-ui-icons business_bulb-63"></i>
                  <p>Sign up</p>
                </a>
              </li>
              <li class="nav-item">
                <a
                  type="button"
                  @click="logout"
                  href="/user/login"
                  class="nav-link"
                >
                  <i class="now-ui-icons media-1_button-power"></i>
                  <p>Login</p>
                </a>
              </li>
            </ul>
          </div>
        </span>
      </div>
    </nav>
    <router-view @login="isLogin = true" />
    <footer
      class="footer"
      :class="{ [`footer-${type}`]: type }"
      style="
        background-color: gray;
        color: white;
        text-shadow: -0.3px 0 #000, 0 0.3px #000, 0.3px 0 #000, 0 -0.3px #000;
        padding: 15px 0em 0px 0em;
        box-shadow: 5px 5px 5px 5px gray;
      "
    >
      <div class="container">
        <nav>
          <ul>
            <p style="font-weight: 900; font-size: 25px">
              ASAP |
              <a
                href="https://lab.ssafy.com/s06-webmobile1-sub2/S06P12A107"
                style="color: white"
              >
                GitLab
              </a>
            </p>
          </ul>
        </nav>
        <div class="copyright" style="font-weight: 900; font-size: 13px">
          © {{ year }}, Designed by SSAFY6기 공통 서울1반 6팀,<br />
                    Coded by
          SSAFY6기 공통 서울1반 6팀
        </div>
      </div>
    </footer>
  </div>
</template>


<script>
import jwt_decode from "jwt-decode";

export default {
  name: "App",
  props: {
    backgroundColor: String,
    type: String,
  },
  data: function () {
    return {
      isLogin: false,
      isAdmin: null,
      year: new Date().getFullYear(),
    };
  },
  methods: {
    logout: function () {
      this.isLogin = false;
      localStorage.removeItem("jwt"); // 토큰 값 삭제
      this.$router.push({ name: "Login" });
    },
  },
  created: function () {
    const token = localStorage.getItem("jwt");
    if (token) {
      this.isLogin = true;
      const decoded = jwt_decode(token);
      this.isAdmin = decoded.isAdmin;
    }
  },
};
</script>


<style>
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Nanum+Gothic:wght@400;700;800&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap");
#app {
  font-family: "Nanum Gothic", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-height: 100vh;
}
a {
  color: #c2d2e9;
}
#asap {
  font-size: 30px;
  font-family: "Black Han Sans", sans-serif;
}
</style>


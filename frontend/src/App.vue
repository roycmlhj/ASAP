<template>
  <div id="app">   <!--전체적으로 수정-->
    <nav class="navbar fixed-top bg-white navbar-expand-lg navbar-relative">      <!--수정-->
      <div class="container">
        <div>
          <div>
            <p id="asap" class="navbar-brand" style="color: #5e83ba">ASAP</p>
          </div>
        </div>
        <span v-if="isLogin">
          <div
            data-color="orange"
            id="navigation"
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
              <li v-if="isAdmin" class="nav-item">
                <a
                  type="button"
                  href="/admin/userlist"
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
    <router-view @login="isLogin = true" @isAdmin="isAdmin = true"/>
    <footer
      class="footer"
      :class="{ [`footer-${type}`]: type }"
      style="
        background-color: rgba(245, 244, 244, 0.932);
        text-shadow: -0.3px 0 #000, 0 0.3px #000, 0.3px 0 #000, 0 -0.3px #000;
        padding: 15px 0em 0px 0em;
        box-shadow: 5px 5px 5px 5px gray;
      "
    >
      <div class="container">
        <nav>
          <ul>
            <p style="font-size: 20px">ASAP | 
            <a
              href="https://lab.ssafy.com/s06-webmobile1-sub2/S06P12A107"
              style="color: rgba(143, 140, 140, 0.932)"
            >GitLab</a></p>
          </ul>
        </nav>
        <div class="copyright" style="font-size: 13px">
          © {{ year }}, Designed by SSAFY 6기 공통 서울 1반 7팀,<br />
                    Coded by
          SSAFY6기 공통 서울 1반 7팀
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
      isAdmin: false,
      year: new Date().getFullYear(),
    };
  },
  methods: {
    logout: function () {
      this.isLogin = false; 
      sessionStorage.removeItem("jwt"); // 토큰 값 삭제              // 수정
      this.$router.push({ name: "Login" });
    },
  },
  created: function () {
    const token = sessionStorage.getItem("jwt");                // 수정
    if (token) {
      this.isLogin = true;
      const decoded = jwt_decode(token);
      console.log(decoded, 123)
      if (decoded.isAdmin == 1) {
        this.isAdmin = true;
      } else {
        this.isAdmin = false;
      }
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
  background-color: rgb(rgba(143, 140, 140, 0.932), green, blue);
}
footer {
  min-height: 15vh;
}
</style>


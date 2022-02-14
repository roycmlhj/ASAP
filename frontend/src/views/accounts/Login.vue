<template>
<section>
  <div class="container">
    <p class="asap">ASAP</p>
    <b-card class="col-lg-4">
      <h2>LOGIN</h2>
      <div class="form">
        <b-form-group
        label="이메일 *"
        label-for="email"
        label-align-sm="left"
      >
        <input 
          type="text" 
          id="email"
          class="form-control"
          placeholder="E-mail"
          v-model="user.email"
        >
        </b-form-group>
      <b-form-group
        label="비밀번호 *"
        label-for="email"
        label-align-sm="left"
      >
        <input 
          type="password" 
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          @keyup.enter="login"
        >
      </b-form-group>
        <button type="button" class="btn" @click="login">로그인</button>
        <div class="mt-2">
          <p>Didn't you have ID?</p>
          <router-link :to="{ name: 'Register' }"><p style="color: black;"><strong>Sign up</strong></p></router-link>
        </div>
      </div>
    </b-card>
  </div>
</section>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data: function () {
    return {
      user: {
        email: null,
        password: null,
      },
    }
  },
  methods: {
    login: function () {
      axios({
        method: 'post',
        url: 'http://localhost:8080/api/v1/user/login/',
        data: this.user,
      })
        .then(res => {
          sessionStorage.setItem('jwt', res.data.accessToken)              // 수정
          this.$emit('login')
          this.$router.push({ name: 'Main'})
        })
        .catch(err => {
          alert("사용자 정보를 확인해주세요!")
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
section {
  background-color: #394E79;
  min-height: 100vh;
  display: flex;
  align-items: center;
  margin-top: 3rem;         /* 수정 */
}
.asap {
  font-family: 'Black Han Sans', sans-serif;
  font-size: 80px;
  background: linear-gradient(-45deg, #5E83BA, #C2D2E9, rgb(235, 160, 196), rgb(244, 089, 163));
  text-align: center;
}
.asap {
  background: linear-gradient(-45deg, #5E83BA, #C2D2E9, rgb(235, 160, 196), rgb(244, 089, 163));
  color: transparent;
  -webkit-background-clip: text;
  -moz-background-clip: text;
  text-align: center;
}
h2 {
  font-family: 'Black Han Sans', sans-serif;
}
.form {
  padding: 0px 25px;
  display: flex;
  flex-direction: column;
}
button {
  font-size: 11px;
  height: 35px;
  background-color: rgb(130, 163, 209);
  }
button:hover {
  background-color: rgb(79, 138, 216);
}
</style>
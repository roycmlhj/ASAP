/*
    작성자 : 한슬기
    생성일 : 2022.01.21
    마지막 업데이트 : 2022.01.21
    
    로그인 페이지
 */
<template>
<section>
  <div class="container">
    <h2><strong>Login</strong></h2>
    <div class="form">
      <b-form-input 
        type="text" 
        id="email"
        placeholder="E-mail"
        v-model="user.email"
      >
      </b-form-input>
      <b-form-input 
        type="password" 
        id="password"
        placeholder="Password"
        v-model="user.password"
        @keyup.enter="login"
      >
      </b-form-input>
      <b-form-checkbox class="mt-2" style="float: left;">&nbsp;자동 로그인하기</b-form-checkbox>
      <b-button @click="login" style="background-color: #C59FDF;">Login</b-button>
      <div class="mt-4">
        Didn't you have ID? &nbsp;&nbsp;
        <router-link :to="{ name: 'Register' }"><strong>Sign up</strong></router-link>
      </div>
    </div>
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
          localStorage.setItem('jwt', res.data.accessToken)
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
.container {
  width: 350px;
  height: 400px;
  border: 1px solid black;
}
.form {
  padding: 40px;
  width: 100%;
}
button {
  width: 100%;
  margin-top: 1.5rem;
  border: none;
}
input {
  margin-bottom: 1rem;
}
h2 {
  margin-top: 1rem;
}
a {
  text-decoration: none;
  color: rgb(95, 10, 88)
}
</style>
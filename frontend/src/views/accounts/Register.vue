/*
    회원 가입 페이지

    작성자 : 고주희
    생성일 : 2022.01.21
    마지막 업데이트 : 2022.01.21

    작성자 : 한슬기
    수정일 : 2022.01.24
 */

<template>
<section>
  <div class="container">
    <b-card bg-variant="light">
      <b-form-group
        label-cols-lg="3"
        label="회원가입"
        label-size="lg"
        label-class="font-weight-bold pt-0"
        class="mb-0"
      >
        <b-form-group
          label="E-mail:"
          label-for="email"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input 
            id="email" 
            v-model="user.email"
            :state="emailState"
            aria-describedby="input-live-help input-live-feedback"
          >
          </b-form-input>
          <b-button class="float-right mt-3" variant="info" @click="clickConfirmation">중복확인</b-button>
          <b-form-invalid-feedback id="input-live-feedback">
            아이디는 이메일 형식이어야 합니다.
          </b-form-invalid-feedback>
        </b-form-group>
        <b-form-group
          label="Password:"
          label-for="password"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input 
            id="password"
            type="password" 
            v-model="user.password"
            :state="passwordState"
            aria-describedby="input-live-help input-live-feedback"
          >
          </b-form-input>
          <b-form-invalid-feedback id="input-live-feedback">
            비밀번호는 문자, 숫자, 특수문자 포함 8자 이상이어야 합니다.
          </b-form-invalid-feedback>
        </b-form-group>
        <b-form-group
          label="Password Confirmation:"
          label-for="passwordcheck"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input 
            id="passwordcheck"
            type="password" 
            v-model="passwordcheck"
            :state="passwordcheckState"
            aria-describedby="input-live-help input-live-feedback"
          >
          </b-form-input>
          <b-form-invalid-feedback id="input-live-feedback">
            비밀번호가 일치하지 않습니다.
          </b-form-invalid-feedback>
        </b-form-group>
        <b-form-group
          label="Nickname:"
          label-for="nickname"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input 
            id="nickname"
            v-model="user.nickname"
            :state="nameState"
            aria-describedby="input-live-help input-live-feedback"
          >
          </b-form-input>
          <b-form-invalid-feedback id="input-live-feedback">
            닉네임은 두 글자 이상이어야 합니다.
          </b-form-invalid-feedback>
        </b-form-group>
        <b-form-group
          label="Interests:"
          label-for="interests"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-select name="interests" id="interests" v-model="user.mainCategory">
            <option value="" selected disabled hidden>선택해주세요</option>
            <option v-for="interest in interestsList" :key="interest.id" :value="interest.iname">{{ interest.iname }}</option>
          </b-form-select>
        </b-form-group>
        <b-form-tags 
          input-id="interests" 
          v-model="user.interests"
          tag-variant="primary"
          tag-pills
          placeholder="관심분야를 입력해주세요."
          ></b-form-tags>
        <b-button class="float-right mt-3" variant="info" @click="clickRegister">회원가입</b-button>
      </b-form-group>
    </b-card>
  </div>
</section>
</template>


<script>
import interest from "./assets/interests.json"
import axios from 'axios'

export default {
  name: 'Register',
  data: () => {
    return {
      interestsList : interest,
      user:{
        email: '',
        password: '',
        nickname: '',
        mainCategory: '',
        interests: []
      },
      passwordcheck: '',
    }
  },
  methods: {
    clickConfirmation: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/user/${this.user.email}/`,
        data: this.user.email,
      })
        .then(res => {
          console.log(res)
          alert("사용할 수 있는 이메일입니다.")
        })
        .catch(err => {
          console.log(err)
          alert("이미 사용중인 이메일입니다.")
        })
    },
    clickRegister: function () {
      console.log(this.user)
      if (this.emailState == false || this.passwordState == false || this.passwordcheckState == false || this.nameState == false ||
        this.emailState == null || this.passwordState == null || this.passwordcheckState == null || this.nameState == null
      ) {
        alert("입력 정보를 확인해주세요.")
      } else {
      this.user.interests = '#.vue.json'
      axios({
        method: 'post',
        url: 'http://localhost:8080/api/v1/user/signup/',
        data: this.user,
      })
        .then(res => {
          console.log(res)
          this.$router.push({ name: 'Login'})
          alert("회원가입에 성공하였습니다.")
        })
        .catch(err => {
          console.log(err)
        })
      }
    }
  },
  computed: {
    emailState() {
      var pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (this.user.email.length == 0) {
        return null
      }
      else if (!pattern.test(this.user.email)) {
        return false
      } else {
        return true
      }
    },
    nameState() {
      if (this.user.nickname.length == 0) {
        return null
      } else {
        return this.user.nickname.length > 1 ? true : false
      }
    },
    passwordState() {
      var pattern1 = /[0-9]/;
      var pattern2 = /[a-zA-Z]/;
      var pattern3 = /[~!@#$%<>^&*]/;
      if (this.user.password == 0) {
        return null
      }
      else if (!pattern1.test(this.user.password)||!pattern2.test(this.user.password)||!pattern3.test(this.user.password)||this.user.password.length<8||this.user.password.length>50) {
        return false
      } else {
        return true
      }
    },
    passwordcheckState() {
      if (this.passwordcheck.length == 0) {
        return null
      }
      else if (this.passwordcheck == this.user.password) {
        return true
      } else {
        return false
      }
    }
  },
}
</script>

<style>
</style>

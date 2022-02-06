/*
    회원 가입 페이지

    작성자 : 고주희
    생성일 : 2022.01.21
    마지막 업데이트 : 2022.01.21

    작성자 : 한슬기
    수정일 : 2022.01.24
 */

<template>
<section class="container">
  <h2><strong>회원가입</strong></h2>
  <p>회원가입을 하고, ASAP을 이용해보세요!</p>
  <div class="form">
    <b-form-group
      label="이메일 *"
      label-for="email"
      label-align-sm="left"
    >
      <div class="d-flex">
        <p>
          <b-form-input 
            id="email" 
            v-model="user.email"
            :state="emailState"
            aria-describedby="input-live-help input-live-feedback"
          >
          </b-form-input>
          <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
            아이디는 이메일 형식이어야 합니다.
          </b-form-invalid-feedback>
        </p>
        <b-button class="ml-1" @click="clickConfirmation">중복확인</b-button>
        <b-button class="ml-1" @click="emailAuth">인증번호 발송</b-button>
      </div>
    </b-form-group>
    <b-form-group
      label="인증번호 *"
      label-for="code"
      label-align-sm="left"
    >
      <div class="d-flex">
        <b-form-input 
          id="number" 
          v-model="code"
          style="width: 72%;"
        >
        </b-form-input>
        <b-button class="ml-2" @click="codeConfirm">인증번호 확인</b-button>
      </div>
    </b-form-group>
    <b-form-group
      label="비밀번호 *"
      label-for="password"
      label-align-sm="left"
    >
      <b-form-input 
        id="password"
        type="password" 
        v-model="user.password"
        :state="passwordState"
        aria-describedby="input-live-help input-live-feedback"
      >
      </b-form-input>
      <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
        비밀번호는 문자, 숫자, 특수문자 포함 8자 이상이어야 합니다.
      </b-form-invalid-feedback>
    </b-form-group>
    <b-form-group
      label="비밀번호 확인 *"
      label-for="passwordcheck"
      label-align-sm="left"
    >
      <b-form-input 
        id="passwordcheck"
        type="password" 
        v-model="passwordcheck"
        :state="passwordcheckState"
        aria-describedby="input-live-help input-live-feedback"
      >
      </b-form-input>
      <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
        비밀번호가 일치하지 않습니다.
      </b-form-invalid-feedback>
    </b-form-group>
    <b-form-group
      label="닉네임 *"
      label-for="nickname"
      label-align-sm="left"
    >
      <b-form-input 
        id="nickname"
        v-model="user.nickname"
        :state="nameState"
        aria-describedby="input-live-help input-live-feedback"
      >
      </b-form-input>
      <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
        닉네임은 두 글자 이상이어야 합니다.
      </b-form-invalid-feedback>
    </b-form-group>
    <b-form-group
      label="관심분야 *"
      label-for="interests"
      label-align-sm="left"
      style="width: 50%;"
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
    <b-button class="mt-4" style="width: 100%;" @click="clickRegister">회원가입</b-button>
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
      flag: 0,
      ans: 0,
      code: null,
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
          this.flag = 1
        })
        .catch(err => {
          console.log(err)
          alert("이미 사용중인 이메일입니다.")
          this.flag = 2
        })
    },
    emailAuth: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/email/${this.user.email}/`,
      })
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.log(err, this.user.email)
        })
    },
    codeConfirm: function () {
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/email/email-confirm/`,
        data: this.code,
      })
        .then(res => {
          console.log(res, this.code)
          this.ans = 1
        })
        .catch(err => {
          console.log(err, this.code)
          this.ans = 2
        })
    },
    clickRegister: function () {
      // console.log(this.user)
      if (this.emailState == false || this.passwordState == false || this.passwordcheckState == false || this.nameState == false ||
        this.emailState == null || this.passwordState == null || this.passwordcheckState == null || this.nameState == null ||
        this.flag == 2 || this.ans == 2
      ) {
        alert("입력 정보를 확인해주세요.")
      } else {
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

<style scoped>
  .container {
    width: 35%;
    /* border: 1px solid black; */
  }
  .form {
    padding: 40px;
  }
  button {
    font-size: 11px;
    height: 38px;
    background-color: rgb(130, 163, 209);
  }
  button:hover {
    background-color: rgb(79, 138, 216);
  }
  h2 {
    margin-top: 1rem;
  }
</style>

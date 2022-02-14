<template>
<section>
  <div class="container col-4">
    <p class="asap">ASAP</p>
    <b-card>
    <h2><strong>SIGN UP</strong></h2>
    <div class="form">
      <b-form-group
        label="이메일 *"
        label-for="email"
        label-align-sm="left"
      >                                      <!--input을 b-form-input으로 수정하고, class 삭제-->
        <b-form-input
          id="email" 
          v-model="user.email"
          :state="emailState"
          placeholder="E-mail"
          aria-describedby="input-live-help input-live-feedback"
        >
        </b-form-input>
        <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
          아이디는 이메일 형식이어야 합니다.
        </b-form-invalid-feedback>
        <p class="float-right mb-0">
          <button type="button" class="btn ml-1" @click="clickConfirmation">중복확인</button>
          <button type="button" class="btn ml-1" @click="emailAuth">인증번호 발송</button>
        </p>
      </b-form-group>
      <b-form-group
        label="인증번호 *"
        label-for="code"
        label-align-sm="left"
      >
        <b-form-input 
          id="number" 
          placeholder="Code"
          v-model="code"
        ></b-form-input>
        <button type="button" class="btn float-right" @click="codeConfirm">인증번호 확인</button>
      </b-form-group>
      <b-form-group
        label="비밀번호 *"
        label-for="password"
        label-align-sm="left"
      >
        <b-form-input 
          id="password"
          type="password" 
          placeholder="Password"
          v-model="user.password"
          :state="passwordState"
          aria-describedby="input-live-help input-live-feedback"
        ></b-form-input>
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
          placeholder="Password Confirmation"
          v-model="passwordcheck"
          :state="passwordcheckState"
          aria-describedby="input-live-help input-live-feedback"
        ></b-form-input>
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
          placeholder="Nickname"
          :state="nameState"
          aria-describedby="input-live-help input-live-feedback"
        ></b-form-input>
        <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
          닉네임은 두 글자 이상이어야 합니다.
        </b-form-invalid-feedback>
        <button type="button" class="btn float-right" @click="nicnameCheck">중복체크</button>
      </b-form-group>
      <b-form-group
        label="관심분야 *"
        label-for="interests"
        label-align-sm="left"
        style="width: 70%;"
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
      <button type="button" class="btn mt-4" style="width: 100%;" @click="clickRegister">회원가입</button>
    </div>
    </b-card>
  </div>
</section>
</template>


<script>
import interest from "./assets/interests.json"
import axios from 'axios'
// import { Button } from '@/components/Button.vue'


export default {
  name: 'Register',
  // components: {
  //   [Button.name]: Button
  // },
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
      flag: 2,
      ans: 2,
      nicFlag: 2,
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
    nicnameCheck: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/user/nickname/${this.user.nickname}/`,
      })
        .then(res => {
          this.nicFlag = 1
          console.log(res)
          alert("사용할 수 있는 닉네임입니다.")
        })
        .catch(err => {
          this.nicFlag = 2
          console.log(err, this.user.email)
          alert("사용중인 닉네임입니다.")
        })
    },
    codeConfirm: function () {
      axios({
        method: 'get',    // 수정
        url: `http://localhost:8080/api/v1/email/email-confirm/${this.user.email}?code=${this.code}`,
      })
        .then(res => {
          console.log(res, this.code)
          this.ans = 1
          alert("인증번호 확인이 완료되었습니다.")
        })
        .catch(err => {
          console.log(err, this.code)
          this.ans = 2
          alert("인증번호를 확인해주세요.")
        })
    },
    clickRegister: function () {
      // console.log(this.user)
      if (this.emailState == false || this.passwordState == false || this.passwordcheckState == false || this.nameState == false ||
        this.emailState == null || this.passwordState == null || this.passwordcheckState == null || this.nameState == null ||
        this.nicFlag == 2 || this.ans == 2 || this.flag == 2 
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
  section {
    background-color: #394E79;
    min-height: 100vh;
    display: flex;
    align-items: center;
    margin-top: 4rem;          /* 수정 */
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
  .form {
    padding: 0px 40px;
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
  h1, h2 {
    font-family: 'Black Han Sans', sans-serif;
  }
</style>

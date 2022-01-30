/*
    작성자 : 한슬기
    생성일 : 2022.01.30
    마지막 업데이트 : 2022.01.30
    
    마이페이지
 */
<template>
  <div>
    <b-button id="show-btn" @click="showModal" variant="link">개인정보수정</b-button>
    <b-modal ref="my-modal" 
      ok-only 
      title="Input Password"    
      hide-footer
    >
    <b-form-group
      label="비밀번호 확인"
      label-for="nickname"
      style="padding: 20px;"
    >
      <b-form-input 
        id="password" 
        v-model="user.password" 
        type="password"
      >
      </b-form-input>
      <b-button class="mt-5" variant="dark" style="float: right;" @click="passwordConfirmation">확인</b-button>
    </b-form-group>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'

export default {
  name: 'MyPage',
  data: function () {
    return {
      user: {
        email: 'meme@naver.com',
        password: null,
      },
      userNo: null,
    }
  },
  methods: {
    passwordConfirmation: function () {
      axios({
        method: 'post',
        url: 'http://localhost:8080/api/v1/user/login/',
        data: this.user,
      })
        .then(res => {
          console.log(res)
          this.$router.push({ name: 'ProfileEdit', params: { user_no : this.userNo }})
        })
        .catch(err => {
          alert("비밀번호를 확인해주세요.")
          this.user.password = null,
          console.log(err)
        })
    },
    showModal() {
      this.$refs['my-modal'].show()
    },
  },
  created: function () {
    const token = localStorage.getItem('jwt')
    const decoded = jwt_decode(token)
    this.userNo = decoded.userno
  }
}
</script>

<style scoped>
</style>
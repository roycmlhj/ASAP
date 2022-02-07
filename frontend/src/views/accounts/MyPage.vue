/*
    작성자 : 한슬기
    생성일 : 2022.01.30
    마지막 업데이트 : 2022.02.05
    
    마이페이지
 */
<template>
  <div class="container d-flex flex-wrap" v-if="userInfo">
    <div>
      <p><img src="https://cdn.imweb.me/thumbnail/20200606/09c71b2f94ea5.jpg" alt="default_image"></p>
      <p>{{ userInfo.nickname }}</p>
      <b-button id="show-btn" @click="showModal" variant="link">개인정보수정</b-button>
    </div>
    <div>
      <h5><strong>관심분야</strong></h5>
      <user-interests :interestList="getInterests()"></user-interests>
    </div>
    <div class="d-flex">
      <user-info-table :studyList="studyList"></user-info-table>
      <user-homework-table :homeworkList="homeworkList"></user-homework-table>
    </div>
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
import UserInterests from '@/components/UserInterests.vue'
import UserInfoTable from '@/components/UserInfoTable.vue'
import UserHomeworkTable from '@/components/UserHomeworkTable.vue'

export default {
  name: 'MyPage',
  components: {
    UserInterests,
    UserInfoTable,
    UserHomeworkTable
  },
  data: function () {
    return {
      user: {
        email: null,
        password: null,
      },
      userNo: null,
      userInfo: null,
      studyList: null,
      homeworkList: null,
    }
  },
  methods: {
    setToken: function () {
      const token = localStorage.getItem('jwt')
      const config = {
        Authorization: `JWT ${token}`
      }
      return config
    },
    getUser: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/user/detail/${this.userNo}`,
        headers: this.setToken(),
      })
        .then(res => {
          this.userInfo = res.data.user
          this.studyList = res.data.studyList
          this.homeworkList = res.data.onHomeworkList
          this.user.email = res.data.user.email
          console.log(res.data)
        })
        .catch(err => {
          console.log(err)
        })
    },
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
      this.user.password = null,
      this.$refs['my-modal'].show()
    },
    getInterests: function () {
      this.interestList = this.userInfo.interests.split('#')
      this.interestList.shift()
      return this.interestList
    },
  },
  created: function () {
    const token = localStorage.getItem('jwt')
    const decoded = jwt_decode(token)
    this.userNo = decoded.userno
    this.getUser()
  }
}
</script>

<style scoped>
  img {
    width: 180px;
    height: 200px;
  }
  p {
    margin: 0px;
  }
</style>
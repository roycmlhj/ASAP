/*
    작성자 : 한슬기
    생성일 : 2022.01.30
    마지막 업데이트 : 2022.02.10
    
    마이페이지
 */
<template>
  <div class="container d-flex flex-wrap" v-if="userInfo">
    <div class="d-flex col-12">
      <div class="col-2">
      <p v-if="img">
        <img :src="img" alt="default-img">
      </p>
      <p v-else>
        <img src="./assets/default.png">
      </p>
        <p>{{ userInfo.nickname }}</p>
        <b-button id="show-btn" @click="showModal" variant="link">개인정보수정</b-button>
        <!-- <b-button href="/file/무직타이거.jpg" download>다운로드</b-button> -->
      </div>
      <div class="col-5 d-flex justify-content-center">
        <study-chart :studyTime="studyTime"></study-chart>
      </div>
      <div class="col-5">
        <study-point-bar :userInfo="userInfo"></study-point-bar>
        <h5 style="text-align : start;"><strong>관심분야</strong></h5>
        <user-interests :interestList="getInterests()"></user-interests>
      </div>
    </div>
    <div class="col-6">
      <user-info-table :studyList="studyList"></user-info-table>
    </div>
    <div class="col-6">
      <user-homework-table :homeworkList="homeworkList" :endHomeworkList="endHomeworkList"></user-homework-table>
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
import StudyChart from '@/components/StudyChart.vue'
import StudyPointBar from '@/components/StudyPointBar.vue'

export default {
  name: 'MyPage',
  components: {
    UserInterests,
    UserInfoTable,
    UserHomeworkTable,
    StudyChart,
    StudyPointBar
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
      homeworkList: [],
      endHomeworkList: [],
      studyTime: null,
      img: null,
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
          console.log(res.data)
          this.studyTime = res.data.study_analyze.studyTime
          this.userInfo = res.data.user
          this.user.email = res.data.user.email
          this.img = this.userInfo.image
          this.studyList = res.data.studyList
          for(var i = 0; i<res.data.onHomeworkList.length;i++){ 
            this.homeworkList.push(res.data.onHomeworkList[i]) 
          } 
          for(var j = 0; j<res.data.doneHomeworkList.length;j++){ 
            this.endHomeworkList.push(res.data.doneHomeworkList[j]) 
          }
          this.homeworkList.sort(function(a, b){
            if(a.homework.endDate < b.homework.endDate) return -1;
            if(a.homework.endDate > b.homework.endDate) return 1;
            if(a.homework.endDate == b.homework.endDate) return 0;
          })
          this.endHomeworkList.sort(function(a, b){
            if(a.homework.endDate < b.homework.endDate) return -1;
            if(a.homework.endDate > b.homework.endDate) return 1;
            if(a.homework.endDate == b.homework.endDate) return 0;
          })
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
  h5 {
    margin-top: 7rem;
  }
</style>

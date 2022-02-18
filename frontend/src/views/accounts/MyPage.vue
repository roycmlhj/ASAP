<template>
<section>
  <div v-if="userInfo">
    <div class="page-header clear-filter" filter-color="blue">
      <div class="container">
        <div class="photo-container">
          <p v-if="img">
            <img :src="img" alt="default-img">
          </p>
          <p v-else>
            <img src="./assets/default.png">
          </p>
        </div>
        <h3 class="title">{{ userInfo.email }}</h3>
        <p class="category">{{ userInfo.nickname }}</p>
        <div class="content">
          <div class="social-description">
            <h2> {{ studyList.length }} </h2>
            <p>Study</p>
          </div>
          <div class="social-description">
            <h2>{{ homeworkList.length + endHomeworkList.length }}</h2>
            <p>Homeworks</p>
          </div>
          <div class="social-description">
            <h2>{{ count }}</h2>
            <p>Posts</p>
          </div>
        </div>
        <button type="button" id="show-btn" @click="showModal" class="btn mt-5 btn-round btn-primary btn-lg" href="#button">Edit</button>    <!--수정-->
      </div>
    </div>
    <div class="section">
      <div class="container">
        <h2 class="title" id="title">About me</h2>
        <div class="d-flex col-12 mt-5">
          <div class="col-6 d-flex justify-content-center">
            <study-chart :studyTime="studyTime" :totalTime="totalTime"></study-chart>     <!--수정-->
          </div>
          <div class="col-6">
            <study-point-bar :userInfo="userInfo"></study-point-bar>
            <h5 class="interest" style="text-align : start;"><strong>관심분야</strong></h5>
            <user-interests :interestList="getInterests()"></user-interests>
          </div>
        </div>
        <div class="table d-flex col-12">
          <div class="col-6">
            <user-info-table :studyList="studyList"></user-info-table>
          </div>
          <div class="col-6">
            <user-homework-table :homeworkList="homeworkList" :endHomeworkList="endHomeworkList"></user-homework-table>
          </div>
        </div>
      </div>
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
      <button id="btn" type="button" class="btn mt-5" style="float: right;" @click="passwordConfirmation">확인</button>
    </b-form-group>
    </b-modal>
  </div>
</section>
</template>
<script>
import { Tabs, TabPane } from '@/components';
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import UserInterests from '@/components/UserInterests.vue'
import UserInfoTable from '@/components/UserInfoTable.vue'
import UserHomeworkTable from '@/components/UserHomeworkTable.vue'
import StudyChart from '@/components/StudyChart.vue'
import StudyPointBar from '@/components/StudyPointBar.vue'

export default {
  name: 'MyPage',
  bodyClass: 'profile-page',
  components: {
    // Tabs,
    // TabPane,
    UserInterests,
    UserInfoTable,
    UserHomeworkTable,
    StudyChart,
    StudyPointBar,
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
      count: 0,
      totalTime: 0,
    }
  },
  methods: {
    setToken: function () {
      const token = sessionStorage.getItem('jwt')                        // 수정
      const config = {
        Authorization: `JWT ${token}`
      }
      return config
    },
    getUser: function () {
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/user/detail/${this.userNo}`,
        headers: this.setToken(),
      })
        .then(res => {
          this.studyTime = res.data.study_analyze.studyTime
          console.log(res.data.study_analyze, 888)
          this.totalTime = res.data.study_analyze.total_time            // 수정
          this.userInfo = res.data.user
          this.count = this.userInfo.boardList.length + this.userInfo.studyBoardList.length
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
        })
        .catch(err => {
          console.log(err)
        })
    },
    passwordConfirmation: function () {
      axios({
        method: 'post',
        url: 'https://i6a107.p.ssafy.io:8443/api/v1/user/login/',
        data: this.user,
      })
        .then(res => {
          // console.log(res)
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
    const token = sessionStorage.getItem('jwt')                     // 수정
    const decoded = jwt_decode(token)
    this.userNo = decoded.userno
    this.getUser()
  }
}
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Ubuntu:wght@500&display=swap");
  section {
    margin-bottom: 10rem;
  }
  .container{
    z-index: 2;
    padding-top: 0;                    /* 수정 */
    padding-bottom: 40px;
    margin-top: 5rem;                    /* 수정 */
  }
  .content{
    max-width: 450px;
    margin: 0 auto;
  }
  .social-description{
    display: inline-block;
    max-width: 150px;
    width: 145px;
    text-align: center;
    margin: 15px 0 0px;
  }
  .title{
    text-align: center;
    margin-top: 30px;
  }
  #title{
    font-family: 'Black Han Sans', sans-serif;
  }
  .category{
    text-align: center;
  }
  .photo-container{
    width: 123px;
    height: 123px;
    border-radius: 50%;
    overflow: hidden;
    margin: 0 auto;
    box-shadow: 0px 10px 25px 0px rgba(0, 0, 0, 0.3);
  }
  p {
    margin: 0px;
  }
  h2 {
    margin-bottom: 15px;
    font-family: 'Ubuntu', sans-serif;
  }
  h3, p {
    font-family: 'Ubuntu', sans-serif;
  }
  .study {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  .interest {
    margin-top: 6rem;
  }
  .table {
    margin-top: 9rem;
  }
  #btn {                              /* 수정 */
  font-size: 11px;
  height: 35px;
  background-color: rgb(130, 163, 209);
  }
  #btn:hover {     /* 수정 */
    background-color: rgb(79, 138, 216);
  }
</style>

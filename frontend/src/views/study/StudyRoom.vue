<template>
<section>
  <div class="container d-flex justify-content-space-between" id="screen">
    <div class="d-flex align-items-stretch justify-content-between col-1">
      <div class="icon">
        <font-awesome-icon type="button" class="fa-2x my-2 ml-2" v-b-toggle.sidebar-right icon="edit"/>
          <b-sidebar id="sidebar-right" title="스터디 게시판" right shadow>
            <div class="px-3 py-2">
              <p class="article">
                <article-modal></article-modal>
                <article-list :studyBoardList="studyBoardList" @getArticleList="getArticleList()"></article-list>
              </p>
            </div>
          </b-sidebar>
        <font-awesome-icon type="button" class="clipboard fa-2x my-2" v-b-toggle.sidebar-right-homework icon="clipboard"/>
        <b-sidebar id="sidebar-right-homework" title="과제 게시판" right shadow>
          <div class="px-3 py-2">
            <p class="homework">
              <homework-modal :user="leader"></homework-modal>
              <homework-list :homeworkList="homeworkList" @getHomeworkList="getHomeworkList()"></homework-list>
            </p>
          </div>
        </b-sidebar>
        <font-awesome-icon class="fa-2x my-2" v-b-modal.modal-xl type="button" id="show-btn" @click="calendarShowModal" icon="calendar-week"/>
          <b-modal ref="calendar-modal" id="modal-xl" size="xl" hide-header hide-footer>
            <calendar :studyLeaderno="leader" :studyno="this.$route.params.study_no" :demoEvents="demoEvents"></calendar>
          </b-modal>
        <font-awesome-icon type="button" class="fa-2x my-2" v-b-toggle.sidebar-left-study icon="info-circle"/>
        <b-sidebar id="sidebar-left-study" title="스터디 상세 정보" right shadow>
          <div>
            <div id="information" v-for="study in studyInformation" :key="study.id">
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-tag" class="fa-1x" /> Name</p>
              <p>{{ study.studyname }}</p>
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-user-tag" class="fa-1x" /> Creator</p>
              <p>{{ study.maker }}</p>
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-palette" class="fa-1x" /> Category</p>
              <p>{{ study.category }}</p>
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-list-ol" class="fa-1x"/> Interests</p>
              <p><badge type="info" class="mx-1" v-for="interest in interestList" :key="interest.id"> {{ interest }}</badge></p>
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-newspaper" class="fa-1x"/> Description</p>
              <p>{{ study.description }}</p>
              <update-study-information :studyInformation="study" :interestList="interestList"></update-study-information>
            </div>
          </div>
        </b-sidebar>
        <font-awesome-icon type="button" class="fa-2x my-2" v-b-toggle.sidebar-left-member icon="user-friends"/>
        <b-sidebar id="sidebar-left-member" title="스터디 회원 목록" right shadow>
          <div class="px-5 py-2 mt-3" v-for="members in studyMemberList" :key="members.id">
            <div class="d-flex justify-content-between" v-for="member in members" :key="member.id">
              <div class="d-flex">
                <p v-if="member.image">
                  <img :src="member.image" alt="default-img">
                </p>
                <p v-else>
                  <img src="./../accounts/assets/default.png">
                </p>
                <a id="show-btn" href="#" class="mt-2 ml-3" @click="showModal(member.studyMember)">{{ member.nickname }}</a>
              </div>
              <b-button class="mt-1" v-if="userNumber == getLeader() && member.studyMember.userno != getLeader()" style="font-size: 10px; height: 32px; background-color: rgb(235, 124, 124);" @click="userKick(member.studyMember.userno)">강퇴</b-button>
            </div>
          </div>
          <a class="kick" href="#" @click="userKick(userNumber)">회원탈퇴</a>
        </b-sidebar>
        <b-modal ref="my-modal" :member="member" hide-header hide-footer>
          <div class="d-block text-center">
            <div v-if="member">
              <user-info-modal :member="member" :studylist="studylist"></user-info-modal>
            </div>
          </div>
        </b-modal>
      </div>
    </div>

  </div>
</section>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import ArticleModal from '../../components/ArticleModal.vue'
import ArticleList from '../../components/ArticleList.vue'
import HomeworkModal from '@/components/HomeworkModal.vue'
import HomeworkList from '../../components/HomeworkList.vue'
import UpdateStudyInformation from '@/components/UpdateStudyInformation.vue'
import UserInfoModal from '@/components/UserInfoModal.vue'
import Calendar from '@/components/Calendar.vue'
import {Badge} from '../../components'

export default {
  name: 'StudyRoom',
  components: {
    ArticleModal,
    ArticleList,
    HomeworkModal,
    HomeworkList,
    UpdateStudyInformation,
    UserInfoModal,
    Calendar,
    [Badge.name]: Badge
  },
  data: function () {
    return {
      value: '',
      context: null,
      studyBoardList: null,
      homeworkList: null,
      studyMemberList: null,
      studyInformation: null,
      interestList: null,
      studylist: null,
      member: null,
      leader: null,
      demoEvents: [],
      userInfo: {
        userno: null,
        studyno: this.$route.params.study_no
      },
      userNumber: null,
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
    showModal: function (user) {
      this.$refs['my-modal'].show()
      this.getUserInformation(user)
    },
    calendarShowModal: function () {
      this.$refs['calendar-modal'].show()
    },
    getStudyInformation: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/study/detail/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data, 888)
          this.studyInformation = res.data
          this.interestList = this.studyInformation.study.interests.split("#")
          this.interestList.shift(0)
          console.log(this.interestList)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getArticleList: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/study_board/boardlist/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data.studyBoardList, 456)
          this.studyBoardList = res.data.studyBoardList
        })
        .catch(err => {
          console.log(err)
        })
    },
    getHomeworkList: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/homework/homeworklist/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          this.homeworkList = res.data.homeworkList
          console.log(this.homeworkList)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getStudyMemberList: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/study/memberList/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          this.studyMemberList = res.data
          console.log(this.studyMemberList, 111)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserInformation: function (user) {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/admin/${user.userno}/`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res)
          this.member = res.data.user
          this.studylist = res.data.studyList
        })
        .catch(err => {
          console.log(err)
        })
    },
     getCalendar: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/study/calendar/${this.$route.params.study_no}`,
      })
      .then(res => {
        const homeworkList = res.data.homeworkList
        for(var i = 0; i < homeworkList.length;i++){
          this.demoEvents.push ({
            title: homeworkList[i].title,
            start: homeworkList[i].endDate,
            end: homeworkList[i].endDate,
            isHomework:true,
            eventno:homeworkList[i].homeworkno,
          })
        }
        const scheduleList = res.data.scheduleList
        for(i = 0; i<scheduleList.length;i++){
          this.demoEvents.push({
            title: "스터디 모임",
            start: scheduleList[i].nextDate,
            end: scheduleList[i].nextDate,
            isHomework:false,
            eventno:scheduleList[i].scheduleno,
          })
        }
        
        })
        .catch(err => {
          console.log(err)
        })
    },
    userKick: function (userno) {
      this.userInfo.userno = userno
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/admin/kick`,
        data: this.userInfo
      })
        .then(res => {
          if (this.userNumber == this.userInfo.userno) {
            console.log(res, this.userInfo)
            alert("스터디에서 탈퇴하였습니다.")
            this.$router.push({name: 'Main'})
          } else {
            console.log(res, this.userInfo)
            alert("스터디에서 해당 회원을 강퇴시켰습니다.")
            window.location.reload()
          }
        })
        .catch(err => {
          console.log(err, this.userInfo)
        })
    },
    getLeader: function () {
      for (let index = 0; index < this.studyMemberList.studyMemberList.length; index++) {
        if (this.studyMemberList.studyMemberList[index].studyMember.position == 0) {
          this.leader = this.studyMemberList.studyMemberList[index].studyMember.userno
          return this.leader
        }
      }
    }
  },
  created: function () {
    this.getStudyMemberList()
    this.getArticleList()
    this.getHomeworkList()
    this.getStudyInformation()
    this.getCalendar()
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      this.userNumber = decoded.userno
    } else {
      this.$router.push({name: 'Login'})
    }
  }
}
</script>

<style scoped>
  section {
    background-image: url("./assets/board.jpg");
    background-size: contain;
    height: 100vh;
    background-repeat: repeat-x;
  }
  .icon {
    display: flex;
    flex-direction: column;
    margin-top: 5rem;
    color: white;
  }
  .icon2 {
    align-items:flex-end
  }
  .kick {
    position: fixed;
    bottom: 8px;
    right: 16px;
  }
  .clipboard {
    margin-left: 1px;
  }
  button { 
    font-size: 11px; 
    height: 38px; 
    background-color: rgb(130, 163, 209); 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
  .article {
    display: flex;
    flex-direction: column;
  }
  .homework {
    display: flex;
    flex-direction: column;
  }
  img {
    width: 40px;
    height: 40px;
    border-radius: 70%;
  }
  a {
    color: black;
  }
  #information {
    margin-top: 1rem;
    display: flex;
    flex-direction: column;
  }
  .studyInfo {
    margin: 0;
    font-size: 25px;
  }

</style>

/*
    작성자 : 한슬기
    생성일 : 2022.02.04
    마지막 업데이트 : 2022.02.06
    
    스터디 방
 */
<template>
  <div class="container">
    <div class="icon">
      <font-awesome-icon type="button" class="fa-2x mb-2" icon="bell"/>
      <font-awesome-icon type="button" class="fa-2x mb-2" v-b-toggle.sidebar-right icon="edit"/>
        <b-sidebar id="sidebar-right" title="스터디 게시판" right shadow>
          <div class="px-3 py-2">
            <p class="article">
              <article-modal></article-modal>
              <article-list :studyBoardList="studyBoardList" @getArticleList="getArticleList()"></article-list>
            </p>
          </div>
        </b-sidebar>
      <font-awesome-icon type="button" class="clipboard fa-2x mb-2" v-b-toggle.sidebar-right-homework icon="clipboard"/>
      <b-sidebar id="sidebar-right-homework" title="과제 게시판" right shadow>
        <div class="px-3 py-2">
          <p class="homework">
            <homework-modal></homework-modal>
            <homework-list :homeworkList="homeworkList" @getHomeworkList="getHomeworkList()"></homework-list>
          </p>
        </div>
      </b-sidebar>
      <font-awesome-icon class="fa-2x mb-2" v-b-modal.modal-xl type="button" id="show-btn" @click="calendarShowModal" icon="calendar-week"/>
        <b-modal ref="calendar-modal" id="modal-xl" size="xl" hide-header hide-footer>
          <calendar :studyLeaderno="getLeader()" :studyno="this.$route.params.study_no" :demoEvents="demoEvents"></calendar>
        </b-modal>
      <font-awesome-icon type="button" class="fa-2x mb-2" icon="cog"/>
    </div>
    <p class="icon2">
      <font-awesome-icon type="button" class="fa-2x mr-2" v-b-toggle.sidebar-left-study icon="info-circle"/>
      <b-sidebar id="sidebar-left-study" title="스터디 상세 정보" left shadow>
        <div class="mt-3 float-left" v-for="study in studyInformation" :key="study.id">
          <p class="studyInfo">{{ study.studyname }}</p>
          <p class="studyInfo">{{ study.maker }}</p>
          <p class="studyInfo">{{ study.category }}</p>
          <b-badge class="mx-1" v-for="interest in interestList" :key="interest.id"> {{ interest }}</b-badge>
          <p class="studyInfo">{{ study.description }}</p>
          <update-study-information :studyInformation="study" :interestList="interestList"></update-study-information>
        </div>
        </b-sidebar>
      <font-awesome-icon type="button" class="fa-2x mr-2" v-b-toggle.sidebar-left-member icon="user-friends"/>
      <b-sidebar id="sidebar-left-member" title="스터디 회원 목록" left shadow>
        <div class="px-3 py-2">
          <div class="mt-3 float-left" v-for="members in studyMemberList" :key="members.id">
            <p class="member" v-for="member in members" :key="member.id">
              <img src="https://cdn.imweb.me/thumbnail/20200606/09c71b2f94ea5.jpg" alt="default_image">
              <a id="show-btn" href="#" @click="showModal(member.studyMember)">{{ member.nickname }}</a>
            </p>
          </div>
        </div>
      </b-sidebar>
      <b-modal ref="my-modal" :member="member" hide-header hide-footer>
        <div class="d-block text-center">
          <div v-if="member">
            <user-info-modal :member="member" :studylist="studylist" :leader="getLeader()"></user-info-modal>
          </div>
        </div>
      </b-modal>
      <font-awesome-icon type="button" class="fa-2x mr-2" icon="comment-dots"/>
    </p>
  </div>
</template>

<script>
import axios from 'axios'
// import jwt_decode from 'jwt-decode'
import ArticleModal from '../../components/ArticleModal.vue'
import ArticleList from '../../components/ArticleList.vue'
import HomeworkModal from '@/components/HomeworkModal.vue'
import HomeworkList from '../../components/HomeworkList.vue'
import UpdateStudyInformation from '@/components/UpdateStudyInformation.vue'
import UserInfoModal from '@/components/UserInfoModal.vue'
import Calendar from '@/components/Calendar.vue'

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
  },
  data: function () {
    return {
      value: '',
      context: null,
      userno: null,
      studyBoardList: null,
      homeworkList: null,
      studyMemberList: null,
      studyInformation: null,
      interestList: null,
      studylist: null,
      member: null,
      leader: null,
      demoEvents: [],
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
          console.log(res.data)
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
          console.log(res.data)
          this.studyBoardList = res.data
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
          this.homeworkList = res.data
          console.log(res)
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
          console.log(this.studyMemberList)
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
        console.log(res.data.homeworkList)
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
        console.log(scheduleList)
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
    this.getArticleList()
    this.getHomeworkList()
    this.getStudyMemberList()
    this.getStudyInformation()
    this.getCalendar()
  }
}
</script>

<style scoped>
  .icon {
    display: flex;
    flex-direction: column;
  }
  .icon2 {
    float: right;
  }
  .clipboard {
    margin-left: 1px;
  }
  button { 
    font-size: 15px; 
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
  /* .studyInfo {
    text-align: left;
    width: 100%;
    background-color: rgb(248, 239, 228);
  } */
</style>
<template>
  <div class="container">
    <div>
      <div class= "row ">
        <div class = "d-flex col-12" name="detail-header">
          <div class="col-8" >
            <h1 class="float-left">{{studyname}}</h1>
          </div>
          <div class="col-4" >
            <b-badge class="bg-primary">{{studytopic}}</b-badge><br>
            <b-badge class="mx-1" v-for="tag in studytag" v-bind:key="tag"> {{tag}}</b-badge>
          </div>
        </div>
        <div class="d-flex col-12" name="detail-body"> 
          <div class="col-8 justify-content-start" name="study data" >
            <div  name="description">
              <p align="left">{{description}}</p>
            </div>
            <div align="left" name="link">
              <p>스터디 git : {{link}}</p>
            </div>
            <div align="left" name="call">
              <p>스터디 연락처 : {{call}}</p>
            </div>
          </div>

          <div class="col-4">
            <div class = "col-12" name="studyMembers">
              <div v-if="flag===2">
                <div class="d-flex justify-content-center" v-for="(member, index) in displaymembers" :key="index.id">
                  <p v-if="member.image"><img :src="member.image"></p>
                  <p v-else><img src="../../views/accounts/assets/default.png"></p>
                  <p class="mt-2">
                    <a id="show-btn" href="#" class="mt-2" @click="showModal(member)">
                      {{ member.nickname }}
                    </a>
                  </p>
                </div>
                <b-modal ref="my-modal" :member="member" hide-header hide-footer>
                  <div class="d-block text-center">
                    <div v-if="member">
                      <user-info-modal :member="member" :studylist="studylist"></user-info-modal>
                    </div>
                  </div>
                </b-modal>
              </div>
              <div v-if="flag===0">
                <b-form-checkbox-group
                id="checkbox-group"
                v-model="selected"
                name="members"
                >
                  <b-form-checkbox class="d-flex justify-content-center" v-for="member in displaymembers" v-bind:key="member.id" :value="member.userno">
                    <p v-if="member.image"><img :src="member.image"><a id="show-btn" href="#" @click="showModal(member)">{{member.nickname}}</a></p>
                    <p v-else><img src="../../views/accounts/assets/default.png"><a id="show-btn" href="#" @click="showModal(member)">{{member.nickname}}</a></p>
                  </b-form-checkbox>
                </b-form-checkbox-group>
                <b-modal ref="my-modal" :member="member" hide-header hide-footer>
                  <div class="d-block text-center">
                    <div v-if="member">
                      <user-info-modal :member="member" :studylist="studylist"></user-info-modal>
                    </div>
                  </div>
                </b-modal>
              </div>
              <div v-if="flag===1">
                <div class="d-flex justify-content-center" v-for="(member, index) in displaymembers" v-bind:key="index.id">
                  <p v-if="member.image"><img :src="member.image"></p>
                  <p v-else><img src="../../views/accounts/assets/default.png"></p>
                  <p class="mt-2"><a id="show-btn" href="#" class="mt-2" @click="showModal(member)">
                    {{member.nickname}}</a>
                  </p>
                </div>
                <b-modal ref="my-modal" :member="member" hide-header hide-footer>
                  <div class="d-block text-center">
                    <div v-if="member">
                      <user-info-modal :member="member" :studylist="studylist"></user-info-modal>
                    </div>
                  </div>
                </b-modal>
              </div>
            </div>
            <br>
            <div  name="buttons">
              <div v-if="flag===2">
                <b-button @click="studyApply" class="col-8 bg-primary">
                  스터디 신청하기
                </b-button>
              </div>
              <div v-if="flag===1" disabled=”disabled”>
                <b-button class="col-8 bg-secondary">
                  이미 신청하셨습니다.
                </b-button>
              </div>
              <div v-if="flag===0">
                <b-button @click="studyAccept" class="col-8 bg-primary mb-3">
                  가입 수락
                </b-button>
                
                <b-button @click="studyRefuse" class="col-8 bg-danger mb-3">
                  가입 거절
                </b-button>
                <router-link :to="`/study/board/update/${boardno}`">
                  <b-button  class="col-8 mb-3">글 수정</b-button>
                </router-link>
              </div>
              <b-button class="col-8" @click="deleteBoard(boardno)">글 삭제</b-button>
            </div>
          </div>
          
          
        </div>
      </div>
    </div>
  </div>
  
</template>

<script>

import jwt_decode from 'jwt-decode'
import axios from 'axios'
import UserInfoModal from '@/components/UserInfoModal.vue'

export default {
  name: 'StudyBoardDetail',
  components: {
    UserInfoModal
  },
  data() {
    return {
      boardno: -1,
      studyname: '',
      studytopic:'',
      studytag:[],
      description:'',
      link:'',
      call:'',
      members:[],
      isRecruit:-1,
      maxmember:0,
      styduno:-1,
      displaymembers:[],
      flag:2,
      selected:[],
      userno:-1,
      member: null,
      studylist: null,
      membercnt: 0,
    }
  },
  created() {
    const token = localStorage.getItem('jwt')
    const decoded = jwt_decode(token)
    const userno = decoded.userno
    this.userno = userno
    this.boardno=this.$route.params.boardno
    axios({
      method: 'get',
        url: `http://localhost:8080/api/v1/board/${this.boardno}`,
          
      })
      .then(res => {
        
        console.log(res.data)
        
        const board = res.data.board
        this.boardno=board.boardno
        this.studyname=board.boardname
        this.members = res.data.list
        this.description = board.boarddescription
        this.link = board.link
        this.call = board.contactlink
        var tag = board.interests
        this.studytag = tag.split("#")
        this.studytag.shift(0)
        this.studytopic = board.category
        this.studyno = board.studyno
        this.maxmember = board.maxmember
        console.log(this.maxmember, 100)
        //유저가 스터디 소속인지, 스터디장인지, 외부인인지 여부 판별
        //0이면 스터디장, 1이면 신청자 또는 회원 2이면 외부인
        
        for(var i = 0; i<this.members.length;i++){
          if(this.members[i].userno==userno){
            if (this.members[i].position==0){
              this.flag = 0
            }else{
              this.flag = 1
            }
          }
        }
        for(i =0; i<this.members.length;i++){
          if(this.members[i].position==0 || this.members[i].position==1){
            this.membercnt+=1
          }
        }
        console.log(this.membercnt)
        if(this.flag==0){
          for(i = 0; i<this.members.length;i++){
            if(this.members[i].position==2){
              this.displaymembers.push(this.members[i])
            }
          }
        }else{
          for(i = 0; i<this.members.length;i++){
            if(this.members[i].position!=2){
              this.displaymembers.push(this.members[i])
            }
          }
        }
        console.log(this.displaymembers)
      })
      .catch(err => {
        console.log(this.title, err)
      })
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
    getUserInformation: function (user) {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/admin/${user.userno}/`,
        headers: this.setToken()
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
    studyApply() {
      const data = {
        studyno: this.studyno,
        userno: this.userno,
      }
      // if () {

      // } else {

      // }
      axios({
        method: 'post',
        url: 'http://localhost:8080/api/v1/study/apply',
        headers: this.setToken(),
        data: data
      })
      .then(res => {
        console.log(res)
        alert("스터디를 신청하셨습니다.")
        window.location.reload()
      })
      .catch(err => {
        console.log(err)
      })
    },
    studyAccept() {
      console.log(this.selected,"here")
      if(this.selected.length+this.membercnt > this.maxmember){
        alert("멤버제한을 초과했습니다.")
        return
      }
      for(var i=0; i<this.selected.length;i++){
        var data = {
          flag:1,
          studyno: this.studyno,
          userno: this.selected[i],
        }
        axios({
          method: 'put',
          url: 'http://localhost:8080/api/v1/study/accept',
          headers: this.setToken(),
          data: data
        })
        .then(res => {
          console.log(res)
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
      }
    },
    studyRefuse() {
      console.log(this.selected)
      for(var i=0; i<this.selected.length;i++){
        var data = {
          flag:0,
          studyno: this.studyno,
          userno: this.selected[i],
        }
        axios({
          method: 'put',
          url: 'http://localhost:8080/api/v1/study/accept',
          headers: this.setToken(),
          data: data
        })
        .then(res => {
          console.log(res)
          alert("가입을 거절했습니다.")
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
      }
    },
    deleteBoard: function (boardno) {
      axios({
        method: 'delete',
        url: `http://localhost:8080/api/v1/board/${boardno}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res)
          this.$router.push({name: 'StudyBoard'})
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created: function () {
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
    } else {
      this.$router.push({name: 'Login'})
    }
  },
}

</script>
<style scoped>
  button { 
    font-size: 15px; 
    height: 38px; 
    background-color: rgb(130, 163, 209); 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
  img {
    width: 40px;
    height: 40px;
    border-radius: 70%;
    margin-right: 1rem;
  }
  a {
    color: black;
  }
</style>

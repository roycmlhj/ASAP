<template>
  <div class="container">
    <h1>모집 글 수정</h1>
    <b-form>
      <b-form-group
      >
        <label
          class="float-left"
         for="study">스터디</label>
        <study-dropdown
        v-on:studyEvent="updateStudy"
        ></study-dropdown>
      </b-form-group>
      <b-form-group
        id="input-title-group"
        lebel-for="input-title"
      >
        <label
          class="float-left"
         for="title">스터디 이름</label>
        <b-form-input
          id="input-title"
          v-model="title"
          type = "text"
          required
        >{{title}}
        </b-form-input>
      </b-form-group>
      
      <b-form-group
        id="input-description-group"
        lebel-for="input-description"
        
      >
        <label
          class="float-left"
         for="input-description">스터디 설명</label>
        <b-form-textarea
          id="input-description"
          v-model="description"
          type = "text"
          required
          rows="12"
        >{{description}}
        </b-form-textarea>
      </b-form-group>
      <b-form-group
        id="input-git-group"
        
        label-for="input-git"
      >
        <label
          class="float-left"
         for="input-description">스터디 Git</label>
        <b-form-input
          id="input-git"
          v-model="git"
          type="text"
          placeholder="git 주소를 입력해 주세요"
        >
        {{git}}
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-call-group"
        
        label-for="input-call"
      >
        <label
          class="float-left"
         for="input-description">스터디 연락처</label>
        <b-form-input
          id="input-call"
          v-model="call"
          type="text"
          placeholder="연락처 또는 오픈카카오톡 주소를 알려주세요"
        >
        {{call}}
        </b-form-input>
      </b-form-group>
      <div class="mt-3">
        <b-button @click='updateStudyRoom' class="float-right">수정하기</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import StudyDropdown from '@/components/StudyDropdown.vue'
import axios from 'axios'
import jwt_decode from 'jwt-decode'

export default {
  name: 'UpdateStudyBoard',
  components: {
    StudyDropdown
  },
  data() {
    return {
      title:null,
      description:null,
      interests:[],
      git:null,
      call:null,
      study:null,
      studyno:null,
      userno:null,
    }
  },
  created() {
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      console.log(decoded)
      this.userno = decoded.userno
      
    } else {
      this.$router.push({name: 'Login'})
    }
    this.boardno=this.$route.params.boardno
    console.log(this.boardno)
    axios({
      method:'get',
      url:`http://localhost:8080/api/v1/board/${this.boardno}`,
    }).then(res=> {
      console.log(res)
      const list = res.data.list
      for(var i = 0 ; i<list.length;i++){
        if(list[i].position==0){
          if(list[i].userno!=this.userno){
            this.$router.push({name: 'Main'})
          }
        }
      }
      this.title=res.data.board.boardname
      this.description = res.data.board.boarddescription
      this.git = res.data.board.link
      this.call = res.data.board.contactlink
      console.log(res.data.board)
      this.studyno = res.data.board.studyno
      
    }).catch(err=> {
      console.log(err)
    })
  }
  ,
  methods: {
    updateStudy(studyinfo) {
      this.studyno=studyinfo.studyno
      axios({
        method:'get',
        url:`http://localhost:8080/api/v1/study/list/simple-detail/${studyinfo.studyno}`,
        data:this.study
      }).then(res=> {
        console.log(res)
        this.title=studyinfo.studyname
        
        this.description= res.data.study.description
        console.log(this.description)
        console.log(this.studyno,this.userno)
      })
    },
    
    updateStudyRoom() {
      const StudyRoomItem = {
        boardname:this.title,
        boarddescription:this.description,
        link:this.git,
        contactlink: this.call,
        studyno:this.studyno,
        userno:this.userno,
      }
      
      axios({
        method:'post',
        url:`http://localhost:8080/api/v1/board/${this.boardno}`,
        data: StudyRoomItem,
      }).then(res => {
        console.log(res)
        this.$router.push({name:'StudyBoard'})
      }).catch(err=> {
        console.log(err,1)
        alert(err)
      })
    }
  }
}
</script>
<style scoped>
  button { font-size: 15px; height: 38px; background-color: rgb(130, 163, 209); } button:hover { background-color: rgb(79, 138, 216); }
</style>
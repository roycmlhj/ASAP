<template>
<section>
  <div class="container">
    <div class="col-3">
      <h1 class="mb-0">스터디</h1>
      <h1 class="mt-0">모집하기</h1>
      <font-awesome-icon class="fa-5x my-2" icon="edit"/>
    </div>
    <div class="form col-9">
      <div class="d-flex justify-content-center">
        <div>
          <font-awesome-icon class="fa-3x mt-2 mr-3" icon="fa-regular fa-bell" />
        </div>
        <div>
          <p class="mb-0">모집할 스터디를 선택하고, 필요한 정보를 입력해주세요.</p> 
          <p class="float-left">스터디 git 주소와 오픈 카카오톡은 선택사항입니다.</p>
        </div>
      </div>
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
          class="mt-4"
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
          class="mt-4"
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
          class="mt-4"
          label-for="input-git"
        >
          <label
            class="float-left"
          for="input-description">스터디 링크</label>
          <b-form-input
            id="input-git"
            v-model="git"
            type="text"
            placeholder="스터디 관련 링크를 입력해주세요."
          >
          </b-form-input>
        </b-form-group>
        <b-form-group
          id="input-call-group"
          class="mt-4"
          label-for="input-call"
        >
          <label
            class="float-left"
          for="input-description">스터디 연락처</label>
          <b-form-input
            id="input-call"
            v-model="call"
            type="text"
            placeholder="연락처 또는 오픈카카오톡 주소를 입력해주세요."
          >
          </b-form-input>
        </b-form-group>
        <div class="mt-3">
          <b-button @click='createStudyRoom' class="float-right">작성하기</b-button>
        </div>
      </b-form>
    </div>
  </div>
</section>
</template>

<script>
import StudyDropdown from '@/components/StudyDropdown.vue'
import axios from 'axios'
import jwt_decode from 'jwt-decode'

export default {
  name: 'CreateStudyBoard',
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
    if (sessionStorage.getItem('jwt')) {                     // 수정
      const token = sessionStorage.getItem('jwt')                        // 수정
      const decoded = jwt_decode(token)
      console.log(decoded)
      this.userno = decoded.userno
      
    } else {
      this.$router.push({name: 'Login'})
    }
  }
  ,
  methods: {
    updateStudy(studyinfo) {
      this.studyno=studyinfo.studyno
      axios({
        method:'get',
        url:`https://i6a107.p.ssafy.io:8443/api/v1/study/list/simple-detail/${studyinfo.studyno}`,
        data:this.study
      }).then(res=> {
        console.log(res)
        this.title=studyinfo.studyname
        
        this.description= res.data.study.description
        console.log(this.description)
        console.log(this.studyno,this.userno)
      })
    },
    createStudyRoom: function() {
      console.log(this.title,this.description,this.git,this.call,this.studyno,this.userno)
      const StudyRoomItem = {
        boardname:this.title,
        boarddescription:this.description,
        link:this.git,
        contactlink: this.call,
        studyno:this.studyno,
        userno:this.userno,
      }
      console.log(StudyRoomItem)
      var flagTitle=false
      var flagContent=false
      for(var i = 0; i<StudyRoomItem.boardname.length;i++){
        if(StudyRoomItem.boardname[i]!=' '){
          flagTitle=true
          break
        }
      }
      for(var i = 0; i<StudyRoomItem.boarddescription.length;i++){
        if(StudyRoomItem.boarddescription[i]!=' '){
          flagContent=true
          break
        }
      }
      console.log(flagTitle, flagContent)
      if (StudyRoomItem.boardname == null || StudyRoomItem.boarddescription == null || StudyRoomItem.boardname=='' || StudyRoomItem.boarddescription==''|| !flagTitle ||!flagContent) {
        alert("모든 입력 칸을 입력해주세요.")
      }else{
        axios({
          method:'post',
          url:`https://i6a107.p.ssafy.io:8443/api/v1/board/create/`,
          data: StudyRoomItem,
        }).then(res => {
          console.log(res)
          this.$router.push({name:'StudyBoard'})
        }).catch(err=> {
          console.log(err,1)
          alert(err)
        })
      }
    },
    created: function () {
      if (sessionStorage.getItem('jwt')) {                    // 수정
        const token = sessionStorage.getItem('jwt')                  // 수정
        const decoded = jwt_decode(token)
      } else {
        this.$router.push({name: 'Login'})
      }
    },
  }
}
</script>
<style scoped>
  section {
    margin-top: 5rem;                  /* 수정 */
  }
  .container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    margin-top: 10rem;
  }
  p {
    font-family: 'Do Hyeon', sans-serif;
    font-size: 20px;
  }
  h1 {
    margin-top: 2rem;
    font-family: 'Black Han Sans', sans-serif;
  }
  button { 
    margin-top: 2rem;
    background-color: rgb(130, 163, 209); 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
  .form {
    border: 1px solid rgb(188, 197, 209);
    box-shadow: 5px 5px 5px 5px gray;
    padding: 3% 10%;
    height: 700px;
  }
</style>
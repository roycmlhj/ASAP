<template>
<section>
  <div class="container">
    <div class="col-3">
      <h1 class="mb-0">모집글</h1>
      <h1 class="mt-0">수정하기</h1>
      <font-awesome-icon icon="fa-solid fa-marker" class="fa-5x my-2"/>
    </div>
    <div class="form col-9">
      <div class="d-flex justify-content-center">
        <div>
          <font-awesome-icon icon="fa-solid fa-clipboard-list" class="fa-3x mr-3"/>
        </div>
        <div>
          <p>스터디 모집글을 수정해주세요.</p> 
        </div>
      </div>
      <b-form>
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
            disabled
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
  </div>
</section>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'

export default {
  name: 'UpdateStudyBoard',
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
    if (sessionStorage.getItem('jwt')) {                  // 수정
      const token = sessionStorage.getItem('jwt')                // 수정
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
      console.log(this.title, 456)
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
  },
}
</script>

<style scoped>
  section {
    margin-top: 5rem;            /* 수정 */
  }
  .container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    margin-top: 10rem;
  }
  button { 
    margin-top: 2rem;
    font-size: 15px;
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
  p {
    font-family: 'Do Hyeon', sans-serif;
    font-size: 20px;
    margin-top: 8px;
  }
  h1 {
    margin-top: 2rem;
    font-family: 'Black Han Sans', sans-serif;
  }
</style>
<template>
  <div class="container">
    <h1>모집 글 작성</h1>
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
        <b-form-group label="주제" label-for="interests" label-align-sm="left">
          <b-form-tags 
            input-id="interests" 
            v-model="interests"
            tag-variant="primary"
            tag-pills
            placeholder="주제를 입력해주세요."
          ></b-form-tags>
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
        </b-form-input>
      </b-form-group>
      <div class="mt-3">
        <b-button class="float-right" type="submit">작성하기</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import StudyDropdown from '@/components/StudyDropdown.vue'
import axios from 'axios'


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
    }
  },
  methods: {
    updateStudy(studyinfo) {
      axios({
        method:'get',
        url:`http://localhost:8080/api/v1/study/list/simple-detail/${studyinfo.studyno}`,
        data:this.study
      }).then(res=> {
        console.log(res)
        this.title=studyinfo.studyname
        if (!res.data.study.interests){
          this.interests=[]
        }else{
          const input_interests = res.data.study.interests
          
          this.interests=input_interests.split('#')
        }
        this.description= res.data.study.description
        console.log(this.description)
      })
    },
    createStudyRoom: function() {
      const StudyRoomItem = {
        title:this.title,
        description:this.description,
        interests:this.interests,
        git:this.git,
        call: this.call,
      }
      //console.log(StudyRoomItem.member)
      console.log(StudyRoomItem)
    }
  }
}
</script>
<style scoped>
  .container {
    padding: 0% 10%;
  }
  button { 
    font-size: 15px; 
    height: 38px; 
    background-color: rgb(130, 163, 209); 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
</style>

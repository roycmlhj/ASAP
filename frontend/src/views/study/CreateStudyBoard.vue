<template>
  <div class="container">
    <h1>모집 글 작성</h1>
    <b-form>
      <b-form-group
        id="input-study-group"
        label="스터디 선택"
        label-for="input-study"
      >
        <study-dropdown
        v-on:studyEvent="updateStudy"
        ></study-dropdown>
      </b-form-group>
      <b-form-group
        id="input-title-group"
        label="스터디 이름"
        lebel-for="input-title"
      >
        <b-form-input
          id="input-title"
          v-model="title"
          type = "text"
          required
        >{{title}}
        </b-form-input>
      </b-form-group>
      <b-form-tags 
        input-id="interests" 
        v-model="interests"
        tag-variant="primary"
        tag-pills
        placeholder="관심분야를 입력해주세요."
        ></b-form-tags>
      <b-form-group
        id="input-description-group"
        label="스터디 설명"
        lebel-for="input-description"
      >
        <b-form-input
          id="input-description"
          v-model="description"
          type = "text"
          required
        >{{description}}
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-git-group"
        label = "스터디 git"
        label-for="input-git"
      >
        <b-form-input
          id="input-git"
          v-model="git"
          type="text"
        >
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-call-group"
        label="스터디 연락처 또는 오픈카카오 주소"
        label-for="input-call"
      >
        <b-form-input
          id="input-call"
          v-model="call"
          type="text"
        >
        </b-form-input>
      </b-form-group>
      <div class="mt-3">
        <b-button type="submit" variant="primary">작성하기</b-button>
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

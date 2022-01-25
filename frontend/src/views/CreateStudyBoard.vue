<template>
  <div>
    <h1>모집 글 작성</h1>
    <b-form >
      
      <b-form-group
        id="input-study-group"
        label="스터디 선택"
        label-for="input-study"
      >
        <study-dropdown
        v-on:studyEvent="updateStudy"
        >

        </study-dropdown>
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
          placeholder=""
          required
        >{{title}}</b-form-input>
      </b-form-group>
      
      <b-form-group
        id="input-topic-group"
        label="스터디 주제"
        lebel-for="input-topic"
      >
        <b-form-input
          id="input-topic"
          v-model="topic"
          type = "text"
          placeholder=""
          required
        >{{topic}}</b-form-input>
      </b-form-group>
      
      
      <b-form-group
        id="input-description-group"
        label="스터디 설명"
        lebel-for="input-description"
      >
        <b-form-input
          id="input-description"
          v-model="description"
          type = "text"
          placeholder=""
          required
        >{{description}}</b-form-input>
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
          placeholder=""
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
          placeholder=""
        >

        </b-form-input>
      </b-form-group>
      
      <div class="mt-3">
        <b-button type = "submit" variant="btn btn-primary">스터디모집글 작성하기</b-button>
        <b-button variant="btn btn-secondary" class="ms-1"
        href='http://localhost:8080/board/create/'>취소</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import StudyDropdown from '@/views/StudyDropdown.vue'
// @ is an alias to /src
  export default {
    components:{StudyDropdown},
    data() {
      
      return {
        title:null,
        description:null,
        topic:null,
        git:null,
        call:null,
      }
    },
    methods: {
      updateStudy(Study) {
        this.title = Study.title
        this.description = Study.description
        this.topic = Study.topic
        //console.log(this.title)
        //console.log(this.description)
        //console.log(this.topic)
      },
      createStudyRoom: function() {
        const StudyRoomItem = {
          title:this.title,
          description:this.description,
          topic:this.topic,
          git:this.git,
          call: this.call,
        }
        //console.log(StudyRoomItem.member)
        console.log(StudyRoomItem)
      }
    }
  }
</script>
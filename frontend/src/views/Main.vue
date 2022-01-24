<template>
  <div>
    <b-button @click="modalShow = !modalShow">스터디방 만들기</b-button>
    <b-modal v-model="modalShow">
      <h3>스터디방 생성</h3>
      <b-form @submit="createStudyRoom">
        <p>스터디 이름</p>
        <b-form-group
          id="input-title-group"
          label="스터디 이름"
          lebel-for="input-title"
        >
          <b-form-input
            id="input-title"
            v-model="title"
            type = "text"
            placeholder="스터디 이름"
            required
          ></b-form-input>
        </b-form-group>
        <p>스터디 주제</p>
        <b-form-group
          id="input-topic-group"
          label="스터디 주제"
          lebel-for="input-topic"
        >
          <b-form-input
            id="input-topic"
            v-model="topic"
            type = "text"
            placeholder="스터디 주제"
            required
          ></b-form-input>
        </b-form-group>
        
        <p>스터디 설명</p>
        <b-form-group
          id="input-description-group"
          label="스터디 설명"
          lebel-for="input-description"
        >
          <b-form-input
            id="input-description"
            v-model="description"
            type = "text"
            placeholder="스터디 설명"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="input-member-group"
          label="스터디 인원"
          label-for="input-member"
        >
          <study-member-count-bar
            v-on:memberEvent="updateMember"
          >
          </study-member-count-bar>
        </b-form-group>
        
        <div class="mt-3">
          <b-button type = "submit" variant="btn btn-primary">스터디방 만들기</b-button>
          <b-button variant="btn btn-secondary" class="ms-1"
          @click="hideModal">닫기</b-button>
        </div>
      </b-form>
      </b-modal>
  </div>
</template>

<script>
//import axios from 'axios'
import StudyMemberCountBar from '@/views/StudyMemberCountBar'
  export default {
    components: { StudyMemberCountBar },
    data() {
      return {
        modalShow: false,
        title:null,
        description:null,
        topic:null,
        member:null,
      }
    },
    methods: {
      updateMember(memberNum) {
        this.member=memberNum
        //console.log(this.member)
      },
      createStudyRoom: function() {
        const StudyRoomItem = {
          title:this.title,
          description:this.description,
          topic:this.topic,
          member:this.member,
        }
        //console.log(StudyRoomItem.member)
        console.log(StudyRoomItem)
      }
    }
  }
</script>
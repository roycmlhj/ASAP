<template>
  <div>
    <b-button @click="modalShow=!modalShow" variant="link">스터디 방 생성하기</b-button>
    <b-modal v-model="modalShow" title="Create Study" hide-footer>
      <b-form @submit="createStudyRoom">
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
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="input-topic-group"
          label="주제"
          lebel-for="input-topic"
        >
          <b-form-input
            id="input-topic"
            v-model="topic"
            type = "text"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="input-description-group"
          label="소개"
          lebel-for="input-description"
        >
          <b-form-textarea
            id="input-description"
            v-model="description"
            type = "text"
            rows="6"
            max-rows="6"
            required
          ></b-form-textarea>
        </b-form-group>
        <b-form-group
          id="input-member-group"
          label="총 인원 수"
          label-for="input-member"
        >
          <study-member-count-bar
            v-on:memberEvent="updateMember"
          >
          </study-member-count-bar>
        </b-form-group>
        <b-button class="float-right" type="submit" variant="dark">완료</b-button>
      </b-form>
    </b-modal>
    <br>
    <router-link :to="{ name: 'CreateStudyBoard' }">스터디 모집하기</router-link>
  </div>
</template>

<script>
import jwt_decode from 'jwt-decode'
// import axios from 'axios'
import StudyMemberCountBar from '@/components/StudyMemberCountBar.vue'

export default {
  name: 'Main',
  components: { 
    StudyMemberCountBar
  },
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
  },
  created: function () {
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      console.log(decoded)
    } else {
      this.$router.push({name: 'Login'})
    }
  }
}
</script>

<style scoped>
  form {
    padding: 0px 20px;
  }
</style>

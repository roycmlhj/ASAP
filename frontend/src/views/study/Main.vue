<template>
  <div>
    <b-button @click="modalShow=!modalShow" variant="link">스터디방 만들기</b-button>
    <b-modal v-model="modalShow" title="스터디방 생성" hide-footer>
      <b-form>
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
          label="관심분야"
          label-for="interests"
          
        >
          <b-form-select name="interests" id="interests" v-model="mainCategory">
            <option value="" selected disabled hidden>선택해주세요</option>
            <option v-for="interest in interestsList" :key="interest.id" :value="interest.iname">{{ interest.iname }}</option>
          </b-form-select>
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
        <div class="mt-3">
          <b-button @click="createStudyRoom" variant="btn btn-primary">완료</b-button>
        </div>
      </b-form>
    </b-modal>
    <br>
    <router-link :to="{ name: 'CreateStudyBoard' }">스터디 모집하기</router-link>
  </div>
</template>

<script>
import jwt_decode from 'jwt-decode'
import axios from 'axios'
import StudyMemberCountBar from '@/components/StudyMemberCountBar.vue'
import interest from "./assets/interests.json"
export default {
  name: 'Main',
  components: { 
    StudyMemberCountBar
  },
  data() {
    return {
      interestsList : interest,
      modalShow: false,
      title:'',
      description:'',
      memberno:'',
      mainCategory:'',
      interests:[],
      maker:''
    }
  },
  methods: {
    updateMember(memberNum) {
      this.memberno=memberNum
      //console.log(this.member)
    },
    createStudyRoom: function() {
      
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      console.log(decoded)
      
      const StudyRoomItem = {
        studyname:this.title,
        description:this.description,
        category:this.mainCategory,
        memberno:this.memberno,
        maker:decoded.email,
        userno:decoded.userno,
        interests:this.interests,
      }
      
      axios({
        method:'post',
        url:`http://localhost:8080/api/v1/study/create`,
        data:StudyRoomItem
      }).then(res => {
        console.log(res)
        alert("스터디방 생성")
        this.modalShow=false
      }).catch(err=> {
        console.log(err)
        alert(err)
        
      })
      console.log(console.log(this.interests))
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
<style scoped> form { padding: 0px 20px; } </style>
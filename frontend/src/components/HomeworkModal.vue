/*
    작성자 : 한슬기
    생성일 : 2022.02.04
    마지막 업데이트 : 2022.02.04
    
    스터디 방 > 과제 생성 모달
 */
<template>
<div class="container">
  <font-awesome-icon class="fa-1x mt-1 mr-1 float-left" icon="edit"/><a href="#" id="show-btn" class="float-left" style="color: rgb(130, 163, 209);" @click="showModal"><strong>과제 추가하기</strong></a>
  <b-modal ref="my-modal"
    ok-only 
    title="Create Homework"   
    hide-footer 
  >
    <b-form-group
      label="Title"
      label-for="title"
    >
      <b-form-input 
        id="title" 
        type="text"
        v-model="homework.title"
      >
      </b-form-input>
    </b-form-group>
    <b-form-group
      label="Content"
      label-for="content"
    >
      <b-form-textarea 
        id="content" 
        type="text"
        v-model="homework.content"
        rows="6"
        max-rows="6"
      >
      </b-form-textarea>
    </b-form-group>
    <b-form-group
      label="Date"
      label-for="content"
    >
      <b-form-input 
        id="content" 
        type="date"
        v-model="homework.endDate"
      >
      </b-form-input>
      <b-button class="mt-5" style="float: right; background-color: rgb(130, 163, 209);" @click="createHomework()">확인</b-button>
    </b-form-group>
  </b-modal>
</div>
</template>

<script>
import jwt_decode from 'jwt-decode'
import axios from 'axios'


export default {
  name: 'HomeworkModal',
  data: function () {
    return {
      homework: {
        title: null,
        content: null,
        studyno: this.$route.params.study_no,
        userno: null,
        endDate: null,
      },
    }
  },
  methods: {
    setToken: function () {
        const token = localStorage.getItem('jwt')
        const config = {
          Authorization: `JWT ${token}`
        }
        return config
      },
    showModal() {
      this.homework.title = null,
      this.homework.content = null,
      this.endDate = null
      this.$refs['my-modal'].show()
    },
    createHomework: function () {
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/homework/create`,
        data: this.homework,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data)
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
    created: function () {
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      this.homework.userno = decoded.userno
      // console.log(decoded.userno)
    }
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
</style>
<template>
<div class="container">
  <font-awesome-icon v-if="homework.userno==user" class="fa-2x mt-1 mr-1 float-left" icon="edit"/>
    <a v-if="homework.userno==user" type="button" id="show-btn" class="float-left mt-2" style="font-size: 20px; color: #394E79;" @click="showModal"><strong>과제 추가하기</strong></a>  <!--수정-->
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
        maxlength=100
        rows="10"
      ></b-form-textarea>
      
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
  props: { 
    user:{ 
      type: Number, 
    } 
  },
  methods: {
    setToken: function () {
        const token = sessionStorage.getItem('jwt')               // 수정
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
      const token = sessionStorage.getItem('jwt')                  // 수정
      const decoded = jwt_decode(token)
      this.homework.userno = decoded.userno
      console.log(decoded, 123)
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
  content {
    height:100px;
  }
</style>

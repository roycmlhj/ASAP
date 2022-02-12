/*
    작성자 : 한슬기
    생성일 : 2022.02.05
    마지막 업데이트 : 2022.02.05
    
    스터디 방 > 과제 상세보기, 수정, 삭제
 */
<template>
  <div>
    <div id="container">
      <div class="content" :style="{ height: assignment.homework.content.length + 350 + 'px'}">
        <p><strong>작성자 : {{ assignment.nickname }}</strong></p>
        <p>{{ assignment.homework.content }}</p>
        <p class="p">
          <b-form-file ref="file" method="post" enctype="multipart/form-data" class="mt-3"> 
          </b-form-file>
          <b-button class="mt-2" @click="uploadFile()" style="background-color: skyblue; border: none;">Add</b-button>
        </p>
        <p class="mb-1"><strong>과제 제출 리스트</strong></p>
        <div v-for="userHomework in userHomeworkList" :key="userHomework.id">
          <p class="p d-flex justify-content-between">
            {{userHomework.nickname}} - <a :href="userHomework.userHomewrok.filepath" download>{{ userHomework.userHomewrok.ogfilename }}</a> 
            <a v-if="userHomework.userHomewrok.filepath && userHomework.userHomewrok.userno==userno" @click="deleteFile(userHomework.userHomewrok.userhomeworkno)" style="color:red;">X</a>
          </p>
          
        </div>
      </div>
      <div class="mt-2 mb-3">
        <b-button v-if="userno==homeworkUserno" @click="deleteHomework(assignment.homework.homeworkno)">삭제</b-button>
        <b-button v-if="userno==homeworkUserno" id="show-btn" @click="showModal(assignment.homework)">수정</b-button>
      </div>
      <b-modal ref="my-modal"
        ok-only 
        title="Update Homework"   
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
        </b-form-group>
        <b-button style="float-right; font-size: 15px; height: 40px;" @click="updateHomework()">확인</b-button>
      </b-modal>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'

export default {
  name: 'HomeworkItem',
  data: function () {
    return {
    homework: {
        homeworkno: this.assignment.homework.homeworkno,
        title: null,
        content: null,
        endDate: null,
      },
      userno: 0,
      homeworkUserno: this.assignment.homework.userno,
      userHomeworkList: null,
    }
  },
  props: {
    assignment: {
      type: Object
    },
  },
  methods: {
    setToken: function () {
      const token = localStorage.getItem('jwt')
      const config = {
        Authorization: `JWT ${token}`
      }
      return config
    },
    showModal(homework) {
      this.homework.title = homework.title,
      this.homework.content = homework.content,
      this.homework.endDate = homework.endDate
      this.$refs['my-modal'].show()
    },
    uploadFile: function () {
      console.log('파일')
      var file = this.$refs['file'].files[0]
      const formData = new FormData()
      formData.append('files', file)
      formData.append('homeworkno', this.homework.homeworkno)
      formData.append('userno', this.userno)
      console.log(formData)
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/homework/upload`,
        headers: this.setToken(),
        data: formData
      })
        .then(res => {
          console.log(res)
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
    },
    deleteFile: function(fileno){
      console.log(fileno)
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/homework/filedelete/${fileno}`,
       
      })
      .then(res => {
        console.log(res.data)
        alert("과제 삭제 완료")
        window.location.reload()
      })
      .catch(err => {
        console.log(err)
      })
    }
    ,
    deleteHomework: function (homeworkno) {
      axios({
        method: 'delete',
        url: `http://localhost:8080/api/v1/homework/delete/${homeworkno}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data)
          alert("게시글이 삭제되었습니다.")
          window.location.reload()
          this.$emit('getHomeworkList')
        })
        .catch(err => {
          console.log(err)
        })
    },
    updateHomework: function () {
      axios({
        method: 'put',
        url: `http://localhost:8080/api/v1/homework/modify`,
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
    },
    getUserHomeworkList: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/homework/uploadList/${this.homework.homeworkno}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data,1012310)
          this.userHomeworkList = res.data.userHomeworkInfoList
          console.log(this.userHomeworkList, 111333)
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
    created() {
    const token = localStorage.getItem('jwt')
    const decoded = jwt_decode(token)
    const userno = decoded.userno
    this.userno = userno
    this.getUserHomeworkList()
    console.log(this.assignment)
  }
}
</script>

<style scoped>
  #container {
    display: flex;
    flex-direction: column;
  }
  .content {
    border: 1px solid #0000004d;
    position: relative;
    background-color: white;
    box-shadow: 5px 5px 5px 0px gray;
    word-break: break-all;
    white-space:pre;
    padding: 20px;
    display: flex;
    flex-direction: column;
  }
  .content:before {
    content: '';
    position: absolute;
    top: 0;
    right: 0;
    border-top: 15px solid palevioletred;
    border-left: 15px solid rgba(0, 0, 0, 0);
    width: 0;
  }
  button { 
    float: right;
    margin-right: 2px;
    font-size: 11px; 
    height: 30px; 
    background-color: rgb(130, 163, 209); 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
  .p {
    margin: 0px;
  }
  p{
    font-size:15px;
  }
</style>
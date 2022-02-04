/*
    작성자 : 한슬기
    생성일 : 2022.02.04
    마지막 업데이트 : 2022.02.04
    
    스터디 방 > 게시글 작성 모달
 */
<template>
<div class="container">
  <font-awesome-icon class="fa-1x mt-1 mr-1 float-left" icon="edit"/><a href="#" id="show-btn" class="float-left" style="color: rgb(130, 163, 209);" @click="showModal"><strong>글 작성하기</strong></a>
  <b-modal ref="my-modal"
    ok-only 
    title="Create Ariticle"   
    hide-footer 
  >
    <b-form-group
      label="Title"
      label-for="title"
    >
      <b-form-input 
        id="title" 
        type="text"
        v-model="article.title"
      >
      </b-form-input>
    </b-form-group>
    <b-form-group
      label="내용"
      label-for="content"
    >
      <b-form-textarea 
        id="Content" 
        type="text"
        v-model="article.content"
        rows="6"
        max-rows="6"
      >
      </b-form-textarea>
      <b-button class="mt-5" style="float: right; background-color: rgb(130, 163, 209);" @click="createArticle()">확인</b-button>
    </b-form-group>
  </b-modal>
</div>
</template>

<script>
import jwt_decode from 'jwt-decode'
import axios from 'axios'


export default {
  name: 'ArticleModal',
  data: function () {
    return {
      article: {
        title: null,
        content: null,
        studyno: this.$route.params.study_no,
        userno: null,
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
      this.article.title = null,
      this.article.content = null
      this.$refs['my-modal'].show()
    },
    createArticle: function () {
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/study_board/create`,
        data: this.article,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data)
          window.location.reload()
        })
        .catch(err => {
          console.log(err, this.article)
        })
    }
  },
    created: function () {
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      this.article.userno = decoded.userno
      // console.log(decoded.userno)
    }
}
</script>

<style>
  .modal {
    padding: 20px;
  }
  a {
    color: red;
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
/*
    작성자 : 한슬기
    생성일 : 2022.02.04
    마지막 업데이트 : 2022.02.04
    
    스터디 방 > 게시글 수정, 삭제, 상세 정보
 */
<template>
<div id="container">
  <div class="content" :style="{ height: post.studyBoard.content.length + 150 + 'px'}">
    <p><strong>작성자 : {{ post.nickname }}</strong></p>
    <p>{{ post.studyBoard.content }}</p>
  </div>
  <div class="mt-2 mb-3">
    <b-button v-if="userno===articleuserno" @click="deleteArticle(post.studyBoard.boardno)">삭제</b-button>
    <b-button v-if="userno===articleuserno" id="show-btn" @click="showModal">수정</b-button>
  </div>
    <b-modal ref="my-modal"
      ok-only 
      title="Update Ariticle"   
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
        label="Content"
        label-for="content"
      >
        <b-form-textarea 
          id="content" 
          type="text"
          v-model="article.content"
          rows="6"
          max-rows="6"
        >
        </b-form-textarea>
        <b-button class="mt-3" style="font-size: 15px; height: 40px;" @click="updateArticle">확인</b-button>
      </b-form-group>
    </b-modal>
</div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'

export default {
  name: 'ArticleItem',
  data: function () {
    return {
      article: {
        title: null,
        content: null,
        boardno: this.post.studyBoard.boardno
      },
      userno: 0,
      articleuserno: this.post.studyBoard.userno,
    }
  },
  props: {
    post: {
      type: Object
    },
    flag: {
      type: Boolean
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
      this.article.title = this.post.studyBoard.title
      this.article.content = this.post.studyBoard.content
      this.$refs['my-modal'].show()
    },
    deleteArticle: function (boardno) {
      axios({
        method: 'delete',
        url: `http://localhost:8080/api/v1/study_board/delete/${boardno}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data)
          alert("게시글이 삭제되었습니다.")
          this.$emit('getArticleList')
        })
        .catch(err => {
          console.log(err)
        })
    },
    updateArticle: function () {
      axios({
        method: 'put',
        url: `http://localhost:8080/api/v1/study_board/modify`,
        data: this.article,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data, this.article)
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created() {
    const token = localStorage.getItem('jwt')
    const decoded = jwt_decode(token)
    const userno = decoded.userno
    this.userno = userno
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
    padding: 10px;
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
</style>
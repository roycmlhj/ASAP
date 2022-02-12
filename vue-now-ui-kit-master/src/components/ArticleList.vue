/*
    작성자 : 한슬기
    생성일 : 2022.02.04
    마지막 업데이트 : 2022.02.07
    
    스터디 방 > 게시글 제목
 */
<template>
  <div class="container">
    <div v-for="studyBoard in studyBoardList" :key="studyBoard.id">
      <a href="#" @click="getArticleInformation(studyBoard.studyBoard.boardno)"><strong>  {{ studyBoard.studyBoard.title }}</strong></a>
      <hr>
      <p v-if="post && post.studyBoard.boardno == studyBoard.studyBoard.boardno">
        <article-item :post="post"></article-item>
      </p>
    </div>
  </div>
</template>

<script>
import ArticleItem from '@/components/ArticleItem.vue'
import axios from 'axios'


export default {
  name: 'ArticleList',
  data: function() {
    return {
      post: null,
    }
  },
  components: {
    ArticleItem,
  },
  props: {
    studyBoardList: {
      type: Array
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
    getArticleInformation: function (boardno) {
      if (this.post != null && boardno == this.post.studyBoard.boardno) {
        this.post.studyBoard.boardno = -1
      } else {
        axios({
          method: 'get',
          url: `http://localhost:8080/api/v1/study_board/boardlist/detail/${boardno}`,
          headers: this.setToken(),
        })
          .then(res => {
            console.log(res.data,1123)
            this.post = res.data
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    getArticleList: function () {
      this.$emit('getArticleList')
    }
  },
  created: function () {
    console.log(this.studyBoardList)
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
  a {
    color: black;
  }
  .container {
    margin-top : 2rem;
    text-align: left;
  }
</style>
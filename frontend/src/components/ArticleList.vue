/*
    작성자 : 한슬기
    생성일 : 2022.02.04
    마지막 업데이트 : 2022.02.04
    
    스터디 방 > 게시글 제목
 */
<template>
  <div class="container">
    <div v-for="studyBoard in studyBoardList" :key="studyBoard.id">
      <div v-for="board in studyBoard" :key="board.id">
        <a href="#" @click="onFlag"><strong>{{ board.studyBoard.title }} / {{ board.nickname }}님</strong></a>
        <hr>
        <article-item :board="board" :onFlag="flag" @getArticleList="getArticleList"></article-item>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import ArticleItem from '@/components/ArticleItem.vue'


export default {
  name: 'ArticleList',
  components: {
    ArticleItem,
  },
  data: function () {
    return {
      flag: false
    }
  },
  props: {
    studyBoardList: {
      type: Object
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
    onFlag: function () {
      this.flag = !this.flag
    },
    getArticleList: function () {
      this.$emit('getArticleList')
    }
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
/*
    작성자 : 한슬기
    생성일 : 2022.02.04
    마지막 업데이트 : 2022.02.04
    
    스터디 방
 */
<template>
  <div class="container">
    <p class="icon">
      <font-awesome-icon class="fa-2x mb-2" icon="bell"/>
      <font-awesome-icon class="fa-2x mb-2" v-b-toggle.sidebar-right icon="edit"/>
        <b-sidebar id="sidebar-right" title="스터디 게시판" right shadow>
          <div class="px-3 py-2">
            <p class="article">
              <article-modal></article-modal>
              <article-list :studyBoardList="studyBoardList" @getArticleList="getArticleList()"></article-list>
            </p>
          </div>
        </b-sidebar>
      <font-awesome-icon class="clipboard fa-2x mb-2" icon="clipboard"/>
      <font-awesome-icon class="fa-2x mb-2" icon="calendar-week"/>
      <font-awesome-icon class="fa-2x mb-2" icon="cog"/>
    </p>
    <p class="icon2">
      <font-awesome-icon class="fa-2x mr-2" icon="user-friends"/>
      <font-awesome-icon class="fa-2x mr-2" icon="info-circle"/>
      <font-awesome-icon class="fa-2x mr-2" icon="comment-dots"/>
    </p>
  </div>
</template>

<script>
import axios from 'axios'
// import jwt_decode from 'jwt-decode'
import ArticleModal from '../../components/ArticleModal.vue'
import ArticleList from '../../components/ArticleList.vue'

export default {
  name: 'StudyRoom',
  components: {
    ArticleModal,
    ArticleList
  },
  data: function () {
    return {
      value: '',
      context: null,
      userno: null,
      studyBoardList: null,
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
    getArticleList: function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/study_board/boardlist/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data)
          this.studyBoardList = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },
    onContext(ctx) {
      this.context = ctx
    },
  },
  created: function () {
    this.getArticleList()
  }
}
</script>

<style scoped>
  .icon {
    display: flex;
    flex-direction: column;
  }
  .icon2 {
    float: right;
  }
  .clipboard {
    margin-left: 1px;
  }
  button { 
    font-size: 15px; 
    height: 38px; 
    background-color: rgb(130, 163, 209); 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
  .article {
    display: flex;
    flex-direction: column;
  }
</style>
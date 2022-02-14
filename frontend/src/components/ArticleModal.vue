<template>
<div class="container">
  <font-awesome-icon class="fa-1x mt-1 mr-1 float-left" icon="edit"/><a type="button" id="show-btn" class="float-left" style="color: rgb(130, 163, 209);" @click="showModal"><strong>글 작성하기</strong></a>  <!--수정-->
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
        required
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
        maxlength=100
        rows="6"
        max-rows="6"
        required
      >
      </b-form-textarea>
      <b-form-file class="mt-5" ref="file" method="post" enctype="multipart/form-data">
      </b-form-file>
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
        studyno: Number(this.$route.params.study_no),
        userno: null,
        files:null,
      },
    }
  },
  methods: {
    setToken: function () {
        const token = sessionStorage.getItem('jwt')    // 수정
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
      var file = this.$refs['file'].files[0]
      const formData = new FormData()

      formData.append('files', file)
      formData.append('title', this.article.title)
      formData.append('content', this.article.content)
      formData.append('studyno', this.article.studyno)
      formData.append('userno', this.article.userno)
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/study_board/create`,
        headers: this.setToken(),
        data: formData
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
      const token = sessionStorage.getItem('jwt')      // 수정
      const decoded = jwt_decode(token)
      this.article.userno = decoded.userno
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

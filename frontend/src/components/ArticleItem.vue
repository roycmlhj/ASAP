<template>
<div id="container">
  <div class="content" :style="{ height: post.studyBoard.content.length + 350 + 'px'}">
    <h5><strong>작성자 : {{ post.nickname }}</strong></h5>
    <div class="overflow" style="width: 100%; white-space: normal">{{ post.studyBoard.content }}</div>
    <hr>
    <h5 class="mb-1"><strong>첨부파일</strong></h5>
    <div v-for="file in post.studyBoard.fileList" :key="file.id">
      <div class="overflow" style="width: 100%; white-space: normal">
        <a href="#" @click="downloadFile(file)"><p class="mb-0">{{ file.ogfilename }}</p></a>
      </div>
    </div>
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
          maxlength=100
          rows="6"
          max-rows="6"
        >
        </b-form-textarea>
        <b-form-file class="mt-5" multiple="multiple" ref="file" method="post" enctype="multipart/form-data">
        </b-form-file>
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
        boardno: this.post.studyBoard.boardno,
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
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
    },
    downloadFile: function (file) {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/study_board/download/${file.fileno}`,
        headers: this.setToken(),
        responseType: "blob",
      })
        .then(res => {
          const url = window.URL
          .createObjectURL(new Blob([res.data]));
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', `${file.ogfilename}`);
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
        })
        .catch(err => {
          console.log(err)
        })
    },
    updateArticle: function () {
      var file = this.$refs['file'].files[0]
      const formData = new FormData()
      console.log(this.article.title, this.article.content, this.article.boardno)

      formData.append('files', file)
      formData.append('title', this.article.title)
      formData.append('content', this.article.content)
      formData.append('boardno', this.article.boardno)
      axios({
        method: 'put',
        url: `http://localhost:8080/api/v1/study_board/modify`,
        headers: this.setToken(),
        data: formData,
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
  created() {
    const token = localStorage.getItem('jwt')
    const decoded = jwt_decode(token)
    const userno = decoded.userno
    this.userno = userno
    // console.log(this.post.studyBoard.fileList[0].ogfilename, 111)
  }

}
</script>

<style scoped>
  #container {
    display: flex;
    flex-direction: column;
  }
  h5 {
    font-size: 16px;
  }
  p{
    font-size:15px;
  }
  .content {
    width: 250px;
    border: 1px solid #0000004d;
    position: relative;
    background-color: white;
    box-shadow: 5px 5px 5px 0px gray;
    word-break: break-all;
    white-space: pre;
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
  .overflow {
    overflow-y: auto;
    font-size: 15px;
  }
  button { 
    float: right;
    margin-right: 2px;
    font-size: 11px; 
    height: 34px; 
    background-color: rgb(130, 163, 209); 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
  a {
    color: black;
    font-size: 15px;
  }
  .p {
    margin-bottom: 4rem;
  }
</style>

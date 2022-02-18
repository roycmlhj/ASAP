<template>   <!--수정-->
<section>
  <div class="page-header2">
    <div class="d-flex justify-content-center">
      <font-awesome-icon icon="fa-solid fa-comments" class="icon fa-5x" style="color: rgb(231, 223, 223);" />
      <div>
        <h2 id="massage">스터디 모집 게시판</h2>
      </div>
    </div>
  </div>
  <div class="container">
    <h2 style="color: #394E79;">스터디 모집 및 찾기</h2>
    <div class="d-flex justify-content-center mb-5">
      <b-form-select class="mt-3 col-2" v-model="selected" :options="options"></b-form-select>
      <input class="form-control col-5" style="height: 40px;" @keyup.enter="search" v-model="searchWord">
      <button type="button" class="btn btn-round mt-3" @click="search">search</button>
    </div>
    <span class="row d-flex">
      <div class="col-12" v-for="study in studies" v-bind:key="study.id">
        <router-link :to="{ name: 'StudyBoardDetail', params: { boardno : study.boardno }}" style="color: black;">
          <div class="card mb-3 col-8">
            <div class="d-flex col-12">
              <div class="div col-6">
                <h4 class="mt-3 mb-3"><strong>{{ study.boardname }}</strong></h4>
                <p class="category mb-3" :style="{ width: study.category.length + 150 + 'px'}">{{ study.category }}</p>
                <div>
                  <badge v-for="topic in study.topics" class="mr-2" type="info" :key="topic.id">{{ topic }}</badge>
                </div>
                <p class="mt-2">작성일 : {{ study.timestamp }}</p>       <!--수정-->
              </div>
              <div class="col-6 d-flex justify-content-center">
                <div>
                  <div class="d-flex">
                    <p v-if="study.userImage" class="mt-2">
                      작성자 :  <img :src="study.userImage" alt="default-img">
                    </p>
                    <p v-else class="mt-2">
                      작성자 : <img src="./../accounts/assets/default.png">
                    </p>
                    <p class="name">{{ study.nickname }}</p>
                  </div>
                  <p class="mt-1 mb-2">스터디 모집 인원 : {{ study.maxmember }}명</p> 
                  <p class="mb-2">스터디 현재 인원 : {{ study.membercnt }}명</p>`
                  <p style="margin-top:-20px;"> <i class="fas fa-eye" /> 조회수 : {{ study.hit }} </p>     <!--수정-->
                </div>
              </div>
            </div>
          </div>
        </router-link>
      </div>
    </span>
    <div class="row d-flex justify-content-around overflow-auto">
      <b-pagination :currentPage="currentPage" :total-rows="rows" @page-click="pageClick" :number-of-pages="10"></b-pagination>
    </div>
  </div>
</section>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import {Badge} from '../../components'


  export default {
    name: 'StudyBoard',
    components: {
      [Badge.name]: Badge
    },
    data() {
      return {
        selected: "boardname",
        interestList: null,
        rows: 30,
        searchWord : null,
        studies : [],
        currentPage : 1,
        nickname: null,
        options: [
          { value: "boardname", text: '스터디 이름'},
          { value: "nickname", text: '작성자'},
          { value: "category", text: '스터디 카테고리'},
          //{ value: "interests", text: '스터디 주제'},
        ]
      }
    },
    methods: {
      setToken: function () {
        const token = sessionStorage.getItem('jwt')            // 수정
        const config = {
          Authorization: `JWT ${token}`
        }
        return config
      },
      search(){
        //검색합니다.
        var searchword = this.searchWord
        console.log(searchword)
        axios({
          method: 'get',
          url: `https://i6a107.p.ssafy.io:8443/api/v1/board/list/search?keyword=${searchword}&type=${this.selected}`,
          
        })
        .then(res => {
          console.log(res.data.content)
          console.log(res.data)
          this.studies = res.data
          for(var i = 0; i<this.studies.length;i++){
            this.studies[i].topics = this.studies[i].interests.split('#')
            this.studies[i].topics.shift()
          }
          this.currentPage=1
        })
        .catch(err => {
          console.log(this.title, err)
        })
        
      },
      pageClick(button, pageNum) {
        
        console.log(pageNum)
        if (this.searchWord){
          var searchword = this.searchWord
          console.log(searchword)
          axios({
            method: 'get',
            url: `https://i6a107.p.ssafy.io:8443/api/v1/board/list/search?keyword=${searchword}&page=${pageNum}&type=${this.selected}`,
            
          })
          .then(res => {
            this.studies = res.data
            for(var i = 0; i<this.studies.length;i++){
              this.studies[i].topics = this.studies[i].interests.split('#')
              this.studies[i].topics.shift()
            }
          })
          .catch(err => {
            console.log(this.title, err)
          })
        }
        else{
          axios({
            method: 'get',
            url: `https://i6a107.p.ssafy.io:8443/api/v1/board/list?page=${pageNum}&size=8`,
            headers: this.setToken()
          })
          .then(res => {
            console.log(res.data.content)
            
            this.studies = res.data.content
            for(var i = 0; i<this.studies.length;i++){
              this.studies[i].topics = this.studies[i].interests.split('#')
              this.studies[i].topics.shift()
              console.log(this.studies)
            }
          })
          .catch(err => {
            console.log(this.title, err)
          })
        }
      },
    },
    created(){
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/board/list?page=${1}&size=8`,
      })
      .then(res => {
        console.log(res.data.content)
        this.studies = res.data.content
        for(var i = 0; i<this.studies.length;i++){
          this.studies[i].topics = this.studies[i].interests.split('#')
          this.studies[i].topics.shift()
        }
      })
      .catch(err => {
        console.log(this.title, err)
      })
      if (sessionStorage.getItem('jwt')) {                 // 수정
        const token = sessionStorage.getItem('jwt')              // 수정
        const decoded = jwt_decode(token)
        this.nickname = decoded.nickname
      } else {
        this.$router.push({name: 'Login'})
      }
    },
  }
</script>

<style scoped> 
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Ubuntu:wght@500&display=swap");
  section {
    margin-top: 4rem;              /* 수정 */
  }
  .container {
    margin-bottom: 10rem;
  }
  .page-header2 {
    width: 100%;
    height: 150px;
    background-color: #394E79;
  }
  .div {
    display: flex;
    flex-direction: column;
    text-align: start;
  }
  h2 {
    color: rgb(231, 223, 223);
    font-family: 'Black Han Sans', sans-serif;
    margin-bottom: 0px;
  }
  #massage{
    margin-top: 3.5rem;
  }
  .icon {
    margin-top: 2rem;
    margin-right: 2rem;
  }
  h2 {
    font-family: 'Black Han Sans', sans-serif;
    margin-top: 3rem;
    margin-bottom: 1rem;
  }
  input {
    margin-top: 16px;
  }
  .card:hover {
    box-shadow: 0 0 50px #C2D2E9;
  }
  .category {
    text-align: center;
    background-color: rgb(248, 239, 228);
  }
  img {
    width: 50px;
    height: 50px;
    border-radius: 75%;
  }
  p {
    margin: 0px;
    text-align: start;
  }
  .name {
    margin-top: 20px;
    margin-left: 7px;
  }
  .divv {
    margin-left: 11rem;
  }
</style>

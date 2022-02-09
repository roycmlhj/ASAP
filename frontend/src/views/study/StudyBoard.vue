<template>
  <div class="container">
    <b-input-group class = "row">
      <b-form-select class = "col-2" v-model="selected" :options="options"></b-form-select>
      <b-form-input class="mb-3" @keyup.enter="search" v-model="searchWord"></b-form-input>
      <b-input-group-append>
        <b-button class="mb-3" @click="search">search</b-button>
      </b-input-group-append>
    </b-input-group>
    <span class="row d-flex">
      <div class="col-6 " v-for="study in studies" v-bind:key="study.id">
        <router-link :to="{ name: 'StudyBoardDetail', params: { boardno : study.boardno }}" style="color: black;">
          <b-card class = "mb-3 col-12">
          <div class="row d-flex justify-content-around">
            <h3>{{study.boardname}}</h3>
            <b-card-text v-if="study.is_recruit===0">
              {{"모집중"}}
            </b-card-text>
            <b-card-text v-else>
              {{"모집완료"}}
            </b-card-text>
            <b-card-text>
              {{study.membercnt}}/{{study.maxmember}}
            </b-card-text>
          </div>
          <div class="row d-flex justify-content-around">
            <b-card-text>
              <b-badge class="mx-1" v-for="topic in study.topics" v-bind:key="topic"> {{topic}}</b-badge>
            </b-card-text>
            <b-card-text>
              <b-avatar :src="study.studyLeaderIconTag"></b-avatar>
              {{study.nickname}}
            </b-card-text>
          </div>
          </b-card>
        </router-link>
      </div>
    </span>
    <div class="row d-flex justify-content-around overflow-auto">
      <b-pagination :currentPage="currentPage" :total-rows="rows" @page-click="pageClick" :number-of-pages="10"></b-pagination>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
//import axios from 'axios'

  export default {
    components: {},
    data() {
      return {
        selected: "boardname",
        rows:100,
        searchWord : null,
        studies : [],
        currentPage : 1,
        options: [
          { value: "boardname", text: '게시판 이름'},
          { value: "nickname", text: '작성자'},
          { value: "category", text: '스터디 카테고리'},
          //{ value: "interests", text: '스터디 주제'},
        ]
      }
    },
    methods: {
      search(){
        //검색합니다.
        var searchword = this.searchWord
        console.log(searchword)
        axios({
          method: 'get',
          url: `http://localhost:8080/api/v1/board/list/search?keyword=${searchword}&type=${this.selected}`,
          
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
      pageClick(button,pageNum) {
        
        console.log(pageNum)
        if (this.searchWord){
          var searchword = this.searchWord
          console.log(searchword)
          axios({
            method: 'get',
            url: `http://localhost:8080/api/v1/board/list/search?keyword=${searchword}&page=${pageNum}&type=${this.selected}`,
            
          })
          .then(res => {
            console.log(res.data.content)
            console.log(res.data)
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
            url: `http://localhost:8080/api/v1/board/list?page=${pageNum}&size=8`,
            
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
        }
        
      }
    },
    created(){
      //axios통신으로 게시글 목록 받아오기
      
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/board/list?page=${1}&size=8`,
        
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
      
    }
  }
</script>

<style scoped>

</style>

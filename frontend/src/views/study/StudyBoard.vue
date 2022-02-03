<template>
  <div class="container">
    <b-input-group>
      <b-form-input class="mb-3" v-model="searchWord"></b-form-input>
      <b-input-group-append>
        <b-button class="mb-3" @click="search">search</b-button>
      </b-input-group-append>
    </b-input-group>
    <span class="row d-flex justify-content-around">
      <div class="col-6 " v-for="study in studies" v-bind:key="study">
        <b-card class = "mb-3 col-12">
        <div class="row d-flex justify-content-around">
          <h3>{{study.title}}</h3>
          <b-card-text>
            {{"모집중"}}
          </b-card-text>
          <b-card-text>
            {{study.currentMember}}/{{study.member}}
          </b-card-text>
        </div>
        <div class="row d-flex justify-content-around">
        <b-card-text>
          <b-badge class="mx-1" v-for="topic in study.topic" v-bind:key="topic"> {{topic}}</b-badge>
        </b-card-text>
        <b-card-text>
          <b-avatar :src="study.studyLeaderIconTag"></b-avatar>
          {{study.studyLeader}}
        </b-card-text>
        </div>
        </b-card>
      </div>
    </span>
    <div class="row d-flex justify-content-around overflow-auto">
      <b-pagination-nav :link-gen="linkGen" :number-of-pages="studies.length/2"></b-pagination-nav>
    </div>
  </div>
</template>
<script>
//import axios from 'axios'
  export default {
    components: {},
    data() {
      return {
        searchWord : null,
        studies : [],
      }
    },
    methods: {
      search(){
        //검색합니다.
        var searchword = this.searchWord
        console.log(searchword)
      },
      linkGen(pageNum) {
        console.log("페이지 이동")
        
        var studies = [
          {title:'i',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
          {title:'j',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
          {title:'k',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
          {title:'l',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
          {title:'m',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
          {title:'n',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
          {title:'o',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
          {title:'p',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
        ]
        if (pageNum===1){
          studies = [
            {title:'a',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
            {title:'b',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
            {title:'c',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
            {title:'d',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
            {title:'e',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
            {title:'f',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
            {title:'g',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
            {title:'h',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
          ]
        }
        this.studies = studies
        return pageNum === 1 ? '?' : `?page=${pageNum}`
      }
    },
    mounted(){
      //axios통신으로 게시글 목록 받아오기
      const studies = [
        {title:'a',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
        {title:'b',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
        {title:'c',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
        {title:'d',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
        {title:'e',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
        {title:'f',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
        {title:'g',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
        {title:'h',topic:['a','b','c','d'],currentMember:2,member:6,studyLeader:'무직타이거',studyLeaderIconTag:''},
      ]
      this.studies = studies
      console.log(studies)
    }
  }
</script>
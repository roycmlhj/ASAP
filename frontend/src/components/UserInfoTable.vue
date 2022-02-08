/*
    작성자 : 한슬기
    생성일 : 2022.02.01
    마지막 업데이트 : 2022.02.01
    
    마이페이지 > 과제, 스터디 목록 테이블
 */
<template>
  <div class="container">
    <h5 class="float-left"><strong>스터디 목록</strong></h5>
    
    <b-table
      id="my-table"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
    >
    </b-table>
    
    <b-pagination
      class="justify-content-center"
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination>
  </div>
</template>

<script>
import moment from 'moment';

export default {
  name: 'UserInfoTable',
  props: {
    studyList: {
      type: Array,
    }
  },
  data() {
    return{
      perPage: 5,
      currentPage: 1,
      items : [],
    }
  },
  computed: {
    rows() {
      
      return this.studyList.length
    }
  },
  methods: {
    dateTime(value) {
      return moment(value).format('YYYY-MM-DD');
    },
  },
  created() {
    console.log(this.studyList,"UHT")
    for(var i = 0; i<this.studyList.length;i++){
      this.items.push({
        스터디:this.studyList[i].studyname,
        스터디주제: this.studyList[i].category,
        시작일: this.dateTime(this.studyList[i].timestamp),
        진행상태: ''
      })
      if(!this.studyList[i].isActivate){
        this.items[i].진행상태="진행중"
      }else{
        this.items[i].진행상태="종료"
      }
    }
  }
}
</script>

<style>
  #th {
    background-color: #e2e0e6;
  }
</style>

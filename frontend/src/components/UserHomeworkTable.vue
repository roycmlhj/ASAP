/*
    작성자 : 한슬기
    생성일 : 2022.02.07
    마지막 업데이트 : 2022.02.07
    
    마이페이지 > 과제 목록 테이블
 */
<template>
  <div>
    <h5 class="float-left"><strong>과제 목록</strong></h5><br>
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
  data() {
    return{
      perPage: 5,
      currentPage: 1,
      items : [],
    }
  },
  props: {
    homeworkList: {
      type: Array,
    }
  },
  methods: {
    dateTime(value) {
      return moment(value).format('YYYY-MM-DD');
    },
  },
  computed: {
    rows() {
      
      return this.homeworkList.length
    }
  },
  created() {
    console.log(this.homeworkList,"UHT")
    for(var i = 0; i<this.homeworkList.length;i++){
      this.items.push({
        스터디:this.homeworkList[i].studyname,
        과제: this.homeworkList[i].homework.title,
        제출기한: this.dateTime(this.homeworkList[i].endDate),
        진행상태: '',
      })
      console.log(456)
      if(!this.homeworkList[i].isActivate){
        this.items[i].진행상태="진행"
      }else{
        this.items[i].진행상태="완료"
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

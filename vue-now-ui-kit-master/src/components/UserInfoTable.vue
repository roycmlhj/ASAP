<template>
  <div class="studyList">
    <h5 class="d-flex justify-content-start mb-1"><strong>스터디 목록</strong></h5>
    <p class="d-flex justify-content-start">진행 중인 스터디 : {{ ingStudy }}개</p>
    <p class="d-flex justify-content-start">완료한 스터디 : {{ endStudy }}개 </p>
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
      endStudy : 0,
      ingStudy : 0,
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
        this.items[i].진행상태="진행"
        this.ingStudy += 1
      }else{
        this.items[i].진행상태="종료"
        this.endStudy += 1
      }
    }
  }
}
</script>

<style scoped>
  #th {
    background-color: #e2e0e6;
  }
  .studyList {
    display: flex;
    flex-direction: column;
  }
  p {
    margin: 0;
  }
</style>

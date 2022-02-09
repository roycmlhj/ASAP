<template>
  <div class="container">
    <full-calendar :events="fcEvents" locale="en"
      @dayClick="dayClick"
      @eventClick="eventClick"
    ></full-calendar>
    <b-modal
      v-model="modalShow1"
      @ok="createSchedule"
      title="Create schedule"
      id="modal-lg" size="lg"
      ok-only
    >
      <h5>다음 스터디 일정을 {{ this.selecteddate }}로 설정하시겠습니까?</h5>
    </b-modal>
    <b-modal
      v-model="modalShow2"
      @ok="deleteSchedule"
      title="Delete schedule"
      ok-only
    >
      <h5>스터디 일정을 삭제하시겠습니까?</h5>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
export default {
  name: 'calendar',
  components: {
   'full-calendar': require('vue-fullcalendar')
  },
  props: {
    demoEvents: {
      type: Array
    },
    studyno: {
      type:String
    },
    studyLeaderno:{
      type:Number
    }
  },
  methods:{
    eventClick(event,jsEvent,pos){
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      const userno = decoded.userno
      this.userno = userno
      console.log("event")
      console.log(event,jsEvent,pos)
      if(this.studyLeaderno==userno){
        this.modalShow2=!this.modalShow2
      }
      this.selectedEvent=event.eventno
      this.selectedEventflag=event.isHomework
      console.log(this.selectedEvent)
    },
    deleteSchedule(){
      if(this.selectedEventflag){
        
        axios({
          method:'delete',
          url:`http://localhost:8080/api/v1/homework/delete/${this.selectedEvent}`
        }).then(res=>{
          console.log(res)
          window.location.reload()
        }).catch(err=>{
          console.log(err)
        })
      }else{
        axios({
          method:'delete',
          url:`http://localhost:8080/api/v1/study/schedule/delete/${this.selectedEvent}`
        }).then(res=>{
          console.log(res)
          window.location.reload()
        }).catch(err=>{
          console.log(err)
        })
        
      }
      
      console.log("delete")
    },
    dayClick(day,jsEvent){
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      const userno = decoded.userno
      this.userno = userno
      console.log("leaderno",this.studyLeaderno)
      console.log(this.studyno)
      console.log(typeof(day))
      var dayarray = String(day).split(' ')
      this.selecteddate=dayarray[3]+'년 '+this.monthToNum[dayarray[1]]+'월 '+dayarray[2]+'일'
      if(this.studyLeaderno==userno){
        this.modalShow1=!this.modalShow1
      }
      console.log(this.selecteddate)
      console.log(jsEvent)

    },
    createSchedule(){
      console.log(this.selecteddate)
      const data = {
        nextDate:this.selecteddate,
        studyno:this.studyno,
      }
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/study/schedule/create`,
        data: data,
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
  data: function () {
    return {
      fcEvents : this.demoEvents,
      selecteddate : '',
      selectedEvent: -1,
      selectedEventflag:false,
      monthToNum:{
        Nan:'01',
        Feb:'02',
        Mar:'03',
        Apr:'04',
        May:'05',
        Jun:'06',
        Jul:'07',
        Aug:'08',
        Sep:'09',
        Oct:'10',
        Nov:'11',
        Dec:'12',
      },
      modalShow1:false,
      modalShow2:false,
      userno:0,
    }
  },
}
</script>

<style scoped>
  h5 {
    text-align: center;
  }
</style>

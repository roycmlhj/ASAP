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
      <h5>시간을 정해주십시오.</h5>
      <b-form-select class="col-1" v-model="selectedHour" :options="optionsHour"></b-form-select>시
      <b-form-select class="col-1" v-model="selectedMinute" :options="optionsMinute"></b-form-select>분

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
  created(){
    console.log(this.optionsHour,"12312312313123123")
    for(var i = 0; i<6;i++){
      this.optionsMinute.push({value:i*10 , text:`${i*10}`})
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
      this.selecteddate=dayarray[3]+'/'+this.monthToNum[dayarray[1]]+'/'+dayarray[2]
      if(this.studyLeaderno==userno){
        this.modalShow1=!this.modalShow1
      }
      console.log(this.selecteddate)
      console.log(jsEvent)

    },
    createSchedule(){
      console.log(this.selecteddate)

      const data = {
        nextDate:this.selecteddate+`/${this.selectedHour}/${this.selectedMinute}`,
        studyno:this.studyno,
      }
      console.log(data.nextDate,11111)
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
      optionsHour: [
        { value:0, text:'0'},
        { value:1, text:'1'},
        { value:2, text:'2'},
        { value:3, text:'3'},
        { value:4, text:'4'},
        { value:5, text:'5'},
        { value:6, text:'6'},
        { value: 7, text:' 7'},
        { value: 8, text:' 8'},
        { value: 9, text:' 9'},
        { value: 10, text:' 10'},
        { value: 11, text:' 11'},
        { value: 12, text:' 12'},
        { value: 13, text:' 13'},
        { value: 14, text:' 14'},
        { value: 15, text:' 15'},
        { value: 16, text:' 16'},
        { value: 17, text:' 17'},
        { value: 18, text:' 18'},
        { value: 19, text:' 19'},
        { value: 20, text:' 20'},
        { value: 21, text:' 21'},
        { value: 22, text:' 22'},
        { value: 23, text:' 23'},
      ],
      optionsMinute : [],
      selectedHour: 0,
      selectedMinute: 0,
    }
  },
}
</script>
<style>
  .homeworkCalendar {
    background : #FFFF8C
  }
</style>
<style scoped>
  h5 {
    text-align: center;
  }
  
</style>

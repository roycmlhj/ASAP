<template>
  <div class="container">
    <full-calendar :events="fcEvents" locale="en" 
      @dayClick="dayClick"
      @eventClick="eventClick"
    ></full-calendar>
    <modal
      :show.sync = "modalShow1"
      :show-close="false"
      class="modal-mini"
      @ok="createSchedule"
      title="Create schedule"
      ok-only
    >
    <div class="d-flex justify-content-center">
      <font-awesome-icon icon="fa-solid fa-clock" class="fa-4x mb-3" style="color: rgb(130, 163, 209); " />
    </div>
      <h5>다음 스터디 시간을 <br> 정해주세요.</h5>
      <div class="mt-4 d-flex justify-content-center">
        <b-form-select class="col-4" v-model="selectedHour" :options="optionsHour"></b-form-select><p class="mt-2">&nbsp;시&nbsp;&nbsp;&nbsp;</p>
        <b-form-select class="col-4" v-model="selectedMinute" :options="optionsMinute"></b-form-select><p class="mt-2">&nbsp;분</p>
      </div>
      <div class = "d-flex justify-content-around mt-3">
        <button type="button" class = "btn mx-1" style="background-color: white; color: black;" @click="createSchedule" link>Create</button>
        <button type="button" class = "btn mx-1" style="background-color: white; color: black;" link @click="modalShow1 = false">Close</button>
      </div>
    </modal>
    <modal
      class="modal-mini"
      :show.sync = "modalShow2"
      :show-close="false"
      @ok="deleteSchedule"
      title="Delete schedule"
      ok-only
    > <div v-if="scheduleTime != null">
        <h5>다음 스케줄 시간 <br> {{ scheduleTime }}</h5> 
      </div>
      <div class = "d-flex justify-content-center"> 
        <button v-if="userno!=studyLeaderno" type="button" class = "btn mx-1" link @click="modalShow2 = false">Close</button> 
      </div>
      <div v-if="userno==studyLeaderno">
        <h5>스터디 일정을 <br> 삭제하시겠습니까?</h5>
        <div class = "d-flex justify-content-around">
          <button type="button" class = "btn mx-1" style="background-color: white; color: black;" @click="deleteSchedule" link> Delete </button>
          <button type="button" class = "btn mx-1" style="background-color: white; color: black;" link @click="modalShow2 = false">Close</button>
        </div>
      </div>
    </modal>
  </div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import {Modal} from './'

export default {
  name: 'calendar',
  data() {
    return {
      scheduleTime:'',
    }
  },
  components: {
   'full-calendar': require('vue-fullcalendar'),
   Modal
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
      console.log(event,jsEvent,pos,"일정 클릭")
      this.modalShow2=!this.modalShow2
      const time = event.end.split(' ')[1]
      this.scheduleTime = time.substr(0,5)
      console.log(this.scheduleTime,"스케줄시간")
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
          url: `http://localhost:8080/api/v1/study/schedule/delete/${this.selectedEvent}`
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

<style scoped>
  .homeworkCalendar {
    background : #FFFF8C
  }
  h5 {
    text-align: center;
  }
</style>


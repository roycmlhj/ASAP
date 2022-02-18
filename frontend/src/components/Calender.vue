
<template>
  <div class="container">
    <full-calendar :events="fcEvents" locale="en"></full-calendar>
  </div>
</template>

<script>
import axios from 'axios'
var demoEvents = [
	
]
export default {
  name: 'Calender',
  components: {
   'full-calendar': require('vue-fullcalendar')
  },
  data: function () {
    return {
      fcEvents : demoEvents
    }
  },
  method: {
    dayClick(day){
      console.log(day)
    }
  }
  ,
  created() {
    axios({
      method: 'get',
      url: `http://localhost:8080/api/v1/study/calendar/18`,
    })
    .then(res => {
      console.log(res)
      const homeworkList = res.data.homeworkList
      for(var i = 0; i<homeworkList.length;i++){
        demoEvents.push({
          title:homeworkList[i].title,
          start:homeworkList[i].endDate,
          end:homeworkList[i].endDate,
          
        })
      }
    })
    .catch(err => {
      console.log(err)
    })
  }
}
</script>

<style scoped>
  
</style>
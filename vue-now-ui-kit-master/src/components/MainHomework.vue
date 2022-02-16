<template>
  <div>
    <br>
    <div v-for="homework in getHomeworkList" :key="homework.id" >
      <p>{{homework.homework.title}}</p>
      </div>
  </div>
</template>

<script>

export default {
  name: 'MainHomework',
  props: {
    onHomeworkList: {
      type: Array
    },
    doneHomeworkList: {
      type: Array
    },
    
  },
  data() {
    return {
      
    }
  },
  methods: {
    dayToNum(day) {
      const beforenum = day.split(' ')[0].split('-')
      const months = [0,31,59,90,120,151,181,212,243,273,304,334,365]
      
      // console.log(beforenum)
      var res = Number(beforenum[0])*365+months[Number(beforenum[1])-1]+Number(beforenum[2])
      return res
    }
  },
  computed: {
    getHomeworkList() {
      var homeworkList = []
      const months = [0,31,59,90,120,151,181,212,243,273,304,334,365]
      var today = new Date()
      var year = today.getFullYear()
      var month = today.getMonth()
      var date = today.getDate()
      var day = today.getDay()
      var weekstart = year*365+months[month]+date-day
      for(var i = 0; i<this.doneHomeworkList.length;i++){
        var hwDay = this.dayToNum(this.doneHomeworkList[i].homework.endDate)
        if(hwDay <= weekstart+7 && hwDay>weekstart){
          homeworkList.push(this.doneHomeworkList[i])
        }
      }
      for(i = 0; i<this.onHomeworkList.length;i++){
        hwDay = this.dayToNum(this.onHomeworkList[i].homework.endDate)
        if(hwDay <= weekstart+7 && hwDay>weekstart){
          homeworkList.push(this.onHomeworkList[i])
        }
      }
      homeworkList.sort(function(a,b){
        if(a.homework.endDate < b.homework.endDate) return -1;
        if(a.homework.endDate > b.homework.endDate) return 1;
        if(a.homework.endDate == b.homework.endDate) return 0;
      })
      return homeworkList
    },
  }
}
</script>
<template>
  <div>
    <h4>오늘 진행되는 스터디</h4>
    <div v-for="study in getStudyList" :key="study.id" >
      <p>{{study.studyname}}</p>
    </div>
  </div>
</template>

<script>

export default {
  name: 'MainTodayStudy',
  props: {
    studyList:{
      type:Array
    }
    
  },
  data() {
    return {
      
    }
  },
  methods: {
    dayToNum(day) {
      const beforenum = day.split(' ')[0].split('-')
      const months = [0,31,59,90,120,151,181,212,243,273,304,334,365]
      
      //console.log(beforenum)
      var res = Number(beforenum[0])*365+months[Number(beforenum[1])-1]+Number(beforenum[2])
      return res
    }
  },
  computed: {
    getStudyList() {
      var studyList = []
      
      const months = [0,31,59,90,120,151,181,212,243,273,304,334,365]
      var today = new Date()
      var year = today.getFullYear()
      var month = today.getMonth()
      var date = today.getDate()
      var todaynum = year*365+months[month]+date
      for(var i = 0; i<this.studyList.length;i++){
        for(var j = 0; j<this.studyList[i].scheduleList.length;j++){
          if(this.dayToNum(this.studyList[i].scheduleList[j].nextDate)==todaynum){
            studyList.push(this.studyList[i])
            break
          }
        }
      }
      console.log(studyList,123123123)
      // homeworkList.sort(function(a,b){
      //   if(a.endDate<b.endDate) return -1;
      //   if(a.endDate > b.endDate) return 1;
      //   if(a.endDate == b.endDate) return 0;
      // })
      return studyList
    },
  }
}
</script>

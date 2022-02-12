/*
    작성자 : 한슬기
    생성일 : 2022.02.09
    마지막 업데이트 : 2022.02.09
    
    마이페이지 > 스터디 파이차트
 */
<template>
  <div id="container">
    <h5 class="d-flex justify-content-start"><strong>스터디 분석</strong></h5>
    <p>{{ userName }}님의 한달 스터디 시간은 26시간입니다.</p>
    <pie-chart :chart-data="datacollection" style="width: 350px;"></pie-chart>
  </div>
</template>

<script>
  import PieChart from '../PieChart.js'
  import jwt_decode from 'jwt-decode'

  export default {
    components: {
      PieChart
    },
    props: {
      studyTime: {
        type: Array
      }
    },
    data: function () {
      return {
        color : ["#394E79", "#5E83BA", "#C2D2E9", "#E3C5D5", "#9A8BA5", "#DB7093", "#7B68EE", "#DDA0DD", "#FAFAD2", "#E9967A"],
        datacollection:  {
          labels: [],
          datasets: [
            {
              backgroundColor: [],
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }, 
          ]
        },
        userName: null,
      }
    },
    methods : {
      getRandomInt () {
        return Math.floor(Math.random() * (50 - 5 + 1)) + 5
      },
      getStudyName: function () {
        var array = new Array(this.studyTime.length)
        for (let index = 0; index < this.studyTime.length; index++) {
          array[index] = this.studyTime[index].studyName
        }
        return array
      },
      getBackgroundColor: function () {
        var array = new Array(this.studyTime.length)
        for (let index = 0; index < this.studyTime.length; index++) {
          array[index] = this.color[index]
        }
        return array
      },
      getStudyTime: function () {
        var array = new Array(this.studyTime.length)
        for (let index = 0; index < this.studyTime.length; index++) {
          array[index] = this.studyTime[index].time
        }
        return array
      }
    },
    created: function () {
      if (localStorage.getItem('jwt')) {
        console.log(this.studyTime, 555)
        const token = localStorage.getItem('jwt')
        const decoded = jwt_decode(token)
        this.userName = decoded.nickname
        const studyName = this.getStudyName()
        this.datacollection.labels = studyName
        const backgroundColor = this.getBackgroundColor()
        this.datacollection.datasets[0].backgroundColor = backgroundColor

        // const studyTime = this.getStudyTime()
        // this.datacollection.datasets[0].data = studyTime
      } 
      else {
        this.$router.push({name: 'Login'})
      }
    }
  }
</script>

<style scoped>
  #container {
    display: flex;
    flex-direction: column;
  }
</style>
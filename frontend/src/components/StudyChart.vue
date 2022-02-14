<template>   <!--전체적으로 수정-->
  <div id="container">
    <h5 class="d-flex justify-content-start"><strong>스터디 분석</strong></h5>
    <p>
      {{ userName }}님의 한달 스터디 시간은 {{ parseInt(totalTime) }}시간입니다.
    </p>
    <!--수정-->
    <pie-chart :chart-data="datacollection" style="width: 350px"></pie-chart>
  </div>
</template>

<script>
import PieChart from "../PieChart.js";
import jwt_decode from "jwt-decode";

export default {
  components: {
    PieChart,
  },
  props: {
    studyTime: {
      type: Array,
    },
    totalTime: {
      // 수정
      type: Number,
    },
  },
  data: function () {
    return {
      color: [
        "#394E79",
        "#5E83BA",
        "#C2D2E9",
        "#E3C5D5",
        "#9A8BA5",
        "#DB7093",
        "#7B68EE",
        "#DDA0DD",
        "#FAFAD2",
        "#E9967A",
      ],
      datacollection: {
        labels: [],
        datasets: [
          {
            backgroundColor: [],
            data: [],
          },
        ],
      },
      userName: null,
    };
  },
  methods: {
    getRandomInt() {
      return Math.floor(Math.random() * (50 - 5 + 1)) + 5;
    },
    getStudyName: function () {
      var array = new Array(this.studyTime.length);
      for (let index = 0; index < this.studyTime.length; index++) {
        array[index] = this.studyTime[index].studyName;
      }
      return array;
    },
    getBackgroundColor: function () {
      var array = new Array(this.studyTime.length);
      for (let index = 0; index < this.studyTime.length; index++) {
        array[index] = this.color[index];
      }
      return array;
    },
    getStudyTime: function () {
      var array = new Array(this.studyTime.length);
      for (let index = 0; index < this.studyTime.length; index++) {
        array[index] = this.studyTime[index].time;
      }
      return array;
    },
  },
  created: function () {
    if (sessionStorage.getItem("jwt")) {
      // 수정
      const token = sessionStorage.getItem("jwt"); // 수정
      const decoded = jwt_decode(token);
      this.userName = decoded.nickname;
      const studyName = this.getStudyName();
      this.datacollection.labels = studyName;
      const backgroundColor = this.getBackgroundColor();
      this.datacollection.datasets[0].backgroundColor = backgroundColor;
      const studyTime = this.getStudyTime();
      this.datacollection.datasets[0].data = studyTime;
    } else {
      this.$router.push({ name: "Login" });
    }
  },
};
</script>

<style scoped>
#container {
  display: flex;
  flex-direction: column;
}
</style>

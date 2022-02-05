/*
    작성자 : 한슬기
    생성일 : 2022.02.04
    마지막 업데이트 : 2022.02.04
    
    스터디 방 > 과제 리스트
 */
<template>
  <div class="container">
    <div v-for="homework in homeworkList" :key="homework.id">
      <div v-for="work in homework" :key="work.id">
        <a href="#" @click="onFlag"><strong>{{ work.homework.title }} / {{ work.nickname }}님</strong></a>
        <hr>
        <homework-item :work="work" :onFlag="flag" @getHomeworkList="getHomeworkList"></homework-item>
      </div>
    </div>
  </div>
</template>

<script>
import HomeworkItem from '@/components/HomeworkItem.vue'

export default {
  name: 'HomeworkList',
  components: {
    HomeworkItem
  },
  data: function () {
    return {
      flag: false,
    }
  },
  props: {
    homeworkList: {
      type: Object
    }
  },
  methods: {
    onFlag: function () {
      this.flag = !this.flag
    },
    getHomeworkList: function () {
      this.$emit('getHomeworkList')
    }
  }
}
</script>

<style scoped>
  button { 
    font-size: 15px; 
    height: 38px; 
    background-color: rgb(130, 163, 209); 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
  a {
    color: black;
  }
  .container {
    margin-top : 2rem;
    text-align: left;
  }
</style>
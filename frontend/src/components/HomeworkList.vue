<template>
  <div class="container">
    <div v-for="homework in homeworkList" :key="homework.id">
      <a type="button" @click="getHomeworkInformation(homework.homework.homeworkno)"><strong> {{ homework.homework.title }}</strong></a>  <!--수정-->
      <hr>
      <p v-if="assignment && assignment.homework.homeworkno == homework.homework.homeworkno">
        <homework-item :assignment="assignment"></homework-item>
      </p>
    </div>
  </div>
</template>

<script>
import HomeworkItem from '@/components/HomeworkItem.vue'
import axios from 'axios'

export default {
  name: 'HomeworkList',
  data: function () {
    return {
      assignment: null
    }
  },
  components: {
    HomeworkItem
  },
  props: {
    homeworkList: {
      type: Array
    }
  },
  methods: {
    setToken: function () {
      const token = sessionStorage.getItem('jwt')                  // 수정
      const config = {
        Authorization: `JWT ${token}`
      }
      return config
    },
    getHomeworkInformation: function (homeworkno) {
      if (this.assignment != null && homeworkno == this.assignment.homework.homeworkno) {
        this.assignment.homework.homeworkno = -1
      } else {
        axios({
          method: 'get',
          url: `http://localhost:8080/api/v1/homework/homework/detail/${homeworkno}`,
          headers: this.setToken(),
        })
          .then(res => {
            this.assignment = res.data
            this.assignment.homework.content.replace(/\n/g,"<br>")
            console.log(this.assignment.homework.content, 789)
          })
          .catch(err => {
            console.log(err)
          })
      }
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

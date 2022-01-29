<template>
  <div class="container">
    <h3 class="float-left"><strong>회원리스트</strong></h3>
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th scope="col">UserNo</th>
          <th scope="col">E-mail</th>
          <th scope="col">Nickname</th>
          <th scope="col">Register Date</th>
          <th scope="col">State</th>
        </tr>
      </thead>
      <tbody v-for="users in userlist" :key="users.id">
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.userno }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.nickname }}</td>
          <td>{{ user.regidate }}</td>
          <td v-if="user.delFlag == 0">가입</td>
          <td v-else>탈퇴</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Admin',
  data: function () {
    return {
      userlist: null,
    }
  },
  methods: {
    setToken: function () {
      const token = localStorage.getItem('jwt')
      const config = {
        Authorization: `JWT ${token}`
      }
      return config
    },
    getUserList: function () {
      axios({
        method: 'get',
        url: 'http://localhost:8080/api/v1/admin/userlist',
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res)
          this.userlist = res.data
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created: function () {
    this.getUserList()
  }
}
</script>

<style scoped>
  th {
    background-color: #E9DDF4;
  }
</style>
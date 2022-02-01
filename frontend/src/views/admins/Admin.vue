/*
    작성자 : 한슬기
    생성일 : 2022.01.30
    마지막 업데이트 : 2022.01.30
    
    관리자 페이지
 */
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
          <th scope="col"></th>
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
          <td v-if="user.delFlag == 0">
            <b-button id="show-btn" size="sm" style="background-color: rgb(221, 182, 74);" @click="showModal(user)">Detail</b-button>
          </td>
        </tr>
      </tbody>
    </table>
    <b-modal ref="my-modal" :member="member" hide-header hide-footer>
      <div class="d-block text-center">
        <div v-if="member">
          <user-info-modal :member="member" :studylist="studylist"></user-info-modal>
        </div>
      </div>
      <div class="float-right mt-4">
        <b-button style="background-color: #A5A6F6;" @click="userUpdate(member.userno)">수정</b-button>&nbsp;
        <b-button style="background-color: #A5A6F6;" @click="userDelete(member.userno)">강퇴</b-button>&nbsp;
        <b-button style="background-color: #A5A6F6;" @click="hideModal">취소</b-button>
    </div>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios'
import UserInfoModal from '@/components/UserInfoModal.vue'

export default {
  name: 'Admin',
  components: {
    UserInfoModal
  },
  data: function () {
    return {
      userlist: null,
      member: null,
      modal: false,
      studylist: null,
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
    showModal: function (user) {
      this.$refs['my-modal'].show()
      this.getUserInformation(user)
    },
    hideModal() {
      this.$refs['my-modal'].hide()
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
    },
    getUserInformation: function (user) {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/admin/${user.userno}/`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res)
          this.member = res.data.user
          this.studylist = res.data.studyList
        })
        .catch(err => {
          console.log(err)
        })
    },
    userDelete: function (userNo) {
      axios({
        method: 'delete',
        url: `http://localhost:8080/api/v1/user/${userNo}/`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res)
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
    },
    userUpdate: function (userNo) {
      this.$router.push({ name: 'ProfileEdit', params: { user_no : userNo }})
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
  button {
    font-size: 11px;
  }
</style>
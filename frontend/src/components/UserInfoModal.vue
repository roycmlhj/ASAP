/*
    작성자 : 한슬기
    생성일 : 2022.01.30
    마지막 업데이트 : 2022.02.01
    
    회원 상세 정보 모달
 */
<template>
  <div class="container">
    <p><img src="https://cdn.imweb.me/thumbnail/20200606/09c71b2f94ea5.jpg" alt="default_image"></p>
    <p>{{ member.email }}</p>
    <table class="table table-bordered">
      <tbody>
        <tr>
          <th>가입일</th>
          <td>{{ member.regidate }}</td>
        </tr>
        <tr>
          <th>관심분야</th>
          <td>{{ member.mainCategory }}</td>
        </tr>
        <tr>
          <th>세부관심분야</th>
          <td style="width: 70%;"><user-interests :interests="member.interests"></user-interests></td>
        </tr>
        <tr>
          <th>포인트 및 레벨</th>
          <td>{{ member.exp }} point  {{ member.level }} level</td>
        </tr>
        <tr>
          <th :rowspan="studylist.length + 1">스터디</th>
          <td v-if="studylist.length >= 1">{{ studylist[0].studyname }}<a href="#" @click="userKick">퇴출</a></td>
        </tr>
        <tr v-for="(study, index) in studylist" :key="index.id">
          <td v-if="index != 0 && studylist.length >= 1">{{ study.studyname }}<a href="#" @click="userKick">퇴출</a></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
import UserInterests from '@/components/UserInterests.vue'

export default {
  name: 'UserModal',
  components: {
    UserInterests
  },
  props: {
    member: {
      type: Object
    },
    studylist: {
      type: Array
    }
  },
  data: function () {
    return {
      userInfo: {
        userno: 3,
        studyno: 8
      }
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
    userKick: function () {
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/admin/kick/`,
        data: this.userInfo
      })
        .then(res => {
          console.log(res)
          window.location.reload()
          alert("스터디에서 해당 회원을 강퇴시켰습니다.")
        })
        .catch(err => {
          console.log(err)
        })
    },
  }
}
</script>

<style scoped>
  img {
    width: 70px;
    height: 70px;
    border-radius: 70%;
  }
  a {
    color: red;
    margin-left: 1rem;
    font-size: 13px;
  }
</style>
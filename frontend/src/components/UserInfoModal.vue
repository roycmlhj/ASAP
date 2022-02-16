<template>
  <div class="container">
    <p v-if="member.image">
      <img :src="member.image" alt="default-img">
    </p>
    <p v-else>
      <img src="../views/accounts/assets/default.png">
    </p>
    <h5><strong>{{ member.email }}</strong></h5>
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
          <td><user-interests :interestList="getInterests(member)"></user-interests></td>
        </tr>
        <tr>
          <th>Level</th>
          <td>
            <p v-if="this.member.level == 1" class="mb-0">
              <font-awesome-icon class="fa-2x mb-2" icon="star"/>
            </p>
            <p v-if="member.level == 2" class="mb-0">
              <font-awesome-icon class="fa-2x mb-2" icon="medal"/>
            </p>
            <p v-if="member.level == 3" class="mb-0">
              <font-awesome-icon class="fa-2x mb-2" icon="bahai"/>
            </p>
            <p v-if="member.level == 4" class="mb-0">
              <font-awesome-icon class="fa-2x mb-2" icon="award"/>
            </p>
            <p v-if="member.level == 5" class="mb-0">
              <font-awesome-icon class="fa-2x mb-2" icon="trophy"/>
            </p>
          </td>
        </tr>
        <tr>
          <th :rowspan="studylist.length + 1">스터디</th>
          <td v-if="studylist.length >= 1">{{ studylist[0].studyname }}<a v-if="isAdmin == 1" type="button" @click="userKick(studylist[0].studyno, member.userno)">퇴출</a></td>    <!--수정-->
        </tr>
        <tr v-for="(study, index) in studylist" :key="index.id">
          <td v-if="index != 0 && studylist.length >= 1">{{ study.studyname }}<a v-if="isAdmin == 1" type="button" @click="userKick(study.studyno, member.userno)">퇴출</a></td>      <!--수정-->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
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
    },
    leader: {
      type: Number
    }
  },
  data: function () {
    return {
      userInfo: {
        userno: null,
        studyno: null,
      },
      isAdmin: null,
      userNumber: null,
    }
  },
  methods: {
    setToken: function () {
      const token = sessionStorage.getItem('jwt')                 // 수정
      const config = {
        Authorization: `JWT ${token}`
      }
      return config
    },
    userKick: function (studyno, userno) {
      this.userInfo.studyno = studyno
      this.userInfo.userno = userno
      axios({
        method: 'post',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/admin/kick`,
        data: this.userInfo
      })
        .then(res => {
          console.log(res, this.userInfo)
          alert("스터디에서 해당 회원을 강퇴시켰습니다.")
          window.location.reload()
        })
        .catch(err => {
          console.log(err, this.userInfo)
        })
    },
    getInterests: function (member) {
      this.interestList = member.interests.split('#')
      this.interestList.shift()
      return this.interestList
    },
  },
  created: function () {
    if (sessionStorage.getItem('jwt')) {                        // 수정
      const token = sessionStorage.getItem('jwt')                    // 수정
      const decoded = jwt_decode(token)
      this.isAdmin = decoded.isAdmin
      this.userNumber = decoded.userno
      console.log(this.leader)
    } else {
      this.$router.push({name: 'Login'})
    }
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
  h5 {
    font-size: 17px;
  }
</style>
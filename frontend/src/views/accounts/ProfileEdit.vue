<template>
<section>      <!--수정!! state 관련된거 지움-->
  <div class="container">
    <h2>개인 정보 수정</h2>
    <b-card bg-variant="light" class="card mb-4">
      <h5><strong>프로필</strong></h5>
      <p v-if="img">
        <img :src="img" alt="default-img">
      </p>
      <p v-else>
        <img src="./assets/default.png">
      </p>
      <p>{{ this.user.email }}</p>
      <a type="button" class="float-right" @click="userDelete">회원탈퇴</a>      <!--수정-->
    </b-card>
    <b-card bg-variant="light" class="input">
      <b-form-group
        label="비밀번호"
        label-for="password"
        label-cols-sm="1"
      >
        <b-form-input 
          id="password"
          type="password" 
          v-model="user.password" 
          :state="passwordState"
          aria-describedby="input-live-help input-live-feedback"
          placeholder="비밀번호는 문자, 숫자, 특수문자 포함 8자 이상이어야 합니다."
        ></b-form-input>
        <!-- <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
          비밀번호는 문자, 숫자, 특수문자 포함 8자 이상이어야 합니다.
        </b-form-invalid-feedback> -->
      </b-form-group>
      <b-form-group
        label="비밀번호"
        label-for="passwordcheck"
        label-cols-sm="1"
      >
        <b-form-input 
          id="passwordcheck" 
          type="password"
          v-model="passwordcheck"
          :state="passwordcheckState"
          aria-describedby="input-live-help input-live-feedback" 
          placeholder="비밀번호를 한번 더 입력해주세요."
        ></b-form-input>
        <!-- <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;"> 
          비밀번호가 일치하지 않습니다.
        </b-form-invalid-feedback> -->
      </b-form-group>
      <b-form-group
        label="닉네임"
        label-for="nickname"
        label-cols-sm="1"
      >
        <b-form-input 
          id="nickname" 
          v-model="user.nickname" 
          :state="nameState"
          aria-describedby="input-live-help input-live-feedback"
          placeholder="닉네임은 2자 이상이어야 합니다."
        ></b-form-input>
        <!-- <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;"> 
          닉네임은 두 글자 이상이어야 합니다.
        </b-form-invalid-feedback> -->
      </b-form-group>
      <b-button id="btn" type="button" class="btn float-right" @click="nicnameCheck">중복체크</b-button>
    </b-card>
    <div class="d-flex mr-3">
      <b-card class="col-6" bg-variant="light">
        <div class="image">
          <h5 class="float-left"><strong>이미지 업로드</strong></h5>
        </div>
        <b-form-file ref="image" method="post" enctype="multipart/form-data"> 
        </b-form-file>
        <b-button id="btn" class="mt-2 float-right" @click="imageUpload()">Add</b-button>
        <b-form-checkbox
          id="checkbox-1"
          name="checkbox-1"
          class="mt-2 float-left"
          @click.native="clearFiles"
        >
          업로드 취소
        </b-form-checkbox>
      </b-card>
      <b-card bg-variant="light" class="col-6 ml-3">
        <h5 class="float-left"><strong>관심 분야</strong></h5>
        <b-form-select name="mainCategory" id="mainCategory" v-model="user.mainCategory" class="mb-3">
          <option value="" selected disabled hidden>선택해주세요</option>
          <option v-for="interest in interestList" :key="interest.id" :value="interest.iname">{{ interest.iname }}</option>
        </b-form-select>
        <b-form-tags v-model="user.interests" no-outer-focus class="mb-2">
          <template v-slot="{ tags, inputAttrs, inputHandlers, addTag, removeTag }">
            <div class="d-flex justify-content-around">
              <input
                v-bind="inputAttrs"
                v-on="inputHandlers"
                placeholder="관심 분야를 추가로 입력해주세요."
                class="form-control col-sm-10 mt-1">
              <button id="btn" @click="addTag()" type="button" class="btn float-right mt-0">Add</button>
            </div>
            <ul
              id="my-custom-tags-list"
              class="list-unstyled d-inline-flex flex-wrap"
              aria-live="polite"
              aria-atomic="false"
              aria-relevant="additions removals"
            >
              <badge class="mr-1"
                v-for="tag in tags"
                :key="tag"
                :id="`my-custom-tags-tag_${tag.replace(/\s/g, '_')}_`"
                tag="li"
              >
                <strong>{{ tag }}</strong>
                <a
                  @click="removeTag(tag)"
                  type="button"
                  class="ml-3"
                  :aria-controls="`my-custom-tags-tag_${tag.replace(/\s/g, '_')}_`"
                >X</a>
              </badge>
            </ul>
          </template>
        </b-form-tags>
      </b-card>
    </div>
    <b-button id="btn" type="button" class="btn float-right" @click="userEdit">저장하기</b-button>
  </div>
</section>
</template>

<script>
import jwt_decode from 'jwt-decode'
import interest from "./assets/interests.json"
import axios from 'axios'
import {Badge} from '../../components'

export default {
  name: 'ProfileEdit',
  components: {
    [Badge.name]: Badge
  },
  data: () => {
    return {
      interestList : interest,
      userno: null,
      admin: 0,                 // 수정
      user:{
        email: null,
        password: '',
        nickname: '',
        mainCategory: '',
        interests: [],
      },
      interestTmp: [],
      passwordcheck: '',
      img: null,
      nicFlag: 2,
      originalNickname: null,
      checkedFlag: false,
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
    imageUpload: function () {
      console.log('이미지')
      var image = this.$refs['image'].files[0]
      const formData = new FormData()
      formData.append('image', image)
      axios({
        method: 'post',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/user/upload/${this.userno}`,
        headers: this.setToken(),
        data: formData
      })
        .then(res => {
          console.log(res)
          sessionStorage.setItem('jwt', res.data.accessToken)               // 수정
          window.location.reload(this.$route.params.user_no)
        })
        .catch(err => {
          console.log(err)
        })
    },
    clearFiles() {
      console.log(this.$refs['image'])
      this.$refs['image'].reset()
    },
    nicnameCheck: function () {
      if (this.user.nickname.length < 3) {
        alert("닉네임은 두글자 이상이어야 합니다.")
      } else {
        axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/user/nickname/${this.user.nickname}/`,
      })
        .then(res => {
          this.nicFlag = 1
          this.checkedFlag = true
          console.log(res)
          alert("사용할 수 있는 닉네임입니다.")
        })
        .catch(err => {
          this.nicFlag = 2
          this.checkedFlag = false
          console.log(err, this.user.email)
          alert("사용중인 닉네임입니다.")
        })
      }
    },
    userEdit: function() {
      var pattern1 = /[0-9]/;
      var pattern2 = /[a-zA-Z]/;
      var pattern3 = /[~!@#$%<>^&*]/;
      var checkFlag = true
      if (this.user.nickname == this.originalNickname) {
        this.nicFlag = 1 
        this.checkedFlag = true
      }
      if (this.nicFlag == 2 || this.checkedFlag == false) {
        alert("닉네임을 확인해주세요.")
        checkFlag = false
      }
      else if (this.user.password != null && this.user.password != "") {
          if (this.user.password != this.passwordcheck) {
            alert("비밀번호가 일치하지 않습니다.") 
            checkFlag = false
        } 
          else if (!pattern1.test(this.user.password)||!pattern2.test(this.user.password)||!pattern3.test(this.user.password)||this.user.password.length<8||this.user.password.length>50) {
            alert("비밀번호는 8글자 이상, 문자와 숫자, 특수문자를 포함해야 합니다.")
            checkFlag = false
          }
      }
      if (checkFlag) {
        axios({
          method: 'post',
          url: `https://i6a107.p.ssafy.io:8443/api/v1/user/${this.userno}/`,
          headers: this.setToken(),
          data: this.user
        })
          .then(res => {
            console.log(res)
            if (this.admin == 0) {
              sessionStorage.setItem('jwt', res.data.accessToken)               // 수정
              alert("회원 정보 수정이 완료되었습니다.")
              window.location.reload(this.$route.params.user_no)
            }
            else {
              alert("회원 정보 수정이 완료되었습니다.")
              window.location.reload(this.$route.params.user_no)
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    userDelete: function () {
      axios({
        method: 'delete',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/user/${this.userno}/`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res)
          alert("회원 탈퇴가 완료되었습니다.")
          this.$router.push({ name: 'Login'})
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserInformation: function () {
      const token = sessionStorage.getItem('jwt')              // 수정
      const decoded = jwt_decode(token)
      this.user.nickname = decoded.nickname
      this.originalNickname = decoded.nickname
      console.log(this.originalNickname)
      this.user.mainCategory = decoded.mainCategory
      this.userno = decoded.userno
      this.user.email = decoded.email
      this.mainCategory = decoded.mainCategory
      this.interestTmp = decoded.interests
      this.user.interests = this.interestTmp.split('#')
      this.img = decoded.image
      console.log(decoded)
    },
    getUserInformationByAdmin: function (user_no) {
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/admin/${user_no}`,
      })
        .then(res => {
          console.log(res)
          this.user.nickname = res.data.user.nickname
          this.user.mainCategory = res.data.user.mainCategory
          this.originalNickname = decoded.nickname
          this.userno = res.data.user.userno
          this.user.email = res.data.user.email
          this.mainCategory = res.data.user.mainCategory
          this.interestTmp = res.data.user.interests
          this.user.interests = this.interestTmp.split('#')
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  computed: {
    nameState() {                                // 수정
      if (this.user.nickname.length == 0) {
        return null
      } else {
        return this.user.nickname.length > 1 ? true : false    
      }
    },
    passwordState() {
      var pattern1 = /[0-9]/;
      var pattern2 = /[a-zA-Z]/;
      var pattern3 = /[~!@#$%<>^&*]/;
      if (this.user.password == 0) {
        return null
      }
      else if (!pattern1.test(this.user.password)||!pattern2.test(this.user.password)||!pattern3.test(this.user.password)||this.user.password.length<8||this.user.password.length>50) {
        return false
      } else {
        return true
      }
    },
    passwordcheckState() {
      if (this.passwordcheck.length == 0) {
        return null
      }
      else if (this.passwordcheck == this.user.password) {
        return true
      } else {
        return false
      }
    }
  },
  created: function () {
    if (sessionStorage.getItem('jwt')) {                       // 수정
      const token = sessionStorage.getItem('jwt')                      // 수정
      const decoded = jwt_decode(token)
      this.img = decoded.image   // 수정
      if (decoded.isAdmin == 1) {
        this.admin = 1
        this.getUserInformationByAdmin(this.$route.params.user_no)
      }
      else {
        this.getUserInformation()
      }
    } else {
      this.$router.push({name: 'Login'})
    }
  }
}
</script>

<style scoped>
  section {
    margin-bottom: 10rem;
  }
  .container {
    margin-top : 5rem;               /* 수정 */
    margin-bottom: 5rem;
  }
  h2 {
    float: left;
    margin-bottom: 1rem;
    font-family: 'Black Han Sans', sans-serif;
  }
  img {
    width: 100px;
    height: 100px;
    border-radius: 70%;
  }
  .image {
    display: flex;
    flex-direction: row;
  }
  a {
    color: black;
  }
  #btn {
    font-size: 11px;
    height: 35px;
    background-color: rgb(130, 163, 209);
  }
  #btn:hover {
    background-color: rgb(79, 138, 216);
  }
</style>
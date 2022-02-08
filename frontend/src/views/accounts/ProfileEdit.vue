/*
    작성자 : 한슬기
    생성일 : 2022.01.30
    마지막 업데이트 : 2022.01.30
    
    개인정보수정 페이지
 */
<template>
<section>
  <div class="container">
    <b-card bg-variant="light" class="card mb-4">
      <h5><strong>프로필</strong></h5>
      <img src='./assets/logo.png' alt="default-img">
      <img src="C:\Users\SSAFY\Desktop\lab\S06P12A107\backend\file_profiles\3bd5257db06dec7b9dcef53941b4046e.jpg" alt="">
      <p>{{ this.user.email }}</p>
      <a href="#" class="float-right" @click="userDelete">회원탈퇴</a>
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
        >
        </b-form-input>
        <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
          비밀번호는 문자, 숫자, 특수문자 포함 8자 이상이어야 합니다.
        </b-form-invalid-feedback>
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
        >
        </b-form-input>
        <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
          비밀번호가 일치하지 않습니다.
        </b-form-invalid-feedback>
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
        >
        </b-form-input>
        <b-form-invalid-feedback id="input-live-feedback" style="text-align: left;">
          닉네임은 두 글자 이상이어야 합니다.
        </b-form-invalid-feedback>
      </b-form-group>
    </b-card>
    <div class="d-flex mt-4">
      <b-card class="col-6" bg-variant="light">
        <div class="image">
          <h5 class="float-left"><strong>이미지 업로드</strong></h5>
        </div>
        <div class="d-flex justify-content-between mt-3">
          <b-form-file ref="image" method="post" enctype="multipart/form-data">
            <!-- <input ref="image" type="file" id="files" name="files" style="width: 120%;"> -->
          </b-form-file>
          <b-button @click="imageUpload()" style="background-color: skyblue; border: none;">Add</b-button>
        </div>
        <b-form-checkbox
          id="checkbox-1"
          name="checkbox-1"
          class="mt-2 float-left"
          @click.native="clearFiles"
        >
          업로드 취소
        </b-form-checkbox>
      </b-card>
      <b-card bg-variant="light" class="col-6">
        <h5 class="float-left"><strong>관심 분야</strong></h5>
        <b-form-select name="mainCategory" id="mainCategory" v-model="user.mainCategory" class="mb-3">
          <option value="" selected disabled hidden>선택해주세요</option>
          <option v-for="interest in interestList" :key="interest.id" :value="interest.iname">{{ interest.iname }}</option>
        </b-form-select>
        <b-form-tags v-model="user.interests" no-outer-focus class="mb-2">
          <template v-slot="{ tags, inputAttrs, inputHandlers, addTag, removeTag }">
            <b-input-group aria-controls="my-custom-tags-list">
              <input
                v-bind="inputAttrs"
                v-on="inputHandlers"
                placeholder="관심 분야를 추가로 입력해주세요."
                class="form-control">
              <b-input-group-append>
                <b-button @click="addTag()" style="background-color: skyblue; border: none;">Add</b-button>
              </b-input-group-append>
            </b-input-group>
            <ul
              id="my-custom-tags-list"
              class="list-unstyled d-inline-flex flex-wrap mb-3 mt-3"
              aria-live="polite"
              aria-atomic="false"
              aria-relevant="additions removals"
            >
              <b-card
                v-for="tag in tags"
                :key="tag"
                :id="`my-custom-tags-tag_${tag.replace(/\s/g, '_')}_`"
                tag="li"
                class="mt-1 mr-1"
                body-class="py-1 pr-2 text-nowrap"
              >
                <strong>{{ tag }}</strong>
                <b-button
                  @click="removeTag(tag)"
                  variant="link"
                  size="sm"
                  :aria-controls="`my-custom-tags-tag_${tag.replace(/\s/g, '_')}_`"
                >remove</b-button>
              </b-card>
            </ul>
          </template>
        </b-form-tags>
      </b-card>
    </div>
    <b-button class="btn1 mt-5 float-right" @click="userEdit">저장하기</b-button>
  </div>
</section>
</template>

<script>
import jwt_decode from 'jwt-decode'
import interest from "./assets/interests.json"
import axios from 'axios'


export default {
  name: 'ProfileEdit',
  data: () => {
    return {
      interestList : interest,
      userno: null,
      admin: null,
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
    imageUpload: function () {
      console.log('이미지')
      var image = this.$refs['image'].files[0]
      const formData = new FormData()
      formData.append('files', image)
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/user/upload/${this.userno}`,
        headers: this.setToken(),
        data: formData
      })
        .then(res => {
          console.log(res)
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
    userEdit: function() {
      console.log(this.user)
      axios({
        method: 'post',
        url: `http://localhost:8080/api/v1/user/${this.userno}/`,
        headers: this.setToken(),
        data: this.user
      })
        .then(res => {
          console.log(res)
          if (this.admin == 0) {
            localStorage.setItem('jwt', res.data.accessToken)
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
    },
    userDelete: function () {
      axios({
        method: 'delete',
        url: `http://localhost:8080/api/v1/user/${this.userno}/`,
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
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
      this.user.nickname = decoded.nickname
      this.user.mainCategory = decoded.mainCategory
      this.userno = decoded.userno
      this.user.email = decoded.email
      this.mainCategory = decoded.mainCategory
      this.interestTmp = decoded.interests
      this.user.interests = this.interestTmp.split('#')
      this.img = decoded.image
      console.log(this.img)
    },
    getUserInformationByAdmin: function (user_no) {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/admin/${user_no}`,
      })
        .then(res => {
          console.log(res)
          this.user.nickname = res.data.user.nickname
          this.user.mainCategory = res.data.user.mainCategory
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
    nameState() {
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
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')
      const decoded = jwt_decode(token)
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
 img {
   width: 100px;
   height: 100px;
   border-radius: 70%;
 }
 .btn1 {
   background-color: palevioletred;
   border: none;
 }
 input {
   border: none;
   border-bottom: solid 1px;
 }
 .image {
   display: flex;
   flex-direction: row;
 }
</style>
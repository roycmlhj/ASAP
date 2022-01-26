/*
    작성자 : 한슬기
    생성일 : 2022.01.25
    마지막 업데이트 : 2022.01.25
    
    개인정보수정 페이지
 */
<template>
  <div class="container">
    <b-card bg-variant="light" class="card mb-4">
      <h5><strong>프로필</strong></h5>
      <p><img src="https://cdn.imweb.me/thumbnail/20200606/09c71b2f94ea5.jpg" alt="default_image"></p>
      <p>{{ username }}</p>
      <b-button variant="link" class="float-right">회원탈퇴</b-button>
    </b-card>
    <b-card bg-variant="light">
      <b-form-group
        label="비밀번호"
        label-for="password"
        label-cols-sm="1"
        label-align-sm="right"
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
        <b-form-invalid-feedback id="input-live-feedback">
          비밀번호는 문자, 숫자, 특수문자 포함 8자 이상이어야 합니다.
        </b-form-invalid-feedback>
      </b-form-group>
      <b-form-group
        label="비밀번호"
        label-for="passwordcheck"
        label-cols-sm="1"
        label-align-sm="right"
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
        <b-form-invalid-feedback id="input-live-feedback">
          비밀번호가 일치하지 않습니다.
        </b-form-invalid-feedback>
      </b-form-group>
      <b-form-group
        label="닉네임"
        label-for="nickname"
        label-cols-sm="1"
        label-align-sm="right"
      >
        <b-form-input 
          id="nickname" 
          v-model="user.nickname" 
          :state="nameState"
          aria-describedby="input-live-help input-live-feedback"
          placeholder="닉네임은 2자 이상이어야 합니다."
        >
        </b-form-input>
        <b-form-invalid-feedback id="input-live-feedback">
          닉네임은 두 글자 이상이어야 합니다.
        </b-form-invalid-feedback>
      </b-form-group>
    </b-card>
    <div class="d-flex mt-4">
      <b-card bg-variant="light" class="col-6">
        <h5 class="float-left"><strong>이미지 업로드</strong></h5>
        <b-form-file v-model="user.file" ref="file-input" class="mb-2"></b-form-file>
        <p class="mt-2 float-left">선택된 이미지 : <b>{{ user.file ? user.file.name : '' }}</b></p>
        <b-form-checkbox
          id="checkbox-1"
          name="checkbox-1"
          class="mt-2"
          @click.native="clearFiles"
        >
          취소
        </b-form-checkbox>
      </b-card>
      <b-card bg-variant="light" class="col-6">
        <h5 class="float-left"><strong>관심 분야</strong></h5>
        <b-form-select name="interests" id="interests" v-model="user.main_category" class="mb-3">
          <option value="" selected disabled hidden>선택해주세요</option>
          <option v-for="interest in interestsList" :key="interest.id" :value="interest.iname">{{ interest.iname }}</option>
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
                <b-button @click="addTag()" variant="primary">Add</b-button>
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
    <b-button class="mt-5 float-right">저장하기</b-button>
  </div>
</template>

<script>
import jwt_decode from 'jwt-decode'
import interest from "./assets/interests.json"

export default {
  name: 'ProfileEdit',
  data: () => {
    return {
      interestsList : interest,
      username : null,
      user:{
        password: '',
        nickname: '',
        main_category: '',
        interests: [],
        file: null
      },
      passwordcheck: '',
    }
  },
  methods: {
    clearFiles() {
      this.$refs['file-input'].reset()
    }
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
      this.username = decoded.sub
      console.log(decoded)
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
</style>
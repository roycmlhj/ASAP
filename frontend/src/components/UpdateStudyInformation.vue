<template>
  <div class="container">
    <b-button @click="showModal">수정</b-button>
    <b-modal ref="my-modal" title="Update Study" hide-footer>
      <b-form>
        <b-form-group
          id="input-title-group"
          label="Name"
          lebel-for="input-title"
        >
        <div class="d-flex justify-content-between">
          <b-form-input
            id="input-title"
            v-model="information.studyname"
            type = "text"
            style="width: 75%"
            disabled
          ></b-form-input>
        </div>
        </b-form-group>
        <b-form-group
          label="Subject"
          label-for="interests"
        >
          <b-form-select name="interests" id="interests" v-model="information.category">
            <option value="" selected disabled hidden>주제를 선택해주세요</option>
            <option v-for="interest in interestsList" :key="interest.id" :value="interest.iname">{{ interest.iname }}</option>
          </b-form-select>
        </b-form-group>
        <b-form-tags
          input-id="interests" 
          v-model="information.interests"
          tag-variant="primary"
          tag-pills
          placeholder="세부 주제를 입력해주세요."
        ></b-form-tags>
        <b-form-group
          id="input-description-group"
          label="Description"
          lebel-for="input-description"
          class="mt-3"
        >
          <b-form-textarea
            id="input-description"
            v-model="information.description"
            type = "text"
            rows="6"
            max-rows="6"
            required
          ></b-form-textarea>
        </b-form-group>
        <b-form-group
          id="input-member-group"
          label="The number of people"
          label-for="input-member"
        >
          <study-member-count-bar
            v-on:memberEvent="updateMember"
          >
          </study-member-count-bar>
        </b-form-group>
        <b-button @click="updateStudyInformation" style="background-color: #A5A6F6; width: 100%">완료</b-button>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import interest from "@/views/accounts/assets/interests.json"
import StudyMemberCountBar from '@/components/StudyMemberCountBar.vue'
import axios from 'axios'


export default {
  name: 'UpdateStudyInformation',
  components: { 
    StudyMemberCountBar,
  },
  data: function () {
    return {
      interestsList : interest,
      information: {
        studyno: this.studyInformation.studyno,
        description: null,
        interests: null,
        category: null,
        memberno: null,
      }
    }
  },
  props: {
    studyInformation: {
      type: Object
    },
    interestList: {
      type: Array
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
    showModal() {
      this.information.studyname = this.studyInformation.studyname
      this.information.description = this.studyInformation.description
      this.information.memberno = this.studyInformation.memberno
      this.information.category = this.studyInformation.category
      this.information.interests = this.interestList
      this.$refs['my-modal'].show()
    },
    updateMember(memberNum) {
      this.memberno=memberNum
      //console.log(this.member)
    },
    updateStudyInformation: function () {
      axios({
        method: 'put',
        url: `http://localhost:8080/api/v1/study/modify`,
        data: this.information,
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
  }
}
</script>

<style>

</style>
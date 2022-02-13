<template>
  <div class="container mt-5">
    <div style="width: auto; text-align: center; background: #394e79">
      <study-room-list :studies="studies"></study-room-list>
    </div>
    <b-row class="mt-5 mb-3">
      <b-col style="background-color: #e6c17b">
        <b-row
          style="
            font-family: 'Gaegu', cursive;
            font-family: 'Gamja Flower', cursive;
            font-family: 'Hi Melody', cursive;
          "
        >
          <b-col cols="6">
            <main-homework
              :onHomeworkList="onHomeworkList"
              :doneHomeworkList="doneHomeworkList"
              class="sticker1"
              style="float: left"
            ></main-homework>
          </b-col>
          <b-col cols="6">
            <main-undo-homework
              :onHomeworkList="onHomeworkList"
              class="sticker1"
              style="float: left"
            ></main-undo-homework>
          </b-col>
        </b-row>
        <b-row
          style="
            font-family: 'Gaegu', cursive;
            font-family: 'Gamja Flower', cursive;
            font-family: 'Hi Melody', cursive;
          "
        >
          <b-col cols="6">
            <main-today-study
              :studyList="studyList"
              class="sticker1"
              style="float: right"
            ></main-today-study>
          </b-col>
          <b-col cols="6">
            <div class="sticker1" style="float: right">
              <br />
              <br />
              <br />
              <br />
              <a
                href="#"
                @click="modalTurn"
                style="color: black; font-size: x-large"
                >+ 스터디방 만들기</a
              >
              <b-modal v-model="modalShow" title="Create Study" hide-footer>
                <b-form>
                  <b-form-group
                    id="input-title-group"
                    label="Name"
                    lebel-for="input-title"
                  >
                    <div class="d-flex justify-content-between">
                      <b-form-input
                        id="input-title"
                        v-model="title"
                        type="text"
                        style="width: 75%"
                        class="mt-2"
                        required
                      ></b-form-input>
                      <b-button
                        @click="nameCheck"
                        style="
                          background-color: rgb(130, 163, 209);
                          font-size: 13px;
                        "
                        >중복체크</b-button
                      >
                    </div>
                  </b-form-group>
                  <b-form-group label="Subject" label-for="interests">
                    <b-form-select
                      name="interests"
                      id="interests"
                      v-model="mainCategory"
                    >
                      <option value="" selected disabled hidden>
                        주제를 선택해주세요
                      </option>
                      <option
                        v-for="interest in interestsList"
                        :key="interest.id"
                        :value="interest.iname"
                      >
                        {{ interest.iname }}
                      </option>
                    </b-form-select>
                  </b-form-group>
                  <b-form-tags
                    input-id="interests"
                    v-model="interests"
                    tag-variant="primary"
                    tag-pills
                    placeholder="세부 주제를 입력해주세요."
                  ></b-form-tags>
                  <b-form-group
                    id="input-description-group"
                    label="Description"
                    lebel-for="input-description"
                    class="mt-4"
                  >
                    <b-form-textarea
                      id="input-description"
                      v-model="description"
                      type="text"
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
                    <study-member-count-bar v-on:memberEvent="updateMember">
                    </study-member-count-bar>
                  </b-form-group>
                  <b-button
                    @click="createStudyRoom"
                    style="background-color: #a5a6f6; width: 100%"
                    >완료</b-button
                  >
                </b-form>
              </b-modal>
              <br />
              <br />
              <router-link :to="{ name: 'CreateStudyBoard' }">
                <a href="#" style="color: black; font-size: x-large"
                  >+ 스터디원 모집하기</a
                >
              </router-link>
            </div>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import StudyMemberCountBar from "@/components/StudyMemberCountBar.vue";
import interest from "@/views/accounts/assets/interests.json";
import StudyRoomList from "@/components/StudyRoomList.vue";
import MainHomework from "@/components/MainHomework.vue";
import MainUndoHomework from "@/components/MainUndoHomework.vue";
import MainTodayStudy from "@/components/MainTodayStudy.vue";
import { Button } from "@/components";

export default {
  name: "Main",
  components: {
    StudyMemberCountBar,
    StudyRoomList,
    MainHomework,
    MainUndoHomework,
    MainTodayStudy,
    [Button.name]: Button,
  },
  data() {
    return {
      interestsList: interest,
      modalShow: false,
      title: "",
      description: "",
      memberno: "",
      mainCategory: "",
      interests: [],
      maker: "",
      userno: null,
      studies: null,
      flag: 2,
      doneHomeworkList: [],
      onHomeworkList: [],
      studyList: [],
    };
  },
  methods: {
    modalTurn: function () {
      if (!this.modalShow) {
        this.title = "";
        this.description = "";
        this.memberno = "";
        this.mainCategory = "";
        this.interests = [];
        this.maker = "";
      }
      this.modalShow = !this.modalShow;
    },
    setToken: function () {
      const token = localStorage.getItem("jwt");
      const config = {
        Authorization: `JWT ${token}`,
      };
      return config;
    },
    updateMember(memberNum) {
      this.memberno = memberNum;
      //console.log(this.member)
    },
    getHomeworkList: function () {
      axios({
        method: "get",
        url: `http://localhost:8080/api/v1/user/detail/${this.userno}`,
      })
        .then((res) => {
          this.onHomeworkList = res.data.onHomeworkList;
          this.doneHomeworkList = res.data.doneHomeworkList;
          this.studyList = res.data.studyList;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    nameCheck: function () {
      if (this.title == "") {
        alert("스터디 이름을 입력해주세요.");
      } else {
        axios({
          method: "get",
          url: `http://localhost:8080/api/v1/study/name_check/${this.title}`,
          data: this.title,
        })
          .then((res) => {
            console.log(res);
            this.flag = 1;
            alert("사용할 수 있는 스터디 이름입니다.");
          })
          .catch((err) => {
            console.log(err);
            this.flag = 2;
            alert("이미 사용중인 스터디 이름입니다.");
          });
      }
    },
    getStudies: function () {
      axios({
        method: "get",
        url: `http://localhost:8080/api/v1/study/${this.userno}/`,
        headers: this.setToken(),
      })
        .then((res) => {
          console.log(res.data);
          this.studies = res.data.studyInfoList;
        })
        .catch((err) => {
          console.log(this.title, err);
        });
    },
    createStudyRoom: function () {
      const token = localStorage.getItem("jwt");
      const decoded = jwt_decode(token);

      const StudyRoomItem = {
        studyname: this.title,
        description: this.description,
        category: this.mainCategory,
        memberno: this.memberno,
        maker: decoded.email,
        userno: decoded.userno,
        interests: this.interests,
      };
      if (
        !StudyRoomItem.studyname ||
        !StudyRoomItem.description ||
        !StudyRoomItem.category ||
        !StudyRoomItem.memberno ||
        !StudyRoomItem.interests
      ) {
        console.log("pass");
        alert("모든 정보를 입력해주세요.");
      } else {
        axios({
          method: "post",
          url: `http://localhost:8080/api/v1/study/create`,
          data: StudyRoomItem,
        })
          .then((res) => {
            console.log(res);
            this.modalShow = false;
            this.getStudies();
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
  created: function () {
    if (localStorage.getItem("jwt")) {
      const token = localStorage.getItem("jwt");
      const decoded = jwt_decode(token);
      console.log(decoded);
      this.userno = decoded.userno;
      this.getHomeworkList();
      this.getStudies();
    } else {
      this.$router.push({ name: "Login" });
    }
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Gaegu&family=Gamja+Flower&family=Hi+Melody&display=swap");
form {
  padding: 0px 20px;
}
/* button {
    width: 100%;
  } */
.sticker1 {
  background-image: url("../../views/accounts/assets/sticker1번.png");
  width: 270px;
  height: 317px;
  background-size: 270px;
}
</style>
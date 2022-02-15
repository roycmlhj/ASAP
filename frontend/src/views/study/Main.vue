<template>   <!--전체적으로 수정-->
<section>
  <div class="page-header2">
    <div class="d-flex justify-content-center">
      <font-awesome-icon icon="fa-solid fa-desktop" class="icon fa-5x" style="color: rgb(231, 223, 223);"/>
      <div>
        <h2 id="massage">{{ nickname }}님의 스터디 공간</h2>
      </div>
    </div>
  </div>
  <div class="container mt-5">
    <div style="width: auto; text-align: center;">
      <study-room-list :studies="studies"></study-room-list>
    </div>
    <hr style="margin-top: 3rem;">
    <div class="d-flex" style="margin-top: 5rem;">
      <card class="col-9 mr-3">
      <tabs type="primary" vertical class="row" > 
        <tab-pane label="이번 주 과제 목록">
          <main-homework
            :onHomeworkList="onHomeworkList"
            :doneHomeworkList="doneHomeworkList"
            
            style="float: left"
          ></main-homework>
        </tab-pane>
        <tab-pane label="미완료 과제 목록">
          <main-undo-homework
            :onHomeworkList="onHomeworkList"
            
            style="float: left"
          ></main-undo-homework>
        </tab-pane>
        <tab-pane label="오늘 진행되는 스터디">
          <main-today-study
            :studyList="studyList"
          ></main-today-study>
        </tab-pane> 
      </tabs>
      </card>
      <card class="col-3">
      <br>
      <br>
      
      <div class="row">
        <a
          class="align-middle" 
          type="button"
        
          @click="modalTurn"
          style="color: black; font-size: x-large; font-family: 'Do Hyeon', sans-serif;"
          >+ 스터디방 만들기</a
        >
        
        <router-link class="align-middle"  :to="{ name: 'CreateStudyBoard' }">
          <a type="button" style="color: black; font-size: x-large; font-family: 'Do Hyeon', sans-serif;" 
            >+ 스터디원 모집하기</a
          >
        </router-link>
      </div>
      </card>
    </div>
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
              required
            ></b-form-input>
            <b-button
              @click="nameCheck"
              class="mt-0"
              style="background-color: rgb(130, 163, 209); font-size: 13px;"
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
          class="mt-3"
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
  </div>
</section>
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
import {Tabs, TabPane} from '@/components'
import {Card} from '@/components'

export default {
  name: "Main",
  components: {
    StudyMemberCountBar,
    StudyRoomList,
    MainHomework,
    MainUndoHomework,
    MainTodayStudy,
    [Button.name]: Button,
    Tabs,
    TabPane,
    Card
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
      nickname: null,
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
      const token = sessionStorage.getItem("jwt");            // 수정
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
      const token = sessionStorage.getItem("jwt");             // 수정
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
    if (sessionStorage.getItem("jwt")) {                 // 수정
      const token = sessionStorage.getItem("jwt");           // 수정
      const decoded = jwt_decode(token);
      console.log(decoded);
      this.userno = decoded.userno;
      this.nickname = decoded.nickname
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
@import url('https://fonts.googleapis.com/css2?family=Lato&family=Nanum+Gothic&family=Ubuntu:wght@500&display=swap');

form {
  padding: 0px 20px;
} 
.page-header2 {
  width: 100%;
  height: 150px;
  background-color: #394E79;
}
section {
  margin-top: 4rem;                /* 수정 */
  margin-bottom: 10rem;
  min-height: 100vh;
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
#massage{
  margin-top: 3.5rem;
}
.icon {
  margin-top: 2.5rem;
  margin-right: 2rem;
}
h2 {
  color: rgb(231, 223, 223);
  font-family: 'Black Han Sans', sans-serif;
  margin-bottom: 0px;
}
#sticker {
  margin-bottom: 15rem;
}
</style>
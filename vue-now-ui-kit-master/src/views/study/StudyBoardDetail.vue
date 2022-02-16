<template>    <!--전체적으로 수정-->
<section>
  <div class="page-header2">
    <div class="d-flex justify-content-center">
      <font-awesome-icon icon="fa-solid fa-child" class="icon fa-5x" style="color: rgb(231, 223, 223);" />
      <div>
        <h5 id="massage">해당 스터디에 가입하고 싶다면 신청하기 버튼을 눌러주세요!</h5>
        <h5>스터디에 대한 설명과 참여한 사람들의 정보를 확인할 수 있습니다.</h5>
      </div>
    </div>
  </div>
  <div class="container">
    <div>
      <div class="row">
        <div class="d-flex col-12 header" name="detail-header">
          <div class="col-8 mainheader">
            <div class="category">
              <span class="badge badge-info">category</span>&nbsp;
              <span>{{ studytopic }}</span>
            </div>
            <div>
              <span class="float-center title">{{ studyname }}</span>
            </div>
          </div>
          <div class="col-4 subheader">
            <div>
              <div class="tags" v-for="tag in studytag" v-bind:key="tag">
                <span class="badge smalltag">{{ tag }}</span
                >&nbsp;
              </div>
            </div>
            <div>
              <div class="boardinfo">
                {{ nickname }} &nbsp;|&nbsp; {{ timestamp }} &nbsp; | &nbsp;
                <i class="fas fa-eye" /> {{ hit }}
              </div>
            </div>
          </div>
        </div>
        <div class="d-flex col-12 content" name="detail-body">
          <div class="col-8 justify-content-start" name="study data">
            <div name="description" class="description">
              <p align="left">
                {{ description }}
              </p>
            </div>
            <div align="left" class="contact" name="link">
              <font-awesome-icon icon="fa-solid fa-link" /> &nbsp;
              <span>{{ link }}</span>
            </div>
            <div align="left" class="contact" name="call">
              <font-awesome-icon icon="fa-solid fa-phone" class="fas" /> &nbsp;
              {{ call }}
            </div>
          </div>

          <div class="col-4 side">
            <div class="col-12 members" name="studyMembers">
              <div v-if="flag === 2">
                <div
                  class="d-flex justify-content-center"
                  v-for="(member, index) in displaymembers"
                  :key="index.id"
                >
                  <p v-if="member.image"><img :src="member.image" /></p>
                  <p v-else>
                    <img src="../../views/accounts/assets/default.png" />
                  </p>
                  <p>
    <!--수정-->     <a id="show-btn"
                      type="button"
                      class="mt-1"
                      @click="showModal(member)"
                    >
                      {{ member.nickname }}
                    </a>
                  </p>
                </div>
                <b-modal
                  ref="my-modal"
                  :member="member"
                  hide-header
                  hide-footer
                >
                  <div class="d-block text-center">
                    <div v-if="member">
                      <user-info-modal
                        :member="member"
                        :studylist="studylist"
                      ></user-info-modal>
                    </div>
                  </div>
                </b-modal>
              </div>
              <div v-if="flag === 0">
                <div
                  class="d-flex justify-content-center"
                  v-for="(member, index) in studyMembers"
                  v-bind:key="index.id"
                >
                  <p v-if="member.image"><img :src="member.image" /></p>
                  <p v-else>
                    <img src="../../views/accounts/assets/default.png" />
                  </p>
                  <p class="mt-2">
   <!--수정-->      <a
                      id="show-btn"
                      type="button"
                      @click="showModal(member)"
                    >
                      {{ member.nickname }}</a
                    >
                  </p>
                </div>
                <b-form-checkbox-group
                  id="checkbox-group"
                  v-model="selected"
                  name="members"
                >
                  <b-form-checkbox
                    class="d-flex justify-content-center"
                    v-for="member in displaymembers"
                    v-bind:key="member.id"
                    :value="member.userno"
                  >
                    <p v-if="member.image">
                      <img :src="member.image" />
    <!--수정-->        <a
                        id="show-btn"
                        type="button"
                        style="margin-left: -10px;"
                        @click="showModal(member)"
                        >{{ member.nickname }}</a
                      >
                    </p>
                    <p v-else>
                      <img src="../../views/accounts/assets/default.png" />
  <!--수정-->         <a
                        id="show-btn"
                        type="button"
                        @click="showModal(member)"
                        >{{ member.nickname }}</a
                      >
                    </p>
                  </b-form-checkbox>
                </b-form-checkbox-group>
                <b-modal
                  ref="my-modal"
                  :member="member"
                  hide-header
                  hide-footer
                >
                  <div class="d-block text-center">
                    <div v-if="member">
                      <user-info-modal
                        :member="member"
                        :studylist="studylist"
                      ></user-info-modal>
                    </div>
                  </div>
                </b-modal>
              </div>
              <div v-if="flag === 1">
                <div
                  class="d-flex justify-content-center"
                  v-for="(member, index) in displaymembers"
                  v-bind:key="index.id"
                >
                  <p v-if="member.image"><img :src="member.image" /></p>
                  <p v-else>
                    <img src="../../views/accounts/assets/default.png" />
                  </p>
                  <p class="mt-2">
   <!--수정-->      <a
                      id="show-btn"
                      type="button"
                      @click="showModal(member)"
                    >
                      {{ member.nickname }}</a
                    >
                  </p>
                </div>
                <b-modal
                  ref="my-modal"
                  :member="member"
                  hide-header
                  hide-footer
                >
                  <div class="d-block text-center">
                    <div v-if="member">
                      <user-info-modal
                        :member="member"
                        :studylist="studylist"
                      ></user-info-modal>
                    </div>
                  </div>
                </b-modal>
              </div>
            </div>
            <br />
            <div name="buttons" class="buttons">
              <div v-if="flag === 2">
                <button type="button" class="btn" @click="studyApply"><strong>
                  <font-awesome-icon icon="fa-solid fa-hand-pointer" />
                  신청하기</strong>
                </button>
              </div>
              <div v-if="flag === 1" disabled="”disabled”">
                <button type="button" id="btn" class="btn">
                  이미 신청한 스터디입니다.
                </button>
              </div>
              <div class="button1" v-if="flag === 0">
                
                <button type="button" class="btn col-5" @click="studyAccept"><font-awesome-icon icon="fa-solid fa-check" /> 가입 수락</button>
                <button type="button" class="btn col-5" @click="studyRefuse">X 가입 거절</button>
                <router-link :to="`/study/board/update/${boardno}`">
                  <button type="button" class="btn col-5"><font-awesome-icon icon="fa-solid fa-pen" /> 수정하기</button>
                </router-link>
                <router-link :to="{}">
                <button type="button" class="btn col-5" @click="deleteBoard(boardno)"><font-awesome-icon icon="fa-solid fa-trash" /> 삭제하기</button>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import UserInfoModal from "@/components/UserInfoModal.vue";

export default {
  name: "StudyBoardDetail",
  components: {
    UserInfoModal,
  },
  data() {
    return {
      boardno: -1,
      studyMembers: [],
      studyname: "",
      studytopic: "",
      studytag: [],
      description: "",
      link: "",
      call: "",
      members: [],
      isRecruit: -1,
      maxmember: 0,
      styduno: -1,
      displaymembers: [],
      flag: 2,
      selected: [],
      userno: -1,
      member: null,
      studylist: null,
      membercnt: 0,
      nickname: "",
      hit: 0,
      timestamp: "",
    };
  },
  created() {
    const token = sessionStorage.getItem("jwt");             // 수정
    const decoded = jwt_decode(token);
    const userno = decoded.userno;
    this.userno = userno;
    this.boardno = this.$route.params.boardno;
    axios({
      method: "get",
      url: `http://localhost:8080/api/v1/board/${this.boardno}`,
    })
      .then((res) => {
        console.log(res.data);

        const board = res.data.board;
        this.boardno = board.boardno;
        this.studyname = board.boardname;
        this.members = res.data.list;
        this.description = board.boarddescription;
        this.link = board.link;
        this.call = board.contactlink;
        var tag = board.interests;
        this.studytag = tag.split("#");
        this.studytag.shift(0);
        this.studytopic = board.category;
        this.studyno = board.studyno;
        this.maxmember = board.maxmember;
        this.nickname = board.nickname;
        this.hit = board.hit;
        this.timestamp = board.timestamp.substring(0, 10);
        console.log(this.maxmember, 100);
        //유저가 스터디 소속인지, 스터디장인지, 외부인인지 여부 판별
        //0이면 스터디장, 1이면 신청자 또는 회원 2이면 외부인
        for (i = 0; i < this.members.length; i++) {
          if (this.members[i].position != 2) {
            this.studyMembers.push(this.members[i]);
          }
        }
        for (var i = 0; i < this.members.length; i++) {
          if (this.members[i].userno == userno) {
            if (this.members[i].position == 0) {
              this.flag = 0;
            } else {
              this.flag = 1;
            }
          }
        }
        for (i = 0; i < this.members.length; i++) {
          if (this.members[i].position == 0 || this.members[i].position == 1) {
            this.membercnt += 1;
          }
        }
        console.log(this.membercnt);
        if (this.flag == 0) {
          for (i = 0; i < this.members.length; i++) {
            if (this.members[i].position == 2) {
              this.displaymembers.push(this.members[i]);
            }
          }
        } else {
          for (i = 0; i < this.members.length; i++) {
            if (this.members[i].position != 2) {
              this.displaymembers.push(this.members[i]);
            }
          }
        }
        console.log(this.displaymembers);
      })
      .catch((err) => {
        console.log(this.title, err);
      });
  },

  methods: {
    setToken: function () {
      const token = sessionStorage.getItem("jwt");            // 수정
      const config = {
        Authorization: `JWT ${token}`,
      };
      return config;
    },
    showModal: function (user) {
      this.$refs["my-modal"].show();
      this.getUserInformation(user);
    },
    getUserInformation: function (user) {
      axios({
        method: "get",
        url: `http://localhost:8080/api/v1/admin/${user.userno}/`,
        headers: this.setToken(),
      })
        .then((res) => {
          console.log(res);
          this.member = res.data.user;
          this.studylist = res.data.studyList;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    studyApply() {
      const data = {
        studyno: this.studyno,
        userno: this.userno,
      };
      // if () {

      // } else {

      // }
      axios({
        method: "post",
        url: "http://localhost:8080/api/v1/study/apply",
        headers: this.setToken(),
        data: data,
      })
        .then((res) => {
          console.log(res);
          alert("스터디를 신청하셨습니다.");
          window.location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    studyAccept() {
      console.log(this.selected, "here");
      if (this.selected.length + this.membercnt > this.maxmember) {
        alert("멤버제한을 초과했습니다.");
        return;
      }
      for (var i = 0; i < this.selected.length; i++) {
        var data = {
          flag: 1,
          studyno: this.studyno,
          userno: this.selected[i],
        };
        axios({
          method: "put",
          url: "http://localhost:8080/api/v1/study/accept",
          headers: this.setToken(),
          data: data,
        })
          .then((res) => {
            console.log(res);
            window.location.reload();
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    studyRefuse() {
      console.log(this.selected);
      for (var i = 0; i < this.selected.length; i++) {
        var data = {
          flag: 0,
          studyno: this.studyno,
          userno: this.selected[i],
        };
        axios({
          method: "put",
          url: "http://localhost:8080/api/v1/study/accept",
          headers: this.setToken(),
          data: data,
        })
          .then((res) => {
            console.log(res);
            alert("가입을 거절했습니다.");
            window.location.reload();
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    deleteBoard: function (boardno) {
      axios({
        method: "delete",
        url: `http://localhost:8080/api/v1/board/${boardno}`,
        headers: this.setToken(),
      })
        .then((res) => {
          console.log(res);
          this.$router.push({ name: "StudyBoard" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style scoped>
section {
  margin-top: 4rem;               /* 수정 */
}
.page-header2 {
  width: 100%;
  height: 150px;
  background-color: #394E79;
}
h5 {
  color: rgb(231, 223, 223);
  font-family: 'Ubuntu', sans-serif;
  margin-bottom: 0px;
}
#massage{
  margin-top: 3rem;
}
.icon {
  margin-top: 2rem;
  margin-right: 2rem;
}
button {
  font-size: 15px;
  background-color: rgb(130, 163, 209);
}
button:hover {
  background-color: rgb(79, 138, 216);
}
img {
  width: 40px;
  height: 40px;
  border-radius: 70%;
  margin-right: 1rem;
}
a {
  color: black;
}
.container {
  margin-top: 80px;
  margin-bottom: 80px;
  margin-bottom: 10rem;
}
.header {
  border-bottom: 1px solid rgb(230, 230, 230);
  padding-bottom: 10px;
  position: relative;
}
.mainheader {
  padding-left: 20px;
  text-align: left;
}
.subheader {
  text-align: right;
  padding-right: 20px;
  padding-top: 40px;
}
.title {
  font-size: 40px;
}
.tags {
  display: inline;
}
.content {
  margin-top: 20px;
}
.smalltag {
  font-size: 13px;
  background-color: rgb(209, 209, 209);
}
.boardinfo {
  bottom: 0px;
}
.description {
  height: 400px;
  overflow: auto;
  border: 1px solid rgb(230, 230, 230);
  border-radius: 5px;
  padding: 20px;
}
.members {
  height: 400px;
  border: 1px solid rgb(230, 230, 230);
  padding-top: 20px;
}
.contact {
  background-color: rgb(245, 245, 245);
  border: 1px solid rgb(219, 219, 219);
  margin-block: 10px;
  padding: 15px;
  vertical-align: baseline;
  height: 50px;
}
.badge {
  border-radius: 5px;
}
.buttons {
  align-content: center;
}
a {
  font-size: 20px;
}
#btn {
  background-color: rgb(193, 201, 207);
}
</style>
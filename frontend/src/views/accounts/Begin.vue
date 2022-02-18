<template>
  <div>
    <b-row class="landing_page" style="background-color: #5e83ba">
      <div class="landing_content col-12 justify-content-center">
        <p id="asap" class="navbar-brand" style="color: #ffffff">ASAP</p>
      </div>
    </b-row>
    <b-row class="landing_page">
      <div class="landing_content col-12">
        <div class="col-6 images">
          <!--<img src="./assets/StudyRoomBegin.PNG" />-->
          <img src="./assets/studyroombegin.png" />
        </div>
        <div class="col-6 ments">
          <div class="div">
            <h1 id="description" class="mb-3">화상통화부터 일정관리까지</h1>
            <h1 id="description" class="mb-3">
              스터디에 필요한 모든 기능을 한곳에서!
            </h1>
            <br />
            <h4 id="description2">
              배너를 통해 공지확인, 과제 제출도 간단하게!
            </h4>
            <h4 id="description2">
              캘린더를 활용해서 간단하게 스터디 일정 관리까지!
            </h4>
          </div>
        </div>
      </div>
    </b-row>

    <b-row class="landing_page2">
      <div class="landing_content col-12">
        <div class="col-6 ments">
          <div class="div">
            <h1 id="description" class="mb-3">스터디 방, 과제, 일정</h1>
            <h1 id="description" class="mb-3">확인을 한번에!</h1>
            <br />
          </div>
          <div class="row justify-content-center">
            <div class="col-4" style="flex: 1; text-align: center">
              <div>
                <h3 id="description3">스터디 방 입장</h3>
                <h5 id="description2" style="font-size: 15px">
                  스터디방 목록을<br />확인하고 입장해보세요.
                </h5>
              </div>
              <div class="mt-5">
                <h3 id="description3">스터디 방 생성</h3>
                <h5 id="description2" style="font-size: 15px">
                  새로운 스터디<br />방을 생성하세요.
                </h5>
              </div>
            </div>
            <div class="col-4" style="flex: 1; text-align: center">
              <div>
                <h3 id="description3">완료·미완료 과제 확인</h3>
                <h5 id="description2" style="font-size: 15px">
                  이번주 과제와 미완료한 과제를<br />한눈에 볼 수 있어요.
                </h5>
              </div>
              <div class="mt-5">
                <h3 id="description3">스터디원 모집</h3>
                <h5 id="description2" style="font-size: 15px">
                  스터디원을 모집하세요.
                </h5>
              </div>
            </div>
          </div>
        </div>
        <div class="col-6 images">
          <div>
            <img src="./assets/mainbegin.png" />
          </div>
        </div>
      </div>
    </b-row>

    <b-row class="landing_page">
      <div class="landing_content col-12">
        <div class="col-6 images">
          <img src="./assets/studyboardbegin.png" />
        </div>
        <div class="col-6 ments">
          <div class="div">
            <h1 id="description" class="mb-3">나에게 맞는</h1>
            <h1 id="description" class="mb-3">스터디를 검색</h1>
            <h4 id="description2">
              스터디 공고를 통해 나에게 필요한 스터디를 찾아보세요.
            </h4>
            <h4 id="description2">
              스터디 멤버 정보를 확인하여 스터디원들의 성향을 확인할 수
              있습니다.
            </h4>
          </div>
        </div>
      </div>
    </b-row>

    <b-row class="landing_page2">
      <div class="landing_content col-12">
        <div class="col-6 ments">
          <div class="div">
            <h1 id="description" class="mb-3">한 눈에 확인하는</h1>
            <h1 id="description" class="mb-3">나의 스터디 정보</h1>
            <h3 id="description2">스터디, 과제 목록을 효율적으로 관리해요.</h3>
            <div class="div d-flex justify-content-center">
              <div>
                <font-awesome-icon icon="fa-solid fa-scroll" class="fa-4x" />
                <h3 id="description2" class="mt-3 mb-1">과제 목록</h3>
                <p class="mb-0">과제 진행 여부를</p>
                <p>확인할 수 있어요.</p>
              </div>
              <div class="mx-5">
                <font-awesome-icon icon="fa-solid fa-medal" class="fa-4x" />
                <h3 id="description2" class="mt-3 mb-1">스터디 포인트</h3>
                <p class="mb-0">포인트를 획득하고,</p>
                <p>레벨업 해보세요.</p>
              </div>
              <div>
                <font-awesome-icon icon="fa-solid fa-chart-pie" class="fa-4x" />
                <h3 id="description2" class="mt-3 mb-1">스터디 분석</h3>
                <p class="mb-0">어떤 스터디를,</p>
                <p class="mb-0">얼마나 진행했는지</p>
                <p>확인할 수 있어요.</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-6 images">
          <img class="" src="./assets/mypage.png" />
        </div>
      </div>
    </b-row>
  </div>
</template>


<script>
export default {
  name: "Begin",
  created: function () {
    if (sessionStorage.getItem("jwt")) {                  // 수정
      this.$router.push({ name: "Main" });
    }
  },
};
</script>


<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic+Coding:wght@400;700&display=swap");
#asap {
  font-size: 180px;
  text-align: center;
}
#description {
  font-family: "Black Han Sans", sans-serif;
}
#description2 {
  font-family: "Nanum Gothic Coding", monospace;
  font-weight: bold;
  font-size: 20px;
}
#description3 {
  font-family: "Black Han Sans", sans-serif;
}
.landing_page {
  height: 900px;
  padding: 30px;
}
.landing_page2 {
  height: 900px;
  padding: 30px;
  background-color: rgb(255, 251, 246);
}
.landing_content {
  width: 1592px;
  display: flex;
  padding: 30px;
  margin-top: 48px;
  margin-left: 45px;
  margin-right: 45px;
}
.div {
  margin-top: 8rem;
}
.page {
  height: 500px;
}
</style>
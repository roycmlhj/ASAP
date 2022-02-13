<template>
  <div>
    <b-row class="landing_page">
      <div class="landing_content">test1</div>
    </b-row>
    <b-row class="landing_page">
      <div class="d-flex landing_content">
        <div class="col-6" id="imageUpload">
          <img src="./assets/studyRoom.png">
        </div>
        <div class="col-6" id="mentUpload">
          <h1 id="description" style="margin-bottom:-3px;">화상통화부터 일정관리까지</h1>
          <h1 id="description">스터디에 필요한 모든 기능을 한곳에서!</h1>
          <br>
          <h4 id="description2">배너를 통해 공지확인, 과제 제출도 간단하게!</h4>
          <br>
          <h4 id="description2">캘린더를 활용해서 간단하게 스터디 일정 관리까지!</h4>
        </div>
      </div>
    </b-row>
    <b-row class="landing_page">
      <div class="landing_content" style="width: 1592px; display: flex">
        <div style="flex: 1; margin-top: 200px">
          <div class="mr-5">
            <p style="font-size: 40px; text-align: right; font-weight: bold">
              스터디 방, 과제, 일정<br />확인을 한번에!
            </p>
          </div>
          <div
            class="mr-5 mt-5 float-right"
            style="display: flex; width: 550px"
          >
            <div style="flex: 1; text-align: right">
              <div>
                <p style="font-size: 20px; font-weight: bold">스터디 방 입장</p>
                <p style="font-size: 17px">
                  스터디방 목록을<br />확인하고 입장해보세요.
                </p>
              </div>
              <div class="mt-5">
                <p style="font-size: 20px; font-weight: bold">스터디 방 생성</p>
                <p style="font-size: 17px">
                  새로운 스터디<br />방을 생성하세요.
                </p>
              </div>
            </div>
            <div style="flex: 1; text-align: right">
              <div>
                <p style="font-size: 20px; font-weight: bold">
                  완료·미완료 과제 확인
                </p>
                <p style="font-size: 17px">
                  이번주 과제와 미완료한 과제를<br />한눈에 볼 수 있어요.
                </p>
              </div>
              <div class="mt-5">
                <p style="font-size: 20px; font-weight: bold">스터디원 모집</p>
                <p style="font-size: 17px">스터디원을 모집하세요.</p>
              </div>
            </div>
          </div>
        </div>
        <div style="flex: 1; margin-top: 500px; margin-left: 50px">
          <div style="position: relative">
            <img src="./assets/main그림3.jpg" />
            <div style="position: absolute; top: -400px; left: 0px">
              <img src="./assets/main그림1.jpg" />
            </div>
            <div style="position: absolute; top: -250px; left: 400px">
              <img src="./assets/main그림2.jpg" />
            </div>
          </div>
        </div>
      </div>
    </b-row>
    <div class="landing_page">
      <div class="row landing_content">
        <div class="col-md-6 images">
          <img src="./assets/studyboard1.png" />
          <img src="./assets/studyboard2.png" />
        </div>
        <div class="col col-md-6 texts">
          <h3>나와 맞는 스터디를 검색</h3>
          <div>
            카테고리, 관심분야, 스터디 공고를 통해 나에게 필요한 스터디를
            찾아보세요.
          </div>
          <div>
            스터디 멤버 정보를 확인하여 스터디원들의 성향을 확인할 수 있습니다.
          </div>
        </div>
      </div>
    </div>
    <b-row class="page">
      <div class="landing_content d-flex col-12">
        <div class="col-6">
          <img class="" src="./assets/mypage.png">
        </div>
        <div class="mypage col-6">
          <div class="div">
            <h2 class="mb-1"><strong>한 눈에 확인하는</strong></h2>
            <h2 class="mb-3"><strong>나의 스터디 정보</strong></h2>
            <p>스터디, 과제 목록을 효율적으로 관리해요.</p>
            <div class="div d-flex justify-content-around">
              <div>
                <font-awesome-icon icon="fa-solid fa-scroll" class="fa-4x" />
                <h5 class="mt-3 mb-1"><strong>과제 목록</strong></h5>
                <p class="mb-0">과제 진행 여부를</p>
                <p>확인할 수 있어요.</p>
              </div>
              <div>
                <font-awesome-icon icon="fa-solid fa-medal" class="fa-4x" />
                <h5 class="mt-3 mb-1"><strong>스터디 포인트</strong></h5>
                <p class="mb-0">포인트를 획득하고,</p>
                <p>레벨업 해보세요.</p>
              </div>
              <div>
                <font-awesome-icon icon="fa-solid fa-chart-pie" class="fa-4x" />
                  <h5 class="mt-3 mb-1"><strong>스터디 분석</strong></h5>
                  <p class="mb-0">어떤 스터디를,</p>
                  <p class="mb-0">얼마나 진행했는지</p>
                  <p>확인할 수 있어요.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </b-row>
  </div>
</template>

<script>

export default {
  name: "Begin",
  created: function () {
    if (localStorage.getItem("jwt")) {
      this.$router.push({ name: "Main" });
    } 
  }
};
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap');
  @import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic+Coding:wght@400;700&display=swap');
  #description{
    font-family: 'Black Han Sans', sans-serif;
  }
  #description2{
    font-family: 'Nanum Gothic Coding', monospace;
  }
  .landing_page {
    height: 900px;
  }
  .landing_content {
    margin-top: 48px;
    margin-left: 45px;
    margin-right: 45px;
  }
  .div {
    margin-top: 8rem;
  }
  .mypage {
    background-color: #d4e1f3;
  }
  .page {
    height: 500px;
  }
</style>

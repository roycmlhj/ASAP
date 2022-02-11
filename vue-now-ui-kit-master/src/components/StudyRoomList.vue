/*
    작성자 : 한슬기
    생성일 : 2022.02.03
    마지막 업데이트 : 2022.02.03
    
    메인페이지 > 스터디 리스트
 */
<template>
<div class="container">
  <div class="container d-flex flex-wrap">
    <div class="col-3 mb-5" v-for="study in studies" :key="study.id">
      <div class="studyRoom">
        <h5><strong>{{ study.studyName }}</strong></h5>
        <div class="d-flex justify-content-center">
          <p class="category" :style="{ width: study.category.length + 60 + '%'}">{{ study.category }}</p>
        </div>
        <div class="d-flex flex-wrap justify-content-center">
          <div v-for="(image, index) in study.memberImage" :key="index.id">
            <p v-if="image"><img :src="image" alt=""></p>
            <p v-else><img src="../views/accounts/assets/default.png"></p>
          </div>
        </div>
        <p><router-link :to="{ name: 'StudyRoom', params: { study_no : study.studyno }}"><b-button>입장하기</b-button></router-link></p>
      </div>  
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'StudyRoomList',
  props: {
    studies: {
      type: Array
    }
  },
}
</script>

<style scoped>
  .studyRoom {
    width: 230px;
    height: 250px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    border: 1px solid;
    margin-right: 1rem;
    border-radius: 2%;
    box-shadow: 5px 5px 5px 0px gray;
  }
  img {
    width: 40px;
    height: 40px;
    border-radius: 70%;
  }
  button {
    font-size: 13px;
    height: 38px;
    background-color: rgb(130, 163, 209);
  }
  button:hover {
    background-color: rgb(79, 138, 216);
  }
  h5 {
    color: rgb(130, 163, 209);
  }
  .category {
    background-color: rgb(245, 240, 235);
    box-shadow: 5px 0px 5px 0px gray;
  }
</style>
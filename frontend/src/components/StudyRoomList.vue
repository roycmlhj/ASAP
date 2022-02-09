/*
    작성자 : 한슬기
    생성일 : 2022.02.03
    마지막 업데이트 : 2022.02.03
    
    메인페이지 > 스터디 리스트
 */
<template>
  <div class="container d-flex flex-wrap justify-content-center">
    <div class="mb-5" v-for="study in studies" :key="study.id">
      <div class="studyRoom">
        <h5><strong>{{ study.studyName }}</strong></h5>
        <div class="d-flex justify-content-center">
          <p class="category" :style="{ width: study.category.length + 60 + '%'}">{{ study.category }}</p>
        </div>
        <p>
          <img src="https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_311/3-2-%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg" alt="default_image">
          <img src="https://cdn.imweb.me/thumbnail/20200606/09c71b2f94ea5.jpg" alt="default_image">
          <img src="https://w.namu.la/s/7dca3fe36abec714f9f294ec68e4127f52bfc9d234639d0fd20d4820ef33e4ed6a61c072d1d7570737a4cfe681e82534466b6f890789ddb218a245d8969845b74e84f75df8e85d8984a3d9c57812d6d589734af074645802fa7d9594f0e0049e" alt="default_image">   
          <img src="http://newsimg.hankookilbo.com/2019/04/29/201904291390027161_3.jpg" alt="default_image">
        </p>
        <p><router-link :to="{ name: 'StudyRoom', params: { study_no : study.studyno }}"><b-button>입장하기</b-button></router-link></p>
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
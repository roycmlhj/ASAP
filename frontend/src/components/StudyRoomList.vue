<template>
  <div>
    <swiper class="swiper" :options="swiperOption"> 
      <swiper-slide v-for="study in studies" :key="study.id">
        <div class="studyRoom">
          <h5><strong>{{ study.studyName }}</strong></h5>
          <p class="mt-5">
            <img src="https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_311/3-2-%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg" alt="default_image">
            <img src="https://cdn.imweb.me/thumbnail/20200606/09c71b2f94ea5.jpg" alt="default_image">
            <img src="https://w.namu.la/s/7dca3fe36abec714f9f294ec68e4127f52bfc9d234639d0fd20d4820ef33e4ed6a61c072d1d7570737a4cfe681e82534466b6f890789ddb218a245d8969845b74e84f75df8e85d8984a3d9c57812d6d589734af074645802fa7d9594f0e0049e" alt="default_image">   
            <img src="http://newsimg.hankookilbo.com/2019/04/29/201904291390027161_3.jpg" alt="default_image">
          </p>
          <p><b-button style="background-color: #E9D155; color: black; font-size: 11px;">입장하기</b-button></p>
        </div>  
      </swiper-slide> 
      <div class="swiper-button-prev" slot="button-prev"></div> 
      <div class="swiper-button-next" slot="button-next"></div> 
    </swiper>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper' 
import 'swiper/css/swiper.css'

export default {
  name: 'StudyRoomList',
  props: {
    studies: {
      type: Array
    }
  },
  components: { 
    Swiper, 
    SwiperSlide 
  },
  data() { 
    return { 
      swiperOption: { 
        slidesPerView: 3, 
        spaceBetween: 30, 
        loop: true, 
        navigation: { 
          nextEl: '.swiper-button-next', 
          prevEl: '.swiper-button-prev' 
        } 
      } 
    } 
  } 
}
</script>

<style scoped>
  .studyRoom {
    width: 250px;
    height: 250px;
    background-color: #2d867d57;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    border-radius: 10%;
    border: 1px solid
  }
  img {
    width: 40px;
    height: 40px;
    border-radius: 70%;
  }
</style>
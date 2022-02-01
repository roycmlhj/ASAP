<template>
  <div>
    <swiper class="swiper" :options="swiperOption"> 
      <swiper-slide v-for="study in studies" :key="study.id">{{ study.studyName }}</swiper-slide> 
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
        slidesPerView: 1, 
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

<style>

</style>
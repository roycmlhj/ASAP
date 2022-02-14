<template>
  <div style="width: 1050px; display: inline-block" class="mt-4 mb-4">
    <swiper ref="swiper" :options="swiperOption">
      <swiper-slide v-for="study in studies" :key="study.id"
        ><div class="studyRoom" style="background-color: #ffffff">
          <h5 class="d-flex justify-content-center">
            <strong>{{ study.studyName }}</strong>
          </h5>
          <div class="d-flex justify-content-center">
            <p
              class="category d-flex justify-content-center"
              :style="{ width: study.category.length + 60 + '%' }"
            >
              {{ study.category }}
            </p>
          </div>
          <div class="d-flex flex-wrap justify-content-center">
            <div v-for="(image, index) in study.memberImage" :key="index.id">
              <p v-if="image"><img :src="image" alt="" /></p>
              <p v-else><img src="../views/accounts/assets/default.png" /></p>
            </div>
          </div>
          <div class="d-flex justify-content-center">
            <router-link
              :to="{ name: 'StudyRoom', params: { study_no: study.studyno } }"
              ><b-button>입장하기</b-button></router-link
            >
          </div>
        </div></swiper-slide
      >
      <div class="swiper-pagination" slot="pagination"></div>
      <!-- <div class="swiper-button-prev" slot="button-prev"></div>
      <div class="swiper-button-next" slot="button-next"></div> -->
    </swiper>
  </div>
</template>

<script>
import { Swiper, SwiperSlide, directive } from "vue-awesome-swiper";
import "swiper/css/swiper.css";

export default {
  name: "StudyRoomList",
  props: {
    studies: {
      type: Array,
    },
  },
  components: { Swiper, SwiperSlide },
  directives: {
    swiper: directive,
  },
  data() {
    return {
      swiperOption: {
        slidesPerView: 3,
        spaceBetween: 10,
        loop: false,
        pagination: { el: ".swiper-pagination", clickable: true },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
        // slidesPerView: "1",
        // spaceBetween: 1, // swiper-slide 사이의 간격 지정
        // slidesOffsetBefore: 0, // slidesOffsetBefore는 첫번째 슬라이드의 시작점에 대한 변경할 때 사용
        // slidesOffsetAfter: 0, // slidesOffsetAfter는 마지막 슬라이드 시작점 + 마지막 슬라이드 너비에 해당하는 위치의 변경이 필요할 때 사용
        // freeMode: true, // freeMode를 사용시 스크롤하는 느낌으로 구현 가능
        // centerInsufficientSlides: true, // 컨텐츠의 수량에 따라 중앙정렬 여부를 결정함
        // loop: false,
        // pagination: { el: ".swiper-pagination", clickable: true },
        // navigation: {
        //   nextEl: ".swiper-button-next",
        //   prevEl: ".swiper-button-prev",
        // },
      },
    };
  },
};
</script>

<style scoped>
.studyRoom {
  width: 340px;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  border: 1px solid;
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
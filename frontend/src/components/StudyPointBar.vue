/*
    작성자 : 한슬기
    생성일 : 2022.02.09
    마지막 업데이트 : 2022.02.09
    
    마이페이지 > 스터디 포인트
 */
<template>
  <div>
    <h5><strong>스터디 포인트</strong></h5>
    <div class="d-flex justify-content-between">
      <p>현재 레벨 : {{ userInfo.level }}</p>
      <p>다음 레벨 : {{ userInfo.level + 1 }}</p>
    </div>
    <b-progress :max="max" height="3rem">
      <b-progress-bar :value="value" style="background-color: #78c0e2;">
        <span><strong>{{ value.toFixed(2) }} / {{ max }}</strong></span>
      </b-progress-bar>
    </b-progress>
  </div>
</template>

<script>
export default {
  name: 'StudyPointBar',
  props: {
    userInfo: {
      type: Object
    }
  },
  data() {
    return {
      value: 66.7,
      // value: this.userInfo.exp,
      max: 100
    }
  },
}
</script>

<style scoped>
  p {
    margin: 0;
  }
  h5 {
    text-align: start;
  }
</style>
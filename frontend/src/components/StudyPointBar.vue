<template>
  <div class="point">
    <h5><strong>스터디 포인트</strong></h5>
    <div class="d-flex justify-content-start">현재 레벨 : {{ userInfo.level }}</div>
    <div class="d-flex justify-content-start">다음 레벨 : {{ userInfo.level + 1 }}</div>
    <div class="mt-3">
      <p v-if="this.userInfo.level == 1" class="d-flex justify-content-between">
        <font-awesome-icon class="fa-2x mb-2" icon="star"/>
        <font-awesome-icon class="fa-2x mb-2" icon="medal"/>
      </p>
      <p v-if="userInfo.level == 2" class="d-flex justify-content-between">
        <font-awesome-icon class="fa-2x mb-2" icon="medal"/>
        <font-awesome-icon class="fa-2x mb-2" icon="bahai"/>
      </p>
      <p v-if="userInfo.level == 3" class="d-flex justify-content-between">
        <font-awesome-icon class="fa-2x mb-2" icon="bahai"/>
        <font-awesome-icon class="fa-2x mb-2" icon="award"/>
      </p>
      <p v-if="userInfo.level == 4" class="d-flex justify-content-between">
        <font-awesome-icon class="fa-2x mb-2" icon="award"/>
        <font-awesome-icon class="fa-2x mb-2" icon="trophy"/>
      </p>
      <p v-if="userInfo.level == 5" class="d-flex justify-content-between">
        <font-awesome-icon class="fa-2x mb-2" icon="award"/>
        <font-awesome-icon class="fa-2x mb-2" icon="trophy"/>
      </p>
    </div>
    <b-progress :max="max" height="3rem">
      <b-progress-bar :value="value" style="background-color: #78c0e2;">
        <span><strong>{{ value.toFixed(0) }} / {{ max }}</strong></span>   <!--수정-->
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
      // value: 66.7,
      value: this.userInfo.exp,
      max: 100
    }
  },
}
</script>

<style scoped>
  .point {
    display: flex;
    flex-direction: column;
  }
  p {
    margin: 0;
  }
  h5 {
    text-align: start;
    margin: 0px;
  }
</style>
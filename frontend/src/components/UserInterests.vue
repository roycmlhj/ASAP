<template>
  <div class="d-flex flex-wrap justify-content-center" :interestList="getInterests()">
    <p v-for="interest in interestList" :key="interest.id" :style="{ width: interest.length +70 + 'px'}">
      {{ interest }}
    </p>
  </div>
</template>

<script>
export default {
  name: 'UserInterests',
  data: function () {
    return {
      interestList: null,
    }
  },
  props: {
    interests: {
      type: String
    }
  },
  methods: {
    getInterests: function () {
      this.interestList = this.interests.split('#')
      this.interestList.shift()
  },
  }
}
</script>

<style scoped>
  p {
    margin-right: 15px;
    background-color: antiquewhite;
  }
</style>
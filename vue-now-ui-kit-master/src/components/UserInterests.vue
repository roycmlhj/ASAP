<template>
  <div class="d-flex flex-wrap justify-content-center">
    <p v-for="interest in interestList" :key="interest.id" :style="{ width: interest.length +75 + 'px'}">
      {{ interest }}
    </p>
  </div>
</template>

<script>
export default {
  name: 'UserInterests',
  props: {
    interestList: {
      type: Array
    }
  },
}
</script>

<style scoped>
  p {
    margin-right: 15px;
    background-color: rgb(248, 239, 228);
  }
</style>
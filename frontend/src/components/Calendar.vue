<template>
  <div class="container">
    <full-calendar :events="fcEvents" locale="en"></full-calendar>
  </div>
</template>

<script>
export default {
  name: 'calendar',
  components: {
   'full-calendar': require('vue-fullcalendar')
  },
  props: {
    demoEvents: {
      type: Array
    }
  },
  data: function () {
    return {
      fcEvents : this.demoEvents
    }
  },
}
</script>

<style scoped>
  
</style>
<template>
  <div>
    <br />
    <h4 style="font-family: 'Do Hyeon', sans-serif;"><strong>미완료 과제 목록</strong></h4>   
    <div v-for="(homework, index) in onHomeworkList" :key="index.id">    <!--수정-->
      <p v-if="index < 5">{{ homework.homework.title }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainUndoHomework",
  props: {
    onHomeworkList: {
      type: Array,
    },
  },
  data() {
    return {};
  },
  methods: {},
  computed: {},
};
</script>
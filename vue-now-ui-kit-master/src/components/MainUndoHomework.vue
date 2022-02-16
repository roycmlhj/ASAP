<template>
  <div>
    <br />
    <div v-for="homework in onHomeworkList" :key="homework.id">
      <p>{{ homework.homework.title }}</p>
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
<template>
  <div>
    <b-form-select @change="memberUpdate" v-model="selected" :options="options" style="width: 15%" required></b-form-select>
  </div>
</template>

<script>

export default {
  name: 'StudyMemberCountBar',
  data() {
    return {
      selected: null,
      options: [
        { value: 1, text: '1' },
        { value: 2, text: '2' },
        { value: 3, text: '3' },
        { value: 4, text: '4' },
        { value: 5, text: '5' },
        { value: 6, text: '6' },
      ]
    }
    
  },
  methods: {
    memberUpdate(){
      console.log("updating")
      this.$emit("memberEvent", this.selected);
    }
  }
}
</script>

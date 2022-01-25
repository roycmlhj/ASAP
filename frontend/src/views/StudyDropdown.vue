<template>
  <div>
    <b-form-select @change="studyUpdate" v-model="selected" :options="options"></b-form-select>
  </div>
</template>

<script>

  
  export default {
    
    data() {
      return {
        dropdownContent: [],
        selected: null,
        options: this.studies,
        
      }
    },
    mounted() {
      //여기서 axios통신 일단 임시로 대충 넣자
      var a = [{title:"a",topic:"atopic",description:"adescription",},{title:"b",topic:"btopic",description:"bdescription",},{title:"c",topic:"ctopic",description:"cdescription",}]
      var res = []
      console.log(a)
      for (var i = 0; i<a.length;i++) {
        res.push({value:a[i],text:a[i].title})
      }
      console.log(res)
      this.options = res
    },
    methods: {
      studyUpdate(){
        console.log("updating")
        this.$emit("studyEvent",this.selected);
      }
    }
  }
</script>
<template>
  <div>
    <b-form-select @change="studyUpdate" v-model="selected" :options="options"></b-form-select>
  </div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
export default {
  name: 'StudyDropdown',
  data() {
    return {
      dropdownContent: [],
      studies: [],
      selected: null,
      options: [],
    }
  },
  created() {
    const token = localStorage.getItem('jwt')
    const decoded =jwt_decode(token)
    const userno = decoded.userno
    //여기서 axios통신 일단 임시로 대충 넣자
    axios({
      method:'get',
      url:`http://localhost:8080/api/v1/study/list/${userno}/`,
      
    }).then(res=> {
      console.log(res)
      this.studies = res.data.studyList
      console.log("studies")
      console.log(this.studies)
      for (var i = 0; i<this.studies.length;i++) {
        this.options.push({value:this.studies[i],text:this.studies[i].studyname})
      }
      console.log(this.options)
      
    }).catch(err => {
      console.log(err)
    })
    
  },
  methods: {
    studyUpdate(){
      console.log("updating")
      this.$emit("studyEvent",this.selected);
    }
  }
}
</script>

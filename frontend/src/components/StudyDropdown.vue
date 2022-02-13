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
    
    axios({
      method:'get',
      url:`http://localhost:8080/api/v1/study/list/${userno}/`,
      
    }).then(res=> {
      console.log(res)
      this.studies = res.data.studyList
      
       for (var i = 0; i<this.studies.length;i++) {
         this.options.push({value:this.studies[i],text:this.studies[i].studyname})

       }
      
      
    }).catch(err => {
      console.log(err)
    })
    // if(update==1){
    //   selected = 
    // }
  },
  methods: {
    studyUpdate(){
      console.log("updating")
      console.log("selected is " , this.selected)
      this.$emit("studyEvent",this.selected);
    }
  }
}
</script>


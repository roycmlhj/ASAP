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
    const token = sessionStorage.getItem('jwt')                  // 수정
    const decoded =jwt_decode(token)
    const userno = decoded.userno
    const email = decoded.email
    
    axios({
      method:'get',
      url:`https://i6a107.p.ssafy.io:8443/api/v1/study/list/${userno}/`,
      
    }).then(res=> {
      console.log(res)
      this.studies = res.data.studyList
      
       for (var i = 0; i<this.studies.length;i++) {
         if (this.studies[i].maker == email) {
          this.options.push({value:this.studies[i],text:this.studies[i].studyname})
         }
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


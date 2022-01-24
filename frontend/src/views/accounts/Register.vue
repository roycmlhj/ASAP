/*
    회원 가입 페이지

    작성자 : 고주희
    생성일 : 2022.01.21
    마지막 업데이트 : 2022.01.21
 */

<template>
<div>
  <h3>회원가입</h3>
    <form   ref="RegisterForm" >
      <label for="Email" >Email</label>
        <input  v-model="user.id"/><br/>
        <label for="password" >PW</label>
        <input v-model="user.password" autocomplete="off" show-password/><br/>
    
      <label for="passwordcheck">PW check</label>
        <input v-model="passwordcheck" autocomplete="off" show-password/><br/>
      
      <label for="Nickname">Nickname </label>
        <input v-model="user.nickname" autocomplete="off"/><br/>

      <label for="interests">관심분야 </label>
      <select name="interests" id="interests" v-model="user.interest">
          <option value="0">선택해주세요</option>
          <option v-for="interest in interestsList" :key="interest.id" :value="interest.id">{{interest.iname}}</option>
        </select><br/>
      <label for="interdetaillist" >관심분야 상세</label>
        <b-form-tags 
            input-id="interdetaillist" 
            v-model="user.interdetaillist"
            tag-variant="orange"
            tag-pills
            
            ></b-form-tags>
        <!-- <p class="mt-2">Value: {{ user.interdetaillist }}</p> -->
      <!-- <div><kbd v-for="(inter, index) in user.interdetaillist" :key="index">{{inter}}</kbd> &nbsp;&nbsp;&nbsp;</div> -->
    </form>
    
      <span class="dialog-footer">
        <button type="primary" @click="clickRegister">회원가입</button>
      </span>
</div>
</template>


<script>
import interest from "./assets/interests.json"

export default {
  name: 'Register',
  data:()=>{
      return{
          interestsList : interest, // json 파일에서 관심 목록 불러옴
          user:{ // 보내기 전에 잠시 저장할 user 데이터
              id:'',
              password:'',
              nickname:'',
              interest:'',
              interdetaillist:[]

          },
          passwordcheck:'', // passwordcheck하려고 있는 데이터
          interdetail:[]
      }
  },
  methods:{
      clickRegister(){

      }
  },
  created(){
      console.log("시작")
    

  },
  watch:{
      interdetaillist:function(){

      }
    
  }
  
}
</script>

<style>

</style>

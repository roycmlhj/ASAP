/*
    작성자 : 한슬기
    생성일 : 2022.02.01
    마지막 업데이트 : 2022.02.01
    
    마이페이지 > 과제, 스터디 목록 테이블
 */
<template>
  <div class="container">
    <h5 v-if="studyList" class="float-left"><strong>스터디 목록</strong></h5>
    <h5 v-else class="float-left"><strong>과제 목록</strong></h5>
    <table class="table table-bordered table-sm">
      <thead>
        <tr>
          <th id="th" scope="col">스터디</th>
          <th id="th" scope="col">스터디 주제</th>
          <th id="th" scope="col">시작일</th>
          <th id="th" scope="col">진행 상태</th>
        </tr>
      </thead>
      <tbody v-for="study in studyList" :key="study.id">
        <tr>
          <td>{{ study.studyname }}</td>
          <td>{{ study.category }}</td>
          <td>2022.02.01</td>
          <td>진행</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'UserInfoTable',
  props: {
    studyList: {
      type: Array,
    }
  }
}
</script>

<style>
  #th {
    background-color: #e2e0e6;
  }
</style>
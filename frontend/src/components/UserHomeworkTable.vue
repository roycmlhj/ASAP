/*
    작성자 : 한슬기
    생성일 : 2022.02.07
    마지막 업데이트 : 2022.02.07
    
    마이페이지 > 과제 목록 테이블
 */
<template>
  <div class="container">
    <h5 class="float-left"><strong>과제 목록</strong></h5><br>
    <table class="table table-bordered table-sm">
      <thead>
        <tr>
          <th id="th" scope="col">스터디</th>
          <th id="th" scope="col">과제</th>
          <th id="th" scope="col">제출 기한</th>
          <th id="th" scope="col">진행 상태</th>
        </tr>
      </thead>
      <tbody v-for="homework in homeworkList" :key="homework.id">
        <tr>
          <td>{{ homework.studyname }}</td>
          <td>{{ homework.content }}</td>
          <td>{{  dateTime(homework.endDate) }}</td>
          <td>진행</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import moment from 'moment';

export default {
  name: 'UserInfoTable',
  props: {
    homeworkList: {
      type: Array,
    }
  },
  methods: {
    dateTime(value) {
      return moment(value).format('YYYY-MM-DD');
    },
  },
}
</script>

<style>
  #th {
    background-color: #e2e0e6;
  }
</style>
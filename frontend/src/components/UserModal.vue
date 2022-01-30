/*
    작성자 : 한슬기
    생성일 : 2022.01.30
    마지막 업데이트 : 2022.01.30
    
    개인정보수정 페이지
 */
<template>
  <div class="container">
    <p><img src="https://cdn.imweb.me/thumbnail/20200606/09c71b2f94ea5.jpg" alt="default_image"></p>
    <p>{{ member.email }}</p>
    <table class="table table-bordered">
      <tbody>
        <tr>
          <th>가입일</th>
          <td>{{ member.regidate }}</td>
        </tr>
        <tr>
          <th>관심분야</th>
          <td>{{ member.mainCategory }}</td>
        </tr>
        <tr>
          <th>포인트</th>
          <td>{{ member.exp }}</td>
        </tr>
        <tr>
          <th>레벨</th>
          <td>{{ member.level }}</td>
        </tr>
        <tr>
          <th>가입 스터디</th>
          <td>알고리즘 스터디</td>
          <!-- <td>{{ member.studyMemberList }}</td> -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'UserModal',
  props: {
    member: {
      type: Object
    }
  },
}
</script>

<style scoped>
  img {
    width: 70px;
    height: 70px;
    border-radius: 70%;
  }
  button {
    height: 30px;
  }
</style>
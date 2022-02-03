<template>

  <div class="container">
    <p class="icon">
      <font-awesome-icon class="fa-2x mb-2" icon="bell"/>
      <font-awesome-icon class="fa-2x mb-2" icon="edit"/>
      <font-awesome-icon class="clipboard fa-2x mb-2" icon="clipboard"/>
      <font-awesome-icon class="fa-2x mb-2" icon="calendar-week"/>
      <font-awesome-icon class="fa-2x mb-2" icon="cog"/>
    </p>
    <b-dropdown id="dropdown-dropright" dropright text="Drop-Right" class="m-2">
      <b-row>
        <b-col md="auto">
          <b-calendar v-model="value" @context="onContext" locale="en-US"></b-calendar>
        </b-col>
      </b-row>
    </b-dropdown>
    <font-awesome-icon class="fa-2x mb-2" icon="user-friends"/>
    <font-awesome-icon class="fa-2x mb-2" icon="info-circle"/>
    <font-awesome-icon class="fa-2x mb-2" icon="comment-dots"/>
  </div>
</template>

<script>

export default {
  data: function () {
      return {
        value: '',
        context: null
      }
    },
    methods: {
      onContext(ctx) {
        this.context = ctx
      }
    }
  }
</script>

<style scoped>
  .icon {
    display: flex;
    flex-direction: column;
  }
  .clipboard {
    margin-left: 1px;
  }
</style>
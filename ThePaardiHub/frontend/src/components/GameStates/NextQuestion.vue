<template>
  <div id="game-ready">
    <p class="game-status">{{$t('message.waitingQuestion')}}</p>
  </div>
</template>

<script>
export default {
  name: "game",
  props: {
    body: {}
  }
};
</script>

<style scoped>

</style>

<template>
  <div id="game-ready">
        <p class="game-status">{{$t('message.waitingPlayers')}}</p>
  </div>
</template>

<script>
export default {
  name: "game-ready",
  props: {
    body: {}
  }
};
</script>

<style scoped>

</style>

<template>
  <div id="game-end">
        <p class="game-status">{{$t('message.gameEnd')}}</p>
        <h2 class="game-name">{{body.gameName}}</h2>
        <ul class="game-players">
          <li class="game-player" v-for="(player,index) in orderedPlayers " :key="index">{{player.name}}</li>
        </ul>
  </div>
</template>

<script>
export default {
  name: "game-end",
  props: {
    body: {}
  },
  computed: {
  orderedPlayers: function () {
    return _.orderBy(body.players, 'score')
  }
}
};
</script>

<style scoped>

</style>

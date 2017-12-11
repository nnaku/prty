<template>
  <div id="game-ready">
        <p class="game-status">{{$t('message.waitingPlayers')}}</p>
        <h1 class="game-name">{{body.gameName}}</h1>
        <p class="game-desc">{{body.gameDescription}}</p>
        <p class="game-n-of-quiz">{{$t('message.nOfQuiz')+" "+body.nOfQuiz}}</p>
        <p class="game-auth">{{$t('message.gameAuthor')+" "+body.author}}</p>
        <ul class="game-players">
          <li class="game-player" v-for="(player,index) in body.players" :key="index">{{player.name}}</li>
        </ul>
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

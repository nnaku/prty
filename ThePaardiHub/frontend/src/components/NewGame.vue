<template>
    <div id="newGame" class="new-game">
        <h3>Create new game.</h3>
        <div class="game-form">
            <input v-model="newGame.name" type="text" placeholder="Game name">
            <input v-model="newGame.description" type="text" placeholder="Game description">
            <label for="private">Private game</label><input v-model="newGame.private" id="private" type="checkbox">
            <button @click="saveGame()">Save game</button>
        </div>
    </div>
</template>

<script>
export default {
  name: "newGame",
  props: {
    game: {}
  },
  data() {
    return {
      newGame: this.game
    };
  },
  methods: {
    saveGame() {
      console.log(JSON.stringify(this.newGame));
    }
  },
  watch: {
    game: function(val) {
      this.newGame = val;
    }
  }
};
</script>

<style scoped>

</style>
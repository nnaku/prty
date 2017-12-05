<template>
    <div id="showGame" class="show-game">
        <h3>Show game.</h3>
        <div class="game-form">
            <h2>{{newGame.name}}</h2>
            <h5 v-if="newGame.private">This game is private</h5>
            <h5 v-else>This game is public</h5>
            <p>{{newGame.description}}</p>
            <h5>Questions</h5>
            <div v-for=" (question, index) in newGame.questions" :key="index" >
                {{question}}
            </div>
            <button @click="editGame()">Edit game</button>
        </div>
    </div>
</template>

<script>
export default {
  name: "showGame",
  props: {
    game: {}
  },
  data() {
    return {
      newGame: this.game
    };
  },
  methods: {
    editGame() {
      this.$parent.display = "newgame";
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
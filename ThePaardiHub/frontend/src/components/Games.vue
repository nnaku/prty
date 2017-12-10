<template>
  <div id="games">
    <div class="games">
      <!-- Messages are from locale/locales.json -->
        <h2>{{ $t('message.myGamesTitle') }}</h2>
        <a class="gameRow" v-for="(game,index) of games" :key="game.id" @click="selectGame(game.id)">
          <div class="game-name" v-if="game.gameName.length <= 20">{{game.gameName}}</div>
          <div v-else>{{game.gameName.substring(0, 19)+"..."}}</div>
        </a>
    </div>
    <div class="editor">
        <h2>{{ $t('message.editor') }}</h2>
        <button v-if="display !== 'newgame' | display === 'showgame'" @click="newGame()" >{{ $t('message.newGame') }}</button>
        <button v-if="display !== 'newquestion' | display === 'showgame'" @click="display = 'newquestion'">{{ $t('message.newQuestion') }}</button>

        <newGameForm class="new-game" v-if="display == 'newgame'" v-bind:game="this.game">{{ $t('message.game') }}</newGameForm>
        <newQuestionForm class="new-question" v-else-if="display == 'newquestion'">{{ $t('message.question') }}</newQuestionForm>
        <showGame class="show-game" v-else-if="display == 'showgame'" v-bind:game="this.game"></showGame>
    </div>
  </div>
</template>

<script>
import newGameForm from "./NewGame";
import newQuestionForm from "./NewQuestion";
import showGame from "./ShowGame.vue";

export default {
  name: "games",
  data() {
    return {
      display: "newgame",
      game: {},
      games: []
    };
  },
  methods: {
    newGame() {
      this.game = [];
      this.display = "newgame";
    },
    selectGame(gameId) {
      this.$nextTick(function() {
        this.axios
          .get("/gameset?id=" + gameId)
          .then(response => {
            this.game = JSON.parse(JSON.stringify(response.data.game));
          })
          .catch(e => {
            this.errors.push(e);
          });
      });
      this.display = "showgame";
    },
    loadGames() {
      this.$nextTick(function() {
        this.axios
          .get("/gamesets")
          .then(response => {
            this.games = JSON.parse(JSON.stringify(response.data.game));
          })
          .catch(e => {
            this.errors.push(e);
          });
      });
    }
  },
  mounted: function() {
    this.loadGames();
  },
  components: {
    newQuestionForm,
    newGameForm,
    showGame
  },
  computed: {}
};
</script>

<style scoped>
#games {
  width: 100%;
  margin: auto;
}
#games > div {
  box-sizing: border-box;
}
.games {
  width: 25%;
  float: left;
}
.games > .gameRow > div {
  margin: auto;
  width: 80%;
  padding: 3%;
}
.games > .gameRow > div:hover {
  background-color: yellow;
  cursor: pointer;
}
.games > .gameRow > .played {
  text-decoration: underline;
}
.games > .gameRow:nth-child(odd) {
  background: lightgray;
}
.editor {
  display: inline-block;
  width: 75%;
}
.editor > button {
}
.editor > .new-game {
}
.editor > .new-question {
}
.editor > .hidden {
  display: none;
}
</style>




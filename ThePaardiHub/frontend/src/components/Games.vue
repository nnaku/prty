<template>
  <div id="games">
    <div class="games">
        <h2>My Games</h2>
        <a class="gameRow" v-for="(game,index) of games" :key="index" @click="selectGame(index)">
            <div class="not-played" v-if="!game.completed" >
                <div class="game-name" v-if="game.title.length <= 20">{{game.title}}</div>
                <div v-else>{{game.title.substring(1, 20)+"..."}}</div>
            </div>
            <div class="played" v-else>
                <div class="game-name" v-if="game.title.length <= 20">{{game.title}}</div>
                <div v-else>{{game.title.substring(1, 20)+"..."}}</div>
            </div>
        </a>
    </div>
    <div class="editor">
        <h2>Editor</h2>
        <button v-if="display !== 'newgame' | display === 'showgame'" @click="newGame()" >New Game</button>
        <button v-if="display !== 'newquestion' | display === 'showgame'" @click="display = 'newquestion'">New Question</button>


        <newGameForm class="new-game" v-if="display == 'newgame'" v-bind:game="this.game">game</newGameForm>
        <newQuestionForm class="new-question" v-else-if="display == 'newquestion'">question</newQuestionForm>
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
    newGame(){
      this.game = []
      this.display = 'newgame'
    },
    selectGame(gameId) {
      // static test contetnt here!!!

      if ((gameId + 1) % 2 == 0) {
        this.game = {
          name: "Parillinen peli",
          description:
            "Tämä peli on parillinen ja sisältää vain parillisia kysymysksiä!",
          questions: [2, 4, 6, 8, 10],
          private: true
        };
      } else {
        this.game = {
          name: "Pariton peli",
          description:
            "Tämä peli on pariton ja sisältää vain parittomia kysymysksiä!",
          questions: [1, 3, 5, 7, 9],
          private: false
        };
      }
      this.display = "showgame";
    }
  },
  mounted: function() {
    this.$nextTick(function() {
      this.axios
        .get("https://jsonplaceholder.typicode.com/todos")
        .then(response => {
          this.games = response.data;
        })
        .catch(e => {
          this.errors.push(e);
        });
    });
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




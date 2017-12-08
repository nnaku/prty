<template>
    <div id="showGame" class="show-game">
        <h3>Show game.</h3>
        <div class="game-form">
            <h2>{{newGame.gameName}}</h2>
            <h5 v-if="newGame.isPrivate">This game is private</h5>
            <h5 v-else>This game is public</h5>
            <p v-html="newGame.description"/>
            <h5>Questions</h5>
            <div v-for=" (question, index) in newGame.questions" :key="index" >
                <p v-html="question.question"/>
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
      newGame: {
        id: null,
        author: "",
        gameName: "",
        isPrivate: true,
        questions: [],
        description: ""
      },
      errors: []
    };
  },
  methods: {
    editGame() {
      this.$parent.display = "newgame";
    }
  },
  watch: {
    game: function(val) {
      this.newGame.id = val.id;
      this.newGame.author = val.author;
      this.newGame.gameName = val.gameName;
      this.newGame.isPrivate = val.isPrivate;
      this.newGame.description = val.description;
      this.axios
        .get("/question?id=" + val.questions)
        .then(response => {
           this.newGame.questions = JSON.parse(JSON.stringify(response.data.questions));
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  },
  mounted: function() {}
};
</script>

<style scoped>

</style>
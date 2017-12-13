<template>
    <div id="showGame" class="show-game">
      <!-- Messages are from locale/locales.json -->
        <h3>{{ $t('message.showGameTitle') }}</h3>
        <div class="game-form">
            <h2>{{newGame.gameName}}</h2>
            <h5 v-if="newGame.isPrivate">{{ $t('message.privateGameTitle') }}</h5>
            <h5 v-else>{{ $t('message.publicGameTitle') }}</h5>
            <p v-html="newGame.description"/>
            <h5>{{ $t('message.questionsTitle') }}</h5>
            <div v-for=" (question, index) in newGame.questions" :key="index" >
                <p v-html="question.question"/>
            </div>
            <button @click="createLobby()">{{ $t('message.createLobby') }}</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
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
    createLobby() {
      axios
        .post("/createlobby", { id: this.game.id })
        .then(response => {
          console.log(response.data)
          this.$router.push("/lobby");
        })
        .catch(e => {
          this.errors.push(e);
        });
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
          this.newGame.questions = JSON.parse(
            JSON.stringify(response.data.questions)
          );
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
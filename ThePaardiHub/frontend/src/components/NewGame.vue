<template>
    <div id="newGame" class="new-game">
      <!-- Messages are from locale/locales.json -->
        <h3>{{ $t('message.createNewGameTitle') }}</h3>
        <div class="game-form">
          <input v-model="newGame.gameName" type="text" :placeholder="$t('message.gameName')">
          <input v-model="newGame.description" type="text" :placeholder="$t('message.gameDesc')">
          <label for="private">{{ $t('message.privateGame') }}</label><input v-model="newGame.isPrivate" id="private" type="checkbox">
          <h2>{{ $t('message.selectQuestionsTitle') }}</h2>
          <a class="selected-questions-row" :id="'selected-'+question.id" v-for="(question,index) of selectedQuestios" :key="question.id" @click="removeQuestion(index)">
            <p v-html="question.question"/>
          </a>
          <button @click="saveGame()">{{ $t('message.saveGameButton') }}</button>
        </div>
        <div class="my-questions">
          <h2>{{ $t('message.myQuestionsTitle') }}</h2>
          <a class="questions-row" :id="'not-selected-'+question.id" :ref="question.id" v-for="(question,index) of myQuestions" :key="question.id" @click="selectQuestion(index)">
            <p v-html="question.question"/>
          </a>
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
      newGame: {
        id: null,
        author: "",
        gameName: "",
        isPrivate: true,
        questions: "",
        description: ""
      },
      myQuestions: [],
      selectedQuestios: [],
      errors: []
    };
  },
  methods: {
    selectQuestion(index) {
      this.selectedQuestios.push(this.myQuestions[index]);
      this.$delete(this.myQuestions, index);
    },
    removeQuestion(index) {
      this.myQuestions.push(this.selectedQuestios[index]);
      this.$delete(this.selectedQuestios, index);
    },
    loadMyQuestions() {
      this.axios
        .get("/questions")
        .then(response => {
          this.myQuestions = JSON.parse(
            JSON.stringify(response.data.questions)
          );
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    saveGame() {
      var array = Array();
      this.selectedQuestios.forEach(question => {
        array.push(question.id);
        this.newGame.questions = array.join(";");
      });
      this.axios
        .post("/gameset", {
          gameName: this.newGame.gameName,
          isPrivate: this.newGame.isPrivate,
          questions: this.newGame.questions,
          description: this.newGame.description
        })
        .then(response => {
          this.$parent.loadGames();
          this.loadMyQuestions();
          this.selectedQuestios = [];
          this.newGame = {
            id: null,
            author: "",
            gameName: "",
            isPrivate: true,
            questions: "",
            description: ""
          };
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  },
  watch: {
    game: function(val) {
      this.newGame = val;
    }
  },
  beforeMount() {
    this.loadMyQuestions();
  }
};
</script>

<style scoped>

</style>
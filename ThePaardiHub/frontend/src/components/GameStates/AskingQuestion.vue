<template>
  <div id="game-on">
    <div class="game-answers-options">
      <a class="game-answer" :id="index" v-on:click.once="answer(option)" v-for="(option,index) in body.options":key="index" :disabled="answered">
        {{option}}
      </a>
    </div>
  </div>
</template>

<script>

export default {
  name: "game-on",
  props: {
    body: {}
  },
  data() {
    return {
      received_messages: {},
      playerInfo: {},
      answered: false,
    };
  },
  methods: {
    answer(option){
      this.answered = true;
      this.$parent.send(option);
    }
    
  }
};
</script>

<style scoped>
.game-question {
  margin: 50px;
}

.game-answers-options > .game-answer {
}
.game-answers-options > .game-answer:nth-child(1) {
  background-color: Tomato;
}
.game-answers-options > .game-answer:nth-child(2) {
  background-color: MediumSeaGreen;
}
.game-answers-options > .game-answer:nth-child(3) {
  background-color: Orange;
}
.game-answers-options > .game-answer:nth-child(4) {
  background-color: DodgerBlue;
}
.game-answers-options > .game-answer:visited {
  color: red;
}
</style>

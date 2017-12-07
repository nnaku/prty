<template>
    <div id="newQuestion" class="new-question">
        <h3>Create new question.</h3>
        <div class="question-form">
            <input v-model="newQuestion.question" class="question" type="text" placeholder="Question">
            <input v-model="newQuestion.right" class="right-answer" type="text" placeholder="Right answer">
            <input v-model="newQuestion.wrong1" class="wrong-answer" type="text" placeholder="Wrong answer">
            <input v-model="newQuestion.wrong2" class="wrong-answer" type="text" placeholder="Wrong answer">
            <input v-model="newQuestion.wrong3" class="wrong-answer" type="text" placeholder="Wrong answer">
            <label for="private">Private question</label><input v-model="newQuestion.private" id="private" type="checkbox">
            <button @click="saveQuestion()">Save question</button>
        </div>
    </div>
</template>

<script>
export default {
  name: "newQuestion",
  data() {
    return {
      newQuestion:{}
    };
  },
  methods: {
    saveQuestion() {
      console.log(JSON.stringify(this.newQuestion));
    }
  }
};
</script>

<style scoped>

</style>




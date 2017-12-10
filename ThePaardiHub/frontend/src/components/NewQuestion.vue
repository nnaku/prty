<template>
    <div id="newQuestion" class="new-question">
      <!-- Messages are from locale/locales.json -->
        <h3>{{ $t('message.createNewQuestionTitle') }}</h3>
        <div class="question-form">
            <input v-model="newQuestion.question" class="question" type="text" :placeholder="$t('message.question')">
            <input v-model="newQuestion.right" class="right-answer" type="text" :placeholder="$t('message.rightAnswer')">
            <input v-model="newQuestion.wrong1" class="wrong-answer" type="text" :placeholder="$t('message.wrongAnswer')">
            <input v-model="newQuestion.wrong2" class="wrong-answer" type="text" :placeholder="$t('message.wrongAnswer')">
            <input v-model="newQuestion.wrong3" class="wrong-answer" type="text" :placeholder="$t('message.wrongAnswer')">
            <label for="private">{{ $t('message.privateQuestion') }}</label><input v-model="newQuestion.private" id="private" type="checkbox">
            <button @click="saveQuestion()">{{ $t('message.saveQuestionButton') }}</button>
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




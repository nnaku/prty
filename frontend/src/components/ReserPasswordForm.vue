<template>
  <div class="registeration box">
    <div class="box-header register">
      <!-- Messages are from locale/locales.json -->
      <div class="title register">{{$t('message.registerheader')}}</div>
      <div class="error">{{responseData}}</div>
    </div>
    <div class="box-body register">
        <input v-model="formData.email" type="email" :placeholder="$t('message.email')"> <span>*</span>
        <button type="button" @keyup.enter="postForm()" @click="postForm()">{{$t('message.submit')}}</button>
    </div>
    <div class="box-footer register">
      <router-link class="routerLink" v-bind:to="'/login'">{{$t('message.backToLogin')}}</router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "registeration-form",
  data() {
    return {
      responseData: "",
      formData: {
        firstname: "",
        lastname: "",
        username: "",
        email: "",
        password: "",
        passwordVerify: ""
      }
    };
  },
  methods: {
  postForm() {
    axios
      .post("/resetpassword", {
        email: this.$data.email
      })
      .then(response => {
        this.posts = response.data;
        console.log("Valid response");
        console.log(JSON.stringify(response.data));
      })
      .catch(e => {
        this.responseData = e.response.data;
        console.log("Invalid response");
        console.log(JSON.stringify(e));
      });
    }
  }
};
</script>

<style>
@import "../css/forms.css";
</style>
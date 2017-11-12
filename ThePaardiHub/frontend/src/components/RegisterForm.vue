<template>
  <div class="registeration box">
    <div class="box-header register">
      <div class="title register">Register an account</div>
      <div class="error">{{responseData}}</div>
    </div>
    <div class="box-body register">
        <input v-model="formData.firstname" type="text" placeholder="First name">
        <input v-model="formData.lastname" type="text" placeholder="Last name">
        <input v-model="formData.username" type="text" placeholder="User name">
        <input v-model="formData.email" type="email" placeholder="Email">
        <input v-model="formData.password" type="password" placeholder="Password">
        <input v-model="formData.passwordVerify" type="password" placeholder="Confirm password">
        <button type="button" v-on:click="postForm()">Submit</button>
    </div>
    <div class="box-footer register">
      <a class="forgot" v-on:click="showLogin()">Already have an account</a>
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
    showLogin() {
      this.$parent.$parent.showLogin();
    },
    postForm() {
      axios
        .post("/register", {
          firstname: this.formData.firstname,
          lastname: this.formData.lastname,
          username: this.formData.username,
          email: this.formData.email,
          password: this.formData.password,
          passwordVerify: this.formData.passwordVerify
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
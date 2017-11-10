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
        <button type="button" @click="postForm()">Submit</button>
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
      responseData:"",
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
          firstname: this.$data.firstname,
          lastname: this.$data.lastname,
          username: this.$data.username,
          email: this.$data.email,
          password: this.$data.password,
          passwordVerify: this.$data.passwordVerify
        })
        .then(response => {
          this.posts = response.data;
          // console.log(JSON.stringify(response.data));
        })
        .catch(e => {
          this.responseData = e.response.data;
          console.log(JSON.stringify(e));
        });
    }
  }
};
</script>
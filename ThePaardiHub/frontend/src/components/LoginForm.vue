<template>
  <div class="box login">
    <div class="box-header login">
      <div class="title login">Login here</div>
      <div v-bind:class="message">{{responseData}}</div>
    </div>
    <div class="box-body login">
        <input v-model="formData.email" type="email" placeholder="Email">
        <input v-model="formData.password" type="password" placeholder="Password">
        <a class="forgot" v-on:click="showReset()">Forgot your password?</a>
        <button type="button" @click="postForm()">Login</button>
    </div>
    <div class="box-footer login">
      
      <a class="login" v-on:click="showRegister()">Create an account</a>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "login-form",
  data() {
    return {
      responseData: "",
      message: "error",
      formData: {
        email: "",
        password: ""
      }
    };
  },
  methods: {
    showReset() {
      this.$parent.$parent.showReset();
    },
    showRegister() {
      this.$parent.$parent.showRegister();
    },
    postForm() {
      axios
        .post("/login", {
          email: this.formData.email,
          password: this.formData.password
        })
        .then(response => {
          this.message = "valid";
          this.responseData = response.data
          console.log("Valid response");
          console.log(JSON.stringify(response.data));
        })
        .catch(e => {
          this.message = "error";
          this.responseData = e.response.data;
          console.log("Invalid response");
          console.log(JSON.stringify(e));
        });
    }
  }
};
</script>
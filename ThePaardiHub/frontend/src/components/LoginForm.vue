<template>
  <div class="box login">
    <div class="box-header login">
      <div class="title login">Login here</div>
      <div class="error">{{responseData}}</div>
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
          email: this.$data.email,
          password: this.$data.password
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
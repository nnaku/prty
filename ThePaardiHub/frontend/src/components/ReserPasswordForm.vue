<template>
  <div class="registeration box">
    <div class="box-header register">
      <div class="title register">Reset your password</div>
      <div class="error">{{responseData}}</div>
    </div>
    <div class="box-body register">
        <input v-model="formData.email" type="email" placeholder="Email">
        <button type="button" @click="postForm()">Submit</button>
    </div>
    <div class="box-footer register">
      <a class="forgot" v-on:click="showLogin()">Back to login</a>
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
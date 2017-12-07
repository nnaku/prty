<template>
  <div class="box login">
    <div class="box-header login">
      <div class="title login">Login here</div>
      <div v-bind:class="status">{{responseMessage}}</div>
    </div>
    <div class="box-body login">
        <input v-model="data.formData.email" type="email" placeholder="Email"> <span>*</span>
        <input v-model="data.formData.password" type="password" @keyup.enter="postForm()" placeholder="Password"> <span>*</span>
        <router-link class="routerLink" v-bind:to="'/reset'">Forgot your password?</router-link>
        <button type="button" @keyup.enter="postForm()" @click="postForm()">Login</button>
    </div>
    <div class="box-footer login">
      <router-link class="routerLink" v-bind:to="'/register'">Create an account</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "login-form",
  data() {
    return {
      responseMessage: "",
      status: "ERROR",
      data: {
        formData: {
          email: "",
          password: "",
          remember: "false"
        }
      },
      rememberMe: true
    };
  },
  methods: {
    postForm() {
      var redirect = this.$auth.redirect();
      this.$auth
        .login({
          data: this.data.formData,
          rememberMe: this.data.remember,
          fetchUser: false,
          redirect: { name: redirect ? redirect.from.name : "Games" }
        })
        .then(
          () => {
            this.$modal.hide("loginRegisterFormModal");
          },
          res => {
            console.log(JSON.stringify(res.response.data.message));
            this.status = res.response.data.status;
            this.responseMessage = res.response.data.message;
          }
        );
    }
  }
};
</script>

<style>
@import "../css/forms.css";
</style>
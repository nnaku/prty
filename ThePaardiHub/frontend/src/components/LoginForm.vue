<template>
  <div class="box login">
    <div class="box-header login">
      <div class="title login">Login here</div>
      <div v-bind:class="status">{{responseMessage}}</div>
    </div>
    <div class="box-body login">
        <input v-model="data.formData.email" type="email" placeholder="Email">
        <input v-model="data.formData.password" type="password" placeholder="Password">
        <router-link class="routerLink" v-bind:to="'/reset'">Forgot your password?</router-link>
        <button type="button" @click="postForm()">Login</button>
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
          email: "test@test.com",
          password: "Passu123",
          remember: "false"
        }
      },
      rememberMe: true
    };
  },
  mounted() {
    console.log(this.$auth.redirect());
  },
  methods: {
    showReset() {
      this.$parent.$parent.showReset();
    },
    showRegister() {
      this.$parent.$parent.showRegister();
    },
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
      /**
        .then(response => {
          this.status = response.data.status;
          this.responseMessage = response.data.message;
          // console.log(JSON.stringify(response.data));
          
        })
        .catch(e => {
          // this.message = "error";
          // this.responseMessage = e.response.message;
          // console.log(JSON.stringify(e.response.data));
        });
        */
    }
  }
};
</script>

<style>
@import '../css/forms.css';
</style>
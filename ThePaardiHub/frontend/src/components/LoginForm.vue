<template>
  <div class="box login">
    <div class="box-header login">
      <!-- Messages are from locale/locales.json -->
      <div class="title login">{{ $t('message.loginheader') }}</div>
      <div v-bind:class="status">{{responseMessage}}</div>
    </div>
    <div class="box-body login">
        <input v-model="data.formData.email" type="email" :placeholder="$t('message.email')"> <span>*</span>
        <input v-model="data.formData.password" type="password" @keyup.enter="postForm()" :placeholder="$t('message.password')"> <span>*</span>
        <router-link class="routerLink" v-bind:to="'/reset'">{{ $t('message.forgotpw') }}</router-link>
        <button type="button" @keyup.enter="postForm()" @click="postForm()">{{ $t('message.loginButton') }}</button>
    </div>
    <div class="box-footer login">
      <router-link class="routerLink" v-bind:to="'/register'">{{ $t('message.createaccount') }}</router-link>
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

<style scoped>
@import "../css/forms.css";
</style>
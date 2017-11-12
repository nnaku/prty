<template>

  <!-- login, register and password reset forms in this modal -->
  <modal class="LRFM" name="loginRegisterFormModal" 
        :width="'85%'"
        :height="'auto'"
        :pivotY="0.2"
        @before-open="beforeOpen"
        @before-close="beforeClose">

    <!-- if login boolean true in header template, draw this form -->
    <LoginForm v-show="this.$parent.form === 'login'"/>

    <!-- if register boolean true in header template, draw this form -->
    <RegisterForm v-show="this.$parent.form === 'register'"/>

    <!-- if reset boolean true in header template, draw this form -->
    <ReserPasswordForm v-show="this.$parent.form === 'reset'"/>

  </modal>
  
</template>

<script>
import axios from "axios";
import RegisterForm from "@/components/RegisterForm";
import LoginForm from "@/components/LoginForm";
import ReserPasswordForm from "@/components/ReserPasswordForm";

export default {
  name: "LoginRegisterModal",
  components: {
    RegisterForm,
    LoginForm,
    ReserPasswordForm
  },
  methods: {
    showLogin() {
      this.$parent.showLogin();
    },
    showRegister() {
      this.$parent.showRegister();
    },
    showReset() {
      this.$parent.showReset();
    }
  }
};
</script>

<style>
.box > .box-header > .title {
  text-align: center;
  margin: 3% auto 4% auto;
  font-size: 1.5em;
  color: #db6e2d;
}
.box > .box-header > .error {
  text-align: center;
  margin: 3% auto 4% auto;
  font-size: 1.5em;
  color: #8db9b0;
  background-color: Tomato;
}
.box > .box-header > .valid {
  text-align: center;
  margin: 3% auto 4% auto;
  font-size: 1.5em;
  color: #8db9b0;
  background-color: MediumSeaGreen;
}
.box > .box-body > input {
  width: 50%;
  height: 2.7em;
  display: block;
  margin: 2% auto;
  text-align: center;
  font-size: 1em;
}
.box > .box-body > button {
  display: block;
  margin: 1em auto;
  min-width: 15%;
  padding: 1em;
  height: 100%;
  border: none;
  background-color: #8db9b0;
  font-size: 1.1em;
}
.box > .box-footer > a,
.box > .box-body > a {
  display: block;
  text-align: center;
  font-size: 0.8em;
  color: grey;
  /* text-decoration: underline; */
  cursor: pointer;
}

@media screen and (min-width: 920px) {

  .box > .box-body > button {
    width: 15%;
    padding: 1em;
    font-size: 1em;
  }

}

@media screen and (max-width: 790px) {

  .box > .box-body > button {
    width: 25%;
    padding: 1em;
    font-size: 1em;
  }

}

</style>
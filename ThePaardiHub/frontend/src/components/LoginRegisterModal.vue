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
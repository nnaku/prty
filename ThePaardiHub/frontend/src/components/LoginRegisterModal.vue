<template>
  <!-- login, register and passeord reser forms in this modal -->
  <modal name="loginRegistesFormModal">
    <!-- if login boolean true in header template, draw this form -->
    <div v-show="this.$parent.login" class="box login">
      <div class="box-header login">
        <div class="partition-title login">Login</div>
      </div>
      <div class="box-body login">
        <form class="login" autocomplete="false">
          <input class="login" id="login-email" placeholder="Email" type="text" data="">
          <input class="login" id="login-password1" placeholder="Password" type="password">
          <a class="login" v-on:click="lostPassword()"href="#">Forgot your password?</a>
        </form>
      </div>
      <div class="box-footer login">
        <div class="button-set login">
          <button class="login" id="login-btn">Login</button>
          <a class="login" v-on:click="toRegister()"href="#">Create an account</a>
        </div>
      </div>
    </div>
    <!-- if register boolean true in header template, draw this form -->
    <div v-show="this.$parent.register" class="box register">
      <div class="box-header register">
        <div class="partition-title register">Register</div>
      </div>
      <div class="box-body register">
        <form class="register" autocomplete="false">
          <input class="register" id="register-firstname" placeholder="Firstname" type="text" data="">
          <input class="register" id="register-lastname" placeholder="Lastname" type="text">
          <input class="register" id="register-username" placeholder="Username" type="text">
          <input class="register" id="register-email" placeholder="Email" type="text">
          <input class="register" id="register-password1" placeholder="Password" type="password">
          <input class="register" id="register-password2" placeholder="Password" type="password">
        </form>
      </div>
      <div class="box-footer register">
        <div class="button-set register">
          <button class="register" id="register-btn">Submit</button>
          <a class="forgot" v-on:click="toLogin()" href="#">Already have an account</a>
        </div>
      </div>
    </div>
    <!-- if reset boolean true in header template, draw this form -->
    <div v-show="this.$parent.reset" class="box forgot">
      <div class="box-header forgot">
        <div class="partition-title forgot">Reser your password</div>
      </div>
      <div class="box-body forgot">
        <form class="forgot" autocomplete="false">
          <input class="forgot" id="forgot-email" placeholder="Email" type="text" data="">
        </form>
      </div>
      <div class="box-footer forgot">
        <div class="button-set forgot">
          <button class="forgot" id="forgot-btn">Confirm</button>
          <a class="forgot" v-on:click="toLogin()" href="#">Back to login</a>
        </div>
      </div>
    </div>

  </modal>
</template>

<script>
export default {
  name: "LoginRegisterModal",
  data(){
    return{
      login: "login",
      register:"register",
      reset: "reset"
    }
  },
  methods: {
    toLogin() {
      this.text = "";
      this.$parent.showLogin();
    },
    toRegister() {
      this.$parent.showRegister();
    },
    lostPassword() {
      this.$parent.showReset();
    }
  }
};
</script>
<style scoped>

</style>

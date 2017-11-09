<template>
  <div class="appheader">
    <div class="topnav" id="myTopnav">
      <router-link class="brand toLeft item" v-bind:to="'/'">PRTY.fi</router-link></a>

      
        <!--
        v-if="this.$parent.authorized" for user who as logged in
        and v-else for this.$parent.authorized users
        -->
      <router-link class="toLeft item" v-bind:to="'/games'" v-if="this.$parent.authorized">My games</router-link>
      <router-link class="toLeft item" v-bind:to="'/about'" v-else>About</router-link>
      <a class="toRight item" v-bind:to="'/logout'" v-on:click="logout()"  v-if="this.$parent.authorized">Logout</a>
      <a class="toRight item" v-on:click="showLogin()" v-else>Login</a>
      <router-link class="toRight item" v-bind:to="'/profile'" v-if="this.$parent.authorized">Profile</router-link>
      <a class="toRight item" v-on:click="showRegister()" v-else >Register</a>
    </div>
    <LoginRegisterModal/>
  </div>
  
</template>

<script>
import LoginRegisterModal from "./LoginRegisterModal";

export default {
  name: "appheader",
  components: {
    LoginRegisterModal
  },
  data() {
    return {
      login: false,
      register: false,
      reset: false
    };
  },
  methods: {
    showLogin() {
      this.login = true;
      this.register = false;
      this.reset = false;
      this.$modal.show("loginRegistesFormModal");
      this.$parent.login();
    },
    showRegister() {
      this.login = false;
      this.register = true;
      this.reset = false;
      this.$modal.show("loginRegistesFormModal");
    },
    showReset() {
      this.login = false;
      this.register = false;
      this.reset = true;
      this.$modal.show("loginRegistesFormModal");
    },
    logout(){
      this.$parent.authorized = false;
    }
  }
};
</script>

<style scoped>
/* Add a black background color to the top navigation */
.topnav {
  background-color: #00ccff;
  overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 20px 20px 15px 15px;
  text-decoration: none;
  font-size: 1.3em;
}

/* Change the color of links on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.toLeft {
  float: left;
}
.toRight {
  float: right;
}
</style>

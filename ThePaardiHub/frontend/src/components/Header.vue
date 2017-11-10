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
      <a class="toRight item" v-on:click="logout()" v-if="this.$parent.authorized">Logout</a>
      <a class="toRight item" v-on:click="showLogin()" v-else>Login</a>
      <router-link class="toRight item" v-bind:to="'/user'" v-if="this.$parent.authorized">Profile</router-link>
      <a class="toRight item" v-on:click="showRegister()" v-else >Register</a>
    </div>
    <LoginRegisterModal/>
  </div>
  
</template>

<script>
import LoginRegisterModal from "./LoginRegisterModal";
export default {
  name: "appheader",
  data() {
    return {
      form: "login"
    };
  },
  components: {
    LoginRegisterModal
  },
  methods: {
    showLogin() {
      this.form = "login";
      this.$modal.show("loginRegisterFormModal");
    },
    showRegister() {
      this.form = "register";
      this.$modal.show("loginRegisterFormModal");
    },
    showReset() {
      this.form = "reset";
      this.$modal.show("loginRegisterFormModal");
    },
    logout() {
      this.$parent.authorized = false;
    }
  }
};
</script>

<style scoped>
/* Add a black background color to the top navigation */
.topnav {
  background-color: #db6e2d;
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
.router-link-active {
  background-color: #ed8e57;
}
</style>

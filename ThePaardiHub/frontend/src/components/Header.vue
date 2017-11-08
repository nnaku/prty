<template>
  <div class="appheader">
    <div class="topnav" id="myTopnav">
      <a class="brand toLeft" href="/">PRTY.fi</a>
        <!--
        v-if="this.$parent.authorized" for user who as logged in
        and v-else for this.$parent.authorized users
        -->
      <a class="toLeft" v-if="this.$parent.authorized" href="#mygames">My games</a>
      <a class="toLeft" v-else href="/about">About</a>
      <a class="toRight" v-if="this.$parent.authorized" href="#logout">Logout</a>
      <a class="toRight" v-else href="#" @click="showLogin()">Login</a>
      <a class="toRight" v-if="this.$parent.authorized" href="#profile">Profile</a>
      <a class="toRight" v-else href="#" @click="showRegister()">Register</a>
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

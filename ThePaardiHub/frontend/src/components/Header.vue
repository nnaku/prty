<template>
  <div class="appheader">
    <div class="topnav" id="myTopnav">
      <!-- Messages are from locale/locales.json -->
      <router-link class="brand toLeft item" v-bind:to="'/'"> {{ $t('message.brand') }} </router-link>
      <router-link class="toLeft item" v-bind:to="'/games'" v-if="this.$auth.check()">{{ $t('message.mygames') }}</router-link>
      <router-link class="toLeft item" v-bind:to="'/about'" v-else>{{ $t('message.about') }}</router-link>
      <a class="toRight item" v-if="this.$auth.check()" v-on:click="logout()">{{ $t('message.logout') }}</a>
      <router-link class="toRight item" v-bind:to="'/login'" v-else>{{ $t('message.login') }}</router-link>
      <router-link class="toRight item" v-bind:to="'/user'" v-if="this.$auth.check()">{{ $t('message.profile') }}</router-link>
      <router-link class="toRight item" v-bind:to="'/register'" v-else >{{ $t('message.register') }}</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "appheader",
  data() {
    return {
      form: "login"
    };
  },
  methods: {
    logout() {
      // Data object is passed directly to http method.
      // Accepts redirect parameter which is passed directly to router.
      // Accepts makeRequest parameter which must be set to true to send request to api. Otherwise the logout just happens locally by deleting tokens.
      this.$auth.logout({
        makeRequest: false,
        params: {}, // data: {} in axios
        success: function() {
          this.$forceUpdate()
        },
        error: function() {},
        redirect: "/logout"

        // etc...
      });
    }
  }
};
</script>

<style scoped>

/* Add rules for brand: background color does not be changed while hover or active */
.topnav a:first-child {
  background-color: #8DB9B0; 
}
.topnav a:first-child:active {
  background-color: #8DB9B0; 
}
.topnav a:first-child:hover {
  background-color: #8DB9B0;
  color: #ffffff;
}
/* Add a background color to the top navigation */
.topnav {
  background-color: #db6e2d;
  overflow: hidden;
  font-size: 19px;
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
/* Change the color of links on hover and active */
.topnav a:hover {
  background-color: #ed8e57;
  color: #ffffff;
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

@media only screen and (max-width: 768px) {
  .topnav {
    font-size: 13px;
  }
}

</style>

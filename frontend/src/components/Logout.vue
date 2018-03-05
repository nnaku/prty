<template>
  <div id="logout">
    <div class="links">
      <!-- Messages are from locale/locales.json -->
      <router-link class="routerToAbout" v-bind:to="'/login'">{{$t('message.loginagain')}}</router-link>
      <p></p>
      <router-link class="routerToHome" v-bind:to="'/'">{{$t('message.gomainpage')}}</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "logout",
  data() {
    return {};
  },
  mounted() {
  },
  methods: {}
};
</script>

<style>

  .links {
    margin-top: 3em;
  }
  .routerToAbout, .routerToHome {
    font-size: 25px;
    color:#8db9b0;
    font-weight: 540;
    text-decoration: none;
  }
</style>
<template>
  <div id="app">
    <appHeader/>
    <router-view/>
    <appFooter/>
  </div>
</template>

<script>
import appHeader from "./components/Header";
import appFooter from "./components/Footer";

export default {
  name: "app",
  components: {
    appHeader,
    appFooter
  },
  data() {
    return {
      authorized: true
    };
  }
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  height: 100%;
}
#app {
  min-height: 100%;
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>






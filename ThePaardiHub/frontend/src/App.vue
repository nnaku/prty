<template>
  <div id="app">
    <appHeader/>
    <router-view class="main-container"/>
    <appFooter/>
  </div>
  
</template>

<script>
import appHeader from "./components/Header";
import appFooter from "./components/Footer";

export default {
  name: "app",
  metaInfo : {
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1'}
    ]
  },
  components: {
    appHeader,
    appFooter
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
.main-container{
  padding-bottom: 50px;
}
#app {
  min-height: 100%;
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

@media only screen and (max-width: 768px) {
  html,body {
    font-size: 0.9em;
  }
}
</style>






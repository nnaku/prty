<template>
  <div id="app">
     <!-- Message is from locale/locales.json -->
    <div class="fullscreen-button" v-if="fullscreen" ><a @click="fullScreenOff()">{{$t('message.fullscreenOff')}}</a></div>
    <appHeader v-if="!fullscreen"/>
    <router-view class="main-container"/>
    <appFooter v-if="!fullscreen"/>
  </div>
</template>

<script>
import appHeader from "./components/Header";
import appFooter from "./components/Footer";


export default {
  data() {
    return {
      fullscreen: false
    };
  },
  methods: {
    fullScreenOn() {
      this.fullscreen = true;
    },
    fullScreenOff() {
      this.fullscreen = false;
    }
  },
  metaInfo: {
    meta: [
      { charset: "utf-8" },
      { name: "viewport", content: "width=device-width, initial-scale=1" }
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
.main-container {
  padding-bottom: 50px;
}
#app {
  min-height: 100%;
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
.fullscreen-button {
  top: 0;
  height: 25px;
  line-height: 20px;
  margin-left: auto;
  margin-right: auto;
  background-color: #db6e2d;
  color: #f2f2f2;
  border-radius: 0 0 25px 25px;
  cursor: pointer;
}
@media only screen and (max-width: 768px) {
  html,
  body {
    font-size: 0.9em;
  }


}
</style>






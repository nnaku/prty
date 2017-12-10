<template>
  <div id="app">
     <!-- Message is from locale/locales.json -->
    <appHeader/>
    <button class="changeLanguage"><a v-on:click="changeLanguage()">{{$t('message.language')}}</a></button>
    <router-view class="main-container"/>
    <appFooter/>
  </div>
</template>

<script>
import appHeader from "./components/Header";
import appFooter from "./components/Footer";
import {i18n} from './main.js';

export default {
  name: "app",
  methods:{
    changeLanguage(){
      if(i18n.locale === "fr"){
        i18n.locale = 'en'
      }else{
        i18n.locale = 'fr'
      }
    }
  },
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
.changeLanguage {
  text-align: right;
  float: right;
  height: 2em;
  background-color: #8DB9B0;
  font-size: 13px;
  margin-top: 0.3em;
  margin-right: 0.3em;
}

@media only screen and (max-width: 768px) {
  html,body {
    font-size: 0.9em;
  }
}
</style>






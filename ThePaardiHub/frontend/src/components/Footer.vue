<template>
  <div class="appfooter">
    <div class="footer">
      <p><a v-on:click="changeLanguage()">{{$t('message.language')}}</a></p> <!-- Aku, tähän contact-modali! -->
      
    </div> 
  </div>
  
</template>

<script>

import {i18n} from '../main.js'

export default {
  name: "appfooter",
  methods:{
    changeLanguage(){
      if(i18n.locale === "fr"){
        i18n.locale = 'en'
      }else{
        i18n.locale = 'fr'
      }

    }
  }
};
</script>

<style>
.appfooter {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #8db9b0;
  overflow: hidden;
  color: white;
  text-align: center;
  height: 40px;
}

.footer {
  text-align: center;
}
</style>

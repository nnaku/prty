<template>
  <div class="appfooter">
    <!-- Message is from locale/locales.json -->
    <div class="footer">
      <p>{{ $t('message.footer') }} </p><p class="change-lang"><a v-on:click="changeLanguage()">{{$t('message.language')}}</a></p>
    </div> 
  </div>
</template>

<script>
import {i18n} from "../main.js";

export default {
  name: "appfooter",
  methods: {
    changeLanguage() {
      if (i18n.locale === "fr") {
        i18n.locale = "en";
      } else {
        i18n.locale = "fr";
      }
    }
  }
};
</script>


<style scoped>
.changeLanguage {
  text-align: right;
  float: right;
  height: 2em;
  background-color: #8db9b0;
  font-size: 13px;
  margin-top: 0.3em;
  margin-right: 0.3em;
}

.appfooter {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #8db9b0;
  overflow: hidden;
  color: white;
  height: 40px;
  display: table;
}
.change-lang{
  float: right;
}
p {
  float: left;
  vertical-align: middle;
  display: table;
  padding-left: 1em;
  font-size: 15px;
  font-weight: bold;
}
</style>

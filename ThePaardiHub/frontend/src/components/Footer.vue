<template>
  <div class="appfooter">
    <!-- Message is from locale/locales.json -->
    <div class="footer">
      <p>{{ $t('message.footer') }}</p>
    </div> 
  </div>
</template>

<style>
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

p {
  vertical-align: middle;
  display: table;
  padding-left: 1em;
  font-size: 15px;
  font-weight: bold;
}
</style>

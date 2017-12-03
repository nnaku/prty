<template>
  <div class="appfooter">
    <div class="footer">
      <p><a v-on:click="showRegister()">Contact</a></p> <!-- Aku, tähän contact-modali! -->
    </div> 
  </div>
  
</template>

<script>
export default {
  name: "appfooter"
};
</script>

<style>
.appfooter {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #8DB9B0;
  overflow: hidden;
  color: white;
  text-align: center;
  height: 50xp;
}

.footer {
  text-align: center;
}
</style>

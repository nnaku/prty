<script src="./account.js"></script>
<style src="../Styles/styles.css"></style>

<template>
  <div id="Account">

  </div>
</template>


<script>
  export default {
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

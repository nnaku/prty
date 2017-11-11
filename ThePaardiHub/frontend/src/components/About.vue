<template>
  <div id="about">
     <div class="abouthost">
      <p class="title">Host</p>
      <p>As a host you can...</p>
     </div>
     <div class="aboutuser">
      <p class="title">User</p>
      <p>As a user you can ...</p>
     </div>
  </div>
</template>

<script>

export default{
  name: "about"
};
</script>

<style scoped>

  .abouthost, .aboutuser {
    background-color: #8DB9B0;
    width: 40%;
    display: inline-block;
    margin-top: 3%;
    height: 100%;
    padding-top: 0.5em;
    color: white;
  }

  .title {
    font-weight: bold;
    text-transform: uppercase;
  }

@media screen and (max-width: 790px) {

  .abouthost, .aboutuser {
    display: block;
    margin: 3% auto 3%;
    width: 80%;
    height: 100%;
  }
}

</style>




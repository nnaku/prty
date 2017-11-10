<template>
  <div class="home">
    <input class="gameToken" id="token" placeholder="Game token">
    <button class="gameToken" id="token-btn">Go!</button>
  </div>
</template>

<script>
export default {
  name: "Home"
};
</script>

<style scoped>
input.gameToken {
  width: 50%;
  height: 6em;
  margin: 0 auto;
  margin-top: 20%;
  display:block;
  font-size: 100%;
  text-align: center;
  border: none;
  box-shadow: 0 0 0.1em 0 grey;
}
button.gameToken {
  width: 10%;
  height: 3em;
  margin-top: 1em;
  font-size: 100%;
  background-color: #8DB9B0;
  border: none;
}
/*button.gameToken:hover {
  background-color: #548E90;
}*/
</style>  
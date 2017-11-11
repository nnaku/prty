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
  width: 60%;
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
  width: 15%;
  height: 100%;
  margin-top: 1em;
  padding: 1em;
  font-size: 100%;
  background-color: #8DB9B0;
  border: none;
}


@media screen and (min-width: 790px) and (max-width: 1020px) {

  button.gameToken {
    width: 20%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  } 
  
  input.gameToken {
  width: 75%;
  height: 6em;
  margin: 0 auto;
  margin-top: 20%;
  display:block;
  font-size: 100%;
  text-align: center;
  border: none;
  
  }

}

@media screen and (max-width: 790px) {

  button.gameToken {
    width: 20%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  }
  input.gameToken {
  width: 75%;
  height: 6em;
  margin: 0 auto;
  margin-top: 20%;
  display:block;
  font-size: 100%;
  text-align: center;
  border: none;
  
  }

}
@media screen and (max-width: 490px) {

  button.gameToken {
    width: 30%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  }

  input.gameToken {
  width: 90%;
  height: 6em;
  margin: 0 auto;
  margin-top: 20%;
  display:block;
  font-size: 100%;
  text-align: center;
  border: none;
  
  }
}
/*button.gameToken:hover {
  background-color: #548E90;
}*/
</style>  
<template>
  <div class="home">
    <!-- Messages are from locale/locales.json -->
    <figure>
      <img src="../images/prty_image.png" alt="PRTY.fi">
    </figure>
    <input class="gameToken" id="token" :placeholder="$t('message.gametoken')">
    <button class="gameToken" id="token-btn"> {{ $t('message.go') }}</button>
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style scoped>
input.gameToken {
  width: 60%;
  height: 3em;
  margin: 0 auto;
  margin-top: 25px;
  display:block;
  font-size: 190%;
  text-align: center;
  border: 1px solid #8DB9B0;
  border-radius: 1em;
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

input.gameToken:focus {
  outline: none;
}

img {
  width: 30%;
  height: 100%;
  margin-top: 25px;
}
figure {
  padding-top: 2.5em;
}

@media only screen and (max-width: 768px) {

  button.gameToken {
    width: 30%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  }

  input.gameToken {
    width: 90%;
    height: 3em;
    margin: 0 auto;
    margin-top: 25px;
    display:block;
    font-size: 230%;
    text-align: center;
    border: none;
  
  }

  img {
    width: 60%;
    height: 100%;
    margin-top: 25;
  }
}

</style>  
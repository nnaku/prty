<template>
  <div class="home">
    <!-- Messages are from locale/locales.json -->
    <figure>
      <img src="../images/prty_image.png" alt="PRTY.fi">
    </figure>
    <p v-if="invalidToken">{{ $t('message.invalidToken') }}</p>

    <div v-if="!showUsernameForm">
      <input v-model="token" class="gameToken" id="token" :placeholder="$t('message.gametoken')">
      <button class="gameToken" @click="validateToken" id="token-btn"> {{ $t('message.go') }}</button>
    </div>
    <div v-else>
      <input v-model="username" class="gameToken" id="token" :placeholder="$t('message.username')">
      <button class="gameToken" @click="joinLobby" id="token-btn"> {{ $t('message.join') }}</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      token: "",
      username: "",
      invalidToken: false,
      showUsernameForm: false,
      errors:[]
    };
  },
  methods: {
    validateToken() {
      axios
        .post("/lobbieexist", {
          key: this.token
        })
        .then(response => {
          if (response.data.lobbieExist) {
            this.showUsernameForm = true;
            this.invalidToken = false;
          } else {
            this.invalidToken = true;
          }
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    joinLobby() {
      axios
        .post("/addplayer", {
          key: this.token,
          name: this.username
        })
        .then(response => {
          var playerInfo = {
            gameToken: this.token,
            name: this.username,
            playerID: response.data.playerID,
            receiveAddress: response.data.receiveAddress,
            sendAddress: response.data.sendAddress
          };
          this.$cookie.delete("playerInfo");
          this.$cookie.set("playerInfo", JSON.stringify(playerInfo), 1);
          this.$router.push('/game/'+this.token);
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  },
  mounted() {
    // if player has played game in 24h hours, app offers last username as default.
    this.username = JSON.parse(this.$cookie.get("playerInfo")).name;
    if(this.$route.query.token){
      this.$parent.fullscreen = true;
      this.token=this.$route.query.token
    this.validateToken()
    }
  }
};
</script>

<style scoped>
input.gameToken {
  width: 60%;
  height: 3em;
  margin: 0 auto;
  margin-top: 25px;
  display: block;
  font-size: 190%;
  text-align: center;
  border: 1px solid #8db9b0;
  border-radius: 1em;
}
button.gameToken {
  width: 15%;
  height: 100%;
  margin-top: 1em;
  padding: 1em;
  font-size: 100%;
  background-color: #8db9b0;
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
    display: block;
    font-size: 150%;
    text-align: center;
  }

  img {
    width: 60%;
    height: 100%;
    margin-top: 25;
  }
}
</style>  
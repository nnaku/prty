<template>
  <div id="game">
      <div class="fullscreen-button" v-if="!this.$parent.fullscreen"><a  @click="fullScreenOn()">{{$t('message.fullscreenOn')}}</a></div>
      <!-- waiting players -->
      <div class="game-ready" v-if="body.state === 'GAME_READY'">
        <p class="game-status">{{$t('message.waitingPlayers')}}</p>
      </div>

      <!-- display questions options -->
      <div class="game-answers-options" v-else-if="body.state == 'ASKING_QUESTION'">
        <div class="player-answers" v-if="answered">{{$t('message.yourAnswer')}}</div>
        <div class="game-answer" v-bind:class="{ answer: !answerId[index] && answered}" :id="'index_'+index" v-for="(option,index) in body.options":key="index" :disabled="answered">
          <a v-on:click.once="answer(option,index)" v-html="option"></a>
        </div>
      </div>

      <!-- waiting next question -->
      <div class="game-wait-answer"v-else-if="body.state == 'CHANING_QUESTION'">
        <p class="game-status">{{$t('message.waitingQuestion')}}</p>
      </div>

      <!-- game end -->
      <div class="game-end" v-else-if="body.state == 'GAME_FINISHED'">
        <p class="game-status">{{$t('message.gameEnd')}}</p>
      </div>

      <!-- bad game state -->
      <div class="game-not-found" v-else>
        <p class="game-status">{{$t('message.noGame')}}</p>
      </div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "game",
  data() {
    return {
      received_messages: {},
      answered: false,
      answerId: [false, false, false, false],
      body: {
        state: null
    }
    };
  },
  methods: {
    fullScreenOn() {
      this.$parent.fullscreen = true;
    },
    answer(option, index) {
      this.answered = true;
      this.answerId[index] = true;
      this.send(option);
    },
    send(option) {
      var data = {
        id: this.playerInfo.playerID,
        answer: option,
        leave: this.playerLeave
      };
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send(
          this.playerInfo.receiveAddress,
          JSON.stringify(data),
          {}
        );
      }
    },
    connect() {
      this.socket = new SockJS("/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          this.stompClient.subscribe(this.playerInfo.sendAddress, tick => {
            this.received_messages = tick;
          });
        },
        error => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    }
  },
  beforeMount() {
    if (JSON.parse(this.$cookie.get("playerInfo"))) {
      this.playerInfo = JSON.parse(this.$cookie.get("playerInfo"));
    } else {
      this.$router.push("/");
    }
  },
  mounted() {
    this.connect();
    this.$parent.fullscreen = true;
  },
  watch: {
    received_messages: function(data) {
      this.body = JSON.parse(data.body);
      if (this.body.state == 'CHANING_QUESTION') {
        this.answered = false;
        this.answerId = [false, false, false, false];
      }
    }
  }
};
</script>

<style scoped>
.game-not-found > .game-status {
  margin: 200px auto;
  font-size: 80px;
}

.answer {
  display: none !important;
}

.game-wait-answer,
.game-ready {
  max-width: 100%;
  background-image: url("../images/waiting.gif");
  background-size: auto;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  height: 640px;
}

.game-answers-options {
  width: 80%;
  margin: 150px auto;
}

.game-answers-options > .game-answer {
  display: inline-block;
  margin: 15px;
  width: 40%;
  height: 150px;
  line-height: 150px;
}

.game-answers-options > .game-answer > a {
  font-size: 30px;
  text-align: center;
  display: block;
  height: 100%;
  line-height: inherit;
  width: 100%;
  text-decoration: none;
}

.game-answers-options > #index_0 {
  background-color: Tomato;
}
.game-answers-options > #index_1 {
  background-color: MediumSeaGreen;
}
.game-answers-options > #index_2 {
  background-color: Orange;
}
.game-answers-options > #index_3 {
  background-color: DodgerBlue;
}

@media only screen and (max-width: 767px) {
  #game {
    width: 100%;
  }
  .game-answers-options {
    margin: 50px 0;
    width: 100%;
  }

  .game-answer {
    width: 75% !important;
    height: 50px !important;
    margin: 15px !important;
    padding: 0px;
  }

  .game-answer > a {
    font-size: 1.4em !important;
    text-align: center;
    display: block;
    height: 50px;
    line-height: 50px !important;
    width: 100%;
    text-decoration: none;
  }
  .game-not-found > .game-status {
    margin-top: 150px;
    font-size: 350%;
  }
}
</style>

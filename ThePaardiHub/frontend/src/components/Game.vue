<template>
  <div id="game">

      <!-- dev data for debug -->
      <!--
      <p>{{body}}</p>
      <div v-for="(key,data,index) in body" :key="index">{{key}} : {{data}}</div>
      -->

      <!-- waiting players -->
      <div class="game-ready" v-if="body.state === 'GAME_READY'">
        <p class="game-status">{{$t('message.waitingPlayers')}}</p>
      </div>

      <!-- display questions options -->
      <div class="game-answers-options" v-else-if="body.state == 'ASKING_QUESTION'">
        <div class="game-answer" :id="index" v-for="(option,index) in body.options":key="index" :disabled="answered">
          <a v-on:click.once="answer(option)" v-html="option"></a>
        </div>
      </div>

      <!-- waiting next question -->
      <div v-else-if="body.state == 'CHANING_QUESTION'">
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
      body: {}
    };
  },
  methods: {
    answer(option) {
      this.answered = true;
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
  },
  watch: {
    received_messages: function(data) {
      this.body = JSON.parse(data.body);
    }
  }
};
</script>

<style scoped>
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

.game-answers-options > .game-answer:nth-child(1) {
  background-color: Tomato;
}
.game-answers-options > .game-answer:nth-child(2) {
  background-color: MediumSeaGreen;
}
.game-answers-options > .game-answer:nth-child(3) {
  background-color: Orange;
}
.game-answers-options > .game-answer:nth-child(4) {
  background-color: DodgerBlue;
}
.game-answers-options > .game-answer:visited {
  color: red;
}
</style>

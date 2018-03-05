<template>
  <div id="lobby">
    <div class="fullscreen-button" v-if="!this.$parent.fullscreen"><a  @click="fullScreenOn()">{{$t('message.fullscreenOn')}}</a></div>
      <!-- waiting players -->
      <div class="game-ready" v-if="body.state === 'GAME_READY'">
        <h1 class="game-name">{{body.gameName}}</h1>
        <p class="game-desc">{{body.gameDescription}}</p>
        <table class="game-info">
          <tr>
            <td class="left">{{$t('message.gameType')}}</td>
            <td class="right">Quiz</td>
          </tr>
          <tr>
            <td class="left">{{$t('message.lobbyKey')}}</td>
            <td class="right">{{body.lobbyKey}}</td>
          </tr>
          <tr>
            <td class="left">{{$t('message.author')}}</td>
            <td class="right">{{body.author}}</td>
          </tr>
        </table>
        <div class="buttons">
          <div class="start"><a @click="start()">{{$t('message.startGame')}}</a></div>
          <div class="close"><a @click="quit()">{{$t('message.closeLobby')}}</a></div>
        </div>
        <p class="game-status">{{$t('message.waitingPlayers')}}</p>
        <p>{{$t('message.playerConnected')}}</p>
        <ul class="player-list-small">
          <li class="player" v-for="(player,index) in body.players" :key="index">
            {{player.name}}
          </li>
        </ul>
      </div>

      <!-- display questions options -->
      <div class="game-show-question" v-else-if="body.state == 'ASKING_QUESTION'">
        <p class="timer">{{body.timer}}</p>
        <p class="timer-text">{{$t('message.timeLeft')}}</p>
        <p class="question" v-html="body.question"></p>
        <div class="game-answer" :id="'index_'+index" v-for="(option,index) in body.options":key="index">
          <p class="colored" v-html="option"></p>
        </div>
      </div>

      <!-- waiting next question -->
      <div class="game-scores"v-else-if="body.state == 'CHANING_QUESTION'">
        <p class="timer">{{body.timer}}</p>
        <p class="timer-text">{{$t('message.waitingQuestion')}}</p>
        <ul class="player-list">
          <li class="player" v-for="(player,score,index) in orderedUsers " :key="index">
            {{player.name}} {{player.score}}
          </li>
        </ul>
      </div>

      <!-- game end -->
      <div class="game-end" v-else-if="body.state == 'GAME_FINISHED'">
        <p class="game-status">{{$t('message.gameEnd')}}</p>
        <ul class="player-list">
          <li class="player" :id="'index_'+index"  v-for="(player,score,index) in orderedUsers " :key="index">
            {{player.name}} {{player.score}}
          </li>
        </ul>
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
var _ = require("lodash");

export default {
  name: "lobby",
  computed: {
    orderedUsers: function() {
      return _.orderBy(this.body.players, "score", "desc");
    }
  },
  data() {
    return {
      receiveAddress: "/prty/game/host",
      sendAddress: "/lobby/host/show",
      received_messages: {},
      body: {
        state:null
      },
      send_message: null,
      connected: false,
      erros: []
    };
  },
  methods: {
    fullScreenOn() {
      this.$parent.fullscreen = true;
    },
    quit() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send(
          this.receiveAddress,
          JSON.stringify({
            startGame: false,
            getData: true,
            terminateLobby: true
          }),
          {}
        );
      }
    },
    refresh() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send(
          this.receiveAddress,
          JSON.stringify({
            startGame: false,
            getData: true,
            terminateLobby: false
          }),
          {}
        );
      }
    },
    start() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send(
          this.receiveAddress,
          JSON.stringify({
            startGame: true,
            getData: true,
            terminateLobby: false
          }),
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
          this.stompClient.subscribe(this.sendAddress, tick => {
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
  mounted() {
    this.$parent.fullscreen = true;
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
#lobby {
}
.buttons {
  margin: 10px auto;
  width: 45%;
}
.start,
.close {
  height: 50px;
  line-height: 50px;
  width: 47%;
  cursor: pointer;
}

.start {
  background-color: MediumSeaGreen;
  float: left;
}
.close {
  background-color: tomato;
  float: right;
}
.game-ready > .game-status {
  margin-top: 75px;
  padding: 0;
}
.game-not-found > .game-status {
  margin: 200px auto;
  font-size: 80px;
}

.game-ready,
.game-show-question,
.game-scores,
.game-end,
.game-not-found {
  width: 80%;
  margin: 0 auto;
}

.timer {
  font-size: 75px;
  margin: 0;
}
.timer-text {
  margin: 0;
  padding: 0;
}

.game-show-question > .question {
  font-size: 50px;
  margin: 10px;
  padding: 0;
}

ul {
  list-style-type: none;
  padding: 0px;
}
.game-info {
  margin: auto;
}
.game-info > tr > td {
  text-align: left;
  padding: 5px 10px;
}

.player-list-small {
  font-size: 30px;
}

.player-list > #index_0 {
  font-size: 130px;
}
.player-list > #index_1 {
  font-size: 90px;
}
.player-list > #index_3 {
  font-size: 70px;
}
.player-list > .player {
  font-size: 50px;
}

.game-end > .player-list > #index_0 {
  background-color: #d4af37;
}
.game-end > .player-list > #index_1 {
  background-color: #c0c0c0;
}
.game-end > .player-list > #index_2 {
  background-color: #cd7f32;
}

.game-answer {
  display: inline-block;
  margin: 15px;
  width: 40%;
  height: 150px;
  line-height: 150px;
}

.colored {
  margin: 0;
  padding: 0;
  font-size: 30px;
  text-align: center;
  display: block;
  height: 100%;
  line-height: inherit;
  width: 100%;
  text-decoration: none;
}
.game-answer:nth-child(4) {
  background-color: Tomato;
}
.game-answer:nth-child(5) {
  background-color: MediumSeaGreen;
}
.game-answer:nth-child(6) {
  background-color: Orange;
}
.game-answer:nth-child(7) {
  background-color: DodgerBlue;
}
</style>


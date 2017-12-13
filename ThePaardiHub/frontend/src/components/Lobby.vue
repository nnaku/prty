<template>
  <div id="lobby">
      <!-- waiting players -->
      <div class="game-ready" v-if="body.state === 'GAME_READY'">
        <h1>Lobby</h1>
        <p class="game-status">{{$t('message.waitingPlayers')}}</p>

        <button @click="send()">{{$t('message.startGame')}}</button>
        <button @click="quit()">{{$t('message.closeLobby')}}</button>
        <p>{{$t('message.lobbyKey')}}: {{body.lobbyKey}}</p>
        
        <h2 class="game-name">{{body.gameName}}</h2>
        <p class="game-desc">{{body.gameDescription}}</p>
        <p class="game-author">{{$t('message.author')}}: {{body.author}}</p>
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
        <div class="game-answer" :id="index" v-for="(option,index) in body.options":key="index">
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
          <li class="player" v-for="(player,score,index) in orderedUsers " :key="index">
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
import axios from "axios";
var _ = require("lodash");
import debugData from "../LobbyDebug.json";

export default {
  name: "lobby",
  props: {
    token: ""
  },
  computed: {
    orderedUsers: function() {
      return _.orderBy(this.body.players, "score", "desc");
    }
  },
  data() {
    return {
      received_messages: {},
      body: debugData[3],
      hostCommand: {
        startGame: true,
        getData: true,
        terminateLobby: false
      },
      send_message: null,
      connected: false,
      erros: []
    };
  },
  methods: {
    quit() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send(
          "/prty/game/host",
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
          "/prty/game/host",
          JSON.stringify({
            startGame: false,
            getData: true,
            terminateLobby: false
          }),
          {}
        );
      }
    },
    send() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send(
          "/prty/game/host",
          JSON.stringify(this.hostCommand),
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
          this.stompClient.subscribe("/lobby/host/show", tick => {
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
    this.$nextTick(function() {
      axios
        .post("/createlobby", { id: "1" })
        .then(response => {
          this.errors = [];
        })
        .catch(e => {
          this.errors.push(e);
        });
    });
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
  margin: 30px;
}

.game-not-found > .game-status{
  margin: 200px auto;
  font-size: 80px;
}

.game-ready,
.game-show-question,
.game-scores,
.game-end,
.game-not-found {
  width: 80%;
  margin: 30px auto;
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
}

.player-list-small{
  font-size: 30px;
}

.player-list > .player:nth-child(1) {
  font-size: 130px;
}
.player-list > .player:nth-child(2) {
  font-size: 90px;
}
.player-list > .player:nth-child(3) {
  font-size: 70px;
}
.player-list > .player {
  font-size: 50px;
}

.game-end > .player-list > .player:nth-child(1) {
  background-color: #d4af37;
}
.game-end > .player-list > .player:nth-child(2) {
  background-color: #c0c0c0;
}
.game-end > .player-list > .player:nth-child(3) {
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


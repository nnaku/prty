<template>
  <div id="lobby">
    <div>
        <h1>dev data</h1>
        <h2>Connected? {{connected}}</h2>
        <button @click="tickleConnection">{{connected ? 'disconnect' : 'connect'}}</button>
        <button @click="send">refresh</button>
        <p>{{received_messages}}</p>
    </div>
    <h1>Lobby {{ $route.params.token }}</h1>
    <h2>{{game.name}}</h2>
    <p>{{game.description}}</p>
    <h3>Number of questions {{game.numberOfQuestions}}</h3>
    <ul>
      <li v-for="(player, index) in players" :key="index">{{player.name}}</li>
    </ul>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from "axios";

export default {
  name: "lobby",
  props: {
    token: ""
  },
  data() {
    return {
      received_messages: {},
      game: {
        name: "default name",
        description:
          "this is null lobby, and this is fake data for development",
        numberOfQuestions: 0
      },
      quiz: {
        question: "",
        options: []
      },
      players: [
        {
          name: "Pelaaja2",
          score: 0,
          anwser: ""
        },
        {
          name: "nAku pentti",
          score: 9999999,
          anwser: ""
        },
        {
          name: "Leeroy",
          score: 10,
          anwser: ""
        }
      ],
      getData: {
        startGame: false,
        getData: true,
        terminateLobby: false
      },
      send_message: null,
      connected: false,
      erros: []
    };
  },
  methods: {
    send() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send(
          "/prty/game/host",
          JSON.stringify(this.getData),
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
            console.log("tämä on tick");
            console.log(tick);
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
      this.players = data.body.players;
      this.quiz.question = data.body.question;
      this.quiz.options = data.body.options;
    }
  }
};
</script>
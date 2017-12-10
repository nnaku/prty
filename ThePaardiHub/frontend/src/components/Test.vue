<template>
  <div>
    <h1>Test</h1>
    <h2>Connected? {{ connected}}</h2>
    <div>
      <button @click="tickleConnection">{{ connected ? 'disconnect' : 'connect' }}</button>
    </div>

    <h2>Players</h2>
    <button @click="addPlayer">Add player</button>
    <p v-for="(player, index) in players" :key="index">{{ player }}</p>

    <h2>Send Message</h2>
    <button @click="send">Get data</button>
    <h2>Message Received</h2>
    <p v-for="(message, index) in received_messages" :key="index">{{ message }}</p>

    <h2>Errors</h2>
    <p v-for="(error, index) in errors" :key="index">{{ error }}</p>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from "axios";

export default {
  name: "test",
  data() {
    return {
      playercount: 0,
      players: [],
      received_messages: [],
      received_body: "",
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
    addPlayer() {
      this.playercount++;
      this.$nextTick(function() {
        axios
          .post("/addplayer", {
            name: "player" + this.playercount,
            key: "qwerty"
          })
          .then(response => {
            this.errors = [];
          })
          .catch(e => {
            this.errors.push(e);
          });
      });
    },
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
            this.received_messages.push(tick);
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
  }
};
</script>
<template>
  <div id="game">
      <p>{{body}}</p>
      <ready v-if="state === 'GAME_READY'" v-bind:body="body"/>
      <asking v-else-if="state == 'ASKING_QUESTION'" v-bind:body="body"/>
      <next v-else-if="state == 'CHANING_QUESTION'" v-bind:body="body"/>
      <end v-else-if="state == 'GAME_FINISHED'" v-bind:body="body"/>
      <lost v-else/>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from "axios";

// Game states
import ready from "./GameStates/GameReady.vue";
import asking from "./GameStates/AskingQuestion.vue";
import next from "./GameStates/NextQuestion.vue";
import end from "./GameStates/GameEnd.vue";
import lost from "./GameStates/NoGame.vue";

export default {
  name: "game",
  data() {
    return {
      received_messages: {},
      body: {},
      state:"",
      playerInfo: {},
      playerLeave: false,
      send_message: null,
      connected: false,
      erros: []
    };
  },
  methods: {
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
      this.state = this.body.state;
    },

  },
  components: {
    ready,
    asking,
    next,
    end,
    lost
  }
};
</script>
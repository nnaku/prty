<template>
  <div>
    <h1>Test</h1>
    <h2>Connected? {{ connected}}</h2>
    <div>
      <button @click="tickleConnection">{{ connected ? 'disconnect' : 'connect' }}</button>
    </div>
    <h2>Send Message</h2>
    <div>
      <input v-model="send_message" placeholder="Send Message">
      <button @click="send">Send</button>
    </div>
    <h2>Message Received</h2>
    <p v-for="(message, index) in received_messages" :key="index">{{ message.body }}</p>
  </div>
</template>

<script>
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
export default {
  name: 'test',
  data () {
    return {
      received_messages: [],
      received_body : "",
      send_message: null,
      connected: false
    }
  },
  methods: {
    send () {
      console.log('Send message:' + this.send_message)
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send('/prty/testIn', this.send_message, {})
      }
    },
    connect () {
      this.socket = new SockJS('http://localhost:8080/gs-guide-websocket')
      this.stompClient = Stomp.over(this.socket)
      this.stompClient.connect({}, (frame) => {
        this.connected = true
        console.log("tämä on frame "+ frame)
        this.stompClient.subscribe('/lobby/testOut', (tick) => {
          console.log("tämä on tick "+ tick)
          this.received_messages.push(JSON.parse(JSON.stringify(tick)))
        })
      }, (error) => {
        console.log(error)
        this.connected = false
      })
    },
    disconnect () {
      if (this.stompClient) {
        this.stompClient.disconnect()
      }
      this.connected = false
    },
    tickleConnection () {
      this.connected ? this.disconnect() : this.connect()
    }
  },
  mounted () {
    this.connect()
  }
}
</script>
<template>
  <div id="about">
     <div class="generalinfo">
       <p class="title">PRTY.fi</p>
       <p class="info">Spelled party without an A. With this everyone is able to invent the funniest,
         most situation fitting questions - teachers for a students in a classroom, organizer for a housewarming party,
         best man and briedsmaid for a wedding, parents for their childrens birthday party etc. Hosts can bring all
         participants together and have a great time.<br><br>
         Everyone is also able to join the game where they have been invited. They can compete and enjoy
         of the time with others!<br><br>
        <ul>
          <li>For different events</li>
          <li>Everyone could be a host of the game</li>
          <li>Brings people together</li>
        </ul>
      </p>
     </div>
     <div class="about">
      <p class="title">As a Host</p>
      <p class="info">You are able to create a question game and hosts that - 
        just register to the PRTY.fi and invite others to compete!
        When you are, for example, with your buddies or in a party with guests, 
        invite them to a special game where the questions are made by yourself! 
        Nothing is stopping you to create questions of animals, movies or for example 
        of yourself or your buddies. If you don't have a time to create questions, no worries. PRTY.fi has premade questions
        you are free to use. Yeah right, sounds cool!<br><br>This is a fun way to mix up the typical party where everyone 
        just hangs out or plays bingo. PRTY.fi gives you also a low pressure to organize 
        parties - an agenda is already planned and you are in charge of the entertainment!<br>
        <ul>
          <li>Register to the PRTY.fi and host games anywhere you are</li>
          <li>Bring all your guests together</li>
          <li>Create your own questions</li>
          <li>Use questions published by other users</li>
        </ul>
      </p>
      <p class="title">As a User</p>
      <p class="info">You are able to join a live hosted game. You get a Game Token from the Host. 
        Just type it on a main page, choose username and join to the game! When everyone else have also
        joined, the party is ready for begin - the questions will appear on the your device and
        you are also able to answer by this device. See your scores, see others scores and have fun!<br><br>
        <ul>
          <li>Join to the PRTY.fi game</li>
          <li>Compete with others</li>
          <li>Have fun</li>
        </ul>
      </p>
     </div>
     </div>
</template>

<script>
export default {
  name: "about"
};
</script>

<style scoped>
.generalinfo {
  background-color: #8db9b0;
  width: 70%;
  display: block;
  margin: 1.1em auto 0 auto;
  height: 100%;
  padding-top: 0.5em;
  color: white;
  height: 100%;
  font-size: 21px;
}

.about {
  background-color: #8db9b0;
  width: 70%;
  margin: 0.3em auto;
  padding-top: 0.5em;
  padding-bottom: 0.5em;
  color: white;
  font-size: 21px;
}

.title {
  font-weight: bold;
  text-transform: uppercase;
  text-align: center;
  margin-bottom: -1em;
}

.info {
  text-align: justify;
  padding: 1.5em 3.5em 0.1em 3.5em;
}

li {
  font-weight: bold;
}

@media only screen and (max-width: 768px) {
  .generalinfo {
    width: 90%;
    font-size: 15px;
  }
  .about {
    margin: 1% auto 1%;
    width: 90%;
    height: 100%;
    font-size: 15px;
  }
  .info {
    text-align: justify;
    padding: 1.5em 2.2em 0.1em 2.2em;
  }
}
</style>




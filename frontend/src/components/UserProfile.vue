<template>
  <div class="profile box">
    <div class="profile box-header ">
      <!-- Messages are from locale/locales.json -->
      <div class="profile title">{{$t('message.profileheader')}}</div>
      <div v-bind:class="status">{{responseMessage}}</div>
      <div class="info">
        <p class="infotext">
          {{$t('message.changeprofiledetails')}}
        </p>
      </div>
    </div>
    <div class="profile box-body">
        <input v-model="userinfo.firstname" type="text" :placeholder="$t('message.firstname')" :disabled="!isDisabled" >
        <input v-model="userinfo.lastname" type="text" :placeholder="$t('message.lastname')" :disabled="!isDisabled" >
        <input v-model="userinfo.username" type="text" :placeholder="$t('message.username')" :disabled="!isDisabled" >
        <input v-model="userinfo.email" type="email" :placeholder="Email" :disabled="false" >
        <input v-model="passwords.password" type="password" :placeholder="$t('message.currentpw')" v-show="changePassword">
        <span>&nbsp;*</span>
        <input id="newPw" v-model="passwords.newPassword" type="password" :placeholder="$t('message.newpw')" v-show="changePassword">
        <span>&nbsp;*</span>
        <input id="newPwVer" v-model="passwords.newPasswordVerify" type="password" :placeholder="$t('message.confirmnewpw')" v-show="changePassword">
        <span>&nbsp;*</span>
        <button class="confirm" type="button" @click="postForm()">{{$t('message.update')}}</button>
        <div class="button-group">
          <button v-on:click="changePW()" v-show="!changePassword">{{$t('message.changepw')}}</button>
        </div>
        <a class="cancel" type="button" @click="cancel" v-show="!isDisabled || changePassword">{{$t('message.cancel')}}</a>   
    </div>
      <div class="profile box-footer">
    </div>
  </div>
</template>

<script>
import axios from "axios";
const messages = {
  msg: "moi"
};
export default {
  name: "test",
  data() {
    return {
      responseMessage: "",
      status: "ERROR",
      isDisabled: true,
      changePassword: false,
      userinfo: {},
      userinfobackup: {},
      body: {},
      passwords: {
        password: "",
        newPassword: "",
        newPasswordVerify: ""
      },
      errors: []
    };
  },
  methods: {
    cancel: function() {
      // not eleganr way, but it does the trick!
      this.$router.go(this.$router.currentRoute);
    },
    changePW() {
      this.changePassword = true;
    },
    editUserInfo() {
      this.isDisabled = false;
    },
    pwMatch() {
      if (this.passwords.newPassword != passwords.newPasswordVerify) {
      }
    },
    pwValid() {
      if (this.passwords.newPassword != passwords.newPasswordVerify) {
      }
    },
    postForm() {
      this.$set(this.body, "firstname", this.userinfo.firstname);
      this.$set(this.body, "lastname", this.userinfo.lastname);
      this.$set(this.body, "username", this.userinfo.username);
      if (this.userinfo.email != this.userinfobackup.email) {
        this.$set(this.body, "email", this.userinfo.email);
      }
      if (this.passwords.newPassword != "") {
        this.$set(this.body, "newPassword", this.passwords.newPassword);
      }
      if (this.passwords.newPasswordVerify != "") {
        this.$set(
          this.body,
          "newPasswordVerify",
          this.passwords.newPasswordVerify
        );
      }
      if (this.passwords.password != "") {
        this.$set(this.body, "password", this.passwords.password);
      }
      this.$nextTick(function() {
        axios
          .put("/user", this.body)
          .then(response => {
            this.status = response.data.status;
            this.responseMessage = response.data.message;
            this.isDisabled = true;
            this.changePassword = false;
            passwords = [];
          })
          .catch(e => {
            this.errors.push(e);
            this.status = e.response.data.status;
            this.responseMessage = e.response.data.message;
          });
      });
    }
  },
  mounted: function() {
    this.$nextTick(function() {
      axios
        .post("/user")
        .then(response => {
          this.userinfo = response.data;
          this.userinfobackup = response.data;
        })
        .catch(e => {
          this.errors.push(e);
        });
    });
  }
};
</script>

<style scoped>
.box > .box-header > .title {
  text-align: center;
  margin: 3% auto 4% auto;
  font-size: 1.5em;
  color: #db6e2d;
}
.box > .box-header > .error {
  text-align: center;
  margin: 3% auto 4% auto;
  font-size: 1.5em;
  color: #ffffff;
  background-color: Tomato;
}
.box > .box-body > input {
  width: 50%;
  height: 2.7em;
  display: block;
  margin: 2% auto;
  text-align: center;
  font-size: 1em;
}

.box > .box-body > .button-group > button {
  display: inline-block;
  margin: 1em auto;
  width: 15%;
  height: 100%;
  padding: 1em;
  border: none;
  background-color: #8db9b0;
}
.box > .box-body > .button-group > .confirm > button {
  display: block;
  margin: 1em auto;
  width: 15%;
  height: 3em;
  border: none;
  background-color: #8db9b0;
}
.box > .box-body > button {
  display: block;
  margin: 1em auto;
  width: 15%;
  height: 3em;
  border: none;
  background-color: #8db9b0;
}

.box > .box-footer > a {
  display: block;
  margin-top: 1rem;
  text-align: center;
  font-size: 0.8em;
  color: grey;
  /* text-decoration: underline; */
  cursor: pointer;
}

.info {
  width: 40%;
  margin: 0 auto;
}

.infotext {
  color: #8db9b0;
  font-weight: 540;
}

span {
  display: inline-block;
  color: red;
  font-weight: bold;
  text-align: left;
}

@media only screen and (max-width: 768px) {
  .box > .box-body > button {
    width: 30%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  }

  .info {
    width: 75%;
    margin: 0 auto;
  }
}
</style>


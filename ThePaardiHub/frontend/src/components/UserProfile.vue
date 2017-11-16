<template>
  <div class="profile box">
    <div class="profile box-header ">
      <div class="profile title">User Profile</div>
    </div>
    <div class="profile box-body">
        <input v-model="userinfo.firstname" type="text" placeholder="First name" :disabled="isDisabled" >
        <input v-model="userinfo.lastname" type="text" placeholder="Last name" :disabled="isDisabled" >
        <input v-model="userinfo.username" type="text" placeholder="User name" :disabled="isDisabled" >
        <input v-model="userinfo.email" type="email" placeholder="Email" :disabled="isDisabled" >
        <input v-model="passwords.password" type="password" placeholder="New password" v-show="changePassword">
        <input v-model="passwords.passwordVerify" type="password" placeholder="Confirm new password" v-show="changePassword">
        <input v-model="passwords.oldPassword" type="password" placeholder="Current password" v-show="changePassword">
        <div class="button-group">
          <button type="button" v-on:click="editUserInfo()" v-show="isDisabled">Edit your profile</button>
          <button class="confirm" type="button" @click="postForm()" v-show="!isDisabled || changePassword">Update</button>
        </div>

        <button v-on:click="changePW()" v-show="!changePassword">Change your password</button>
        
        <a class="cancel" type="button" v-on:click="cancel()" v-show="!isDisabled || changePassword">Cancel</a>
        
    </div>
    <div class="profile box-footer">
      
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "test",
  data() {
    return {
      isDisabled: true,
      changePassword: false,
      userinfo: [],
      userinfobackup: [],
      passwords: {
        password: "",
        passwordVerify: "",
        oldPassword: ""
      },
      errors: []
    };
  },
  methods: {
    cancel() {
      // this.isDisabled = true;
      // this.changePassword = false;
      Object.assign(this.$data, this.$options.data())
    },
    changePW() {
      this.changePassword = true;
    },
    editUserInfo() {
      this.isDisabled = false;
    }
  },
  mounted: function() {
    this.$nextTick(function() {
      axios
        .post("/user")
        .then(response => {
          this.userinfo = response.data;
        })
        .catch(e => {
          this.errors.push(e);
        });
      this.$nextTick(function() {
        this.userinfobackup = this.userinfo;
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
  color: #8db9b0;
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

@media screen and (min-width: 920px) {
  .box > .box-body > button {
    width: 15%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  }
}

@media screen and (max-width: 920px) and (min-width: 790px) {
  .box > .box-body > button {
    width: 20%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  }
}

@media screen and (max-width: 790px) {
  .box > .box-body > button {
    width: 30%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  }
}
@media screen and (max-width: 490px) {
  .box > .box-body > button {
    width: 50%;
    padding: 1em;
    font-size: 1em;
    height: 100%;
  }
}
</style>


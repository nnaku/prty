<template>
  <div class="registeration box">
    <div class="box-header register">
      <!-- Messages are from locale/locales.json -->
      <div class="title register">{{$t('message.registerheader')}}</div>
      <router-link class="routerToAbout" v-bind:to="'/about'">{{$t('message.goAbout')}}</router-link>
      <div v-bind:class="status" v-for="message in responseMessage">
        {{message}}
      </div>
    </div>
    <div class="box-body register">
        <input v-model="formData.firstname" type="text" :placeholder="$t('message.firstname')"> <span>&nbsp;</span>
        <input v-model="formData.lastname" type="text" :placeholder="$t('message.lastname')"> <span>&nbsp;</span>
        <input v-model="formData.username" type="text" :placeholder="$t('message.username')"> <span>*</span>
        <input v-bind:style="emailVer" v-model="formData.email" type="email" :placeholder="$t('message.email')"> <span>*</span>
        <p class="inputError" v-if="mailError">{{mailErrorMessage}}</p>
        <input v-bind:style="newPw" v-model="formData.password" type="password" :placeholder="$t('message.password')"> <span>*</span>
        <p class="inputError" v-if="pwError">{{$t('message.pwErrorInvalid')}}</p>
        <input v-bind:style="newPwVer" v-model="formData.passwordVerify" type="password" :placeholder="$t('message.confirmpasswd')"> <span>*</span>
        <p class="inputError" v-if="pwVerError">{{$t('message.pwErrorDoesNotMatch')}}</p>
        <button type="button" @keyup.enter="postForm()" v-on:click="postForm()">{{$t('message.submit')}}</button>
    </div>
    <div class="box-footer register">
      <router-link class="routerLink" v-bind:to="'/login'">{{$t('message.alreadyaccount')}}</router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "registeration-form",
  data() {
    return {
      responseMessage: {},
      status: "ERROR",
      mailErrorMessage: "Check your email",
      timer: null,
      pwError: false,
      pwVerError: false,
      mailError: false,
      formData: {
        firstname: "",
        lastname: "",
        username: "",
        email: "",
        password: "",
        passwordVerify: ""
      },
      emailVer: {
        borderLeftWidth: "",
        borderLeftColor: ""
      },
      newPw: {
        borderLeftWidth: "",
        borderLeftColor: ""
      },
      newPwVer: {
        borderLeftWidth: "",
        borderLeftColor: ""
      },
      response: {},
      errors: []
    };
  },
  watch: {
    "formData.email": function(query) {
      clearTimeout(this.timer);
      this.emailVer.borderLeftWidth = "10px";
      this.emailVer.borderLeftColor = "Orange";
      this.timer = setTimeout(() => {
        var re = /\S+@\S+\.\S+/;
        if (re.test(query)) {
          axios
            .post("/register/email", {
              email: query
            })
            .then(response => {
              if (response.data.status == "SUCCESS") {
                this.emailVer.borderLeftWidth = "10px";
                this.emailVer.borderLeftColor = "MediumSeaGreen";
                this.mailError = false;
              } else {
                this.emailVer.borderLeftWidth = "10px";
                this.emailVer.borderLeftColor = "Tomato";
                this.mailErrorMessage = response.data.message;
                this.mailError = true;
              }
            })
            .catch(e => {
              this.errors.push(e);
            });
        } else {
          this.emailVer.borderLeftWidth = "10px";
          this.emailVer.borderLeftColor = "Tomato";
          this.mailErrorMessage = "Check your email";
          this.mailError = true;
        }
      }, 500);
    },
    "formData.password": function(query) {
      this.newPw.borderLeftWidth = "10px";
      this.newPw.borderLeftColor = "Orange";
      clearTimeout(this.timer);
      this.timer = setTimeout(() => {
        var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
        if (re.test(this.formData.password)) {
          this.newPw.borderLeftWidth = "10px";
          this.newPw.borderLeftColor = "MediumSeaGreen";
          this.pwError = false;
        } else {
          this.newPw.borderLeftWidth = "10px";
          this.newPw.borderLeftColor = "Tomato";
          this.pwError = true;
        }
      }, 1000);
    },
    "formData.passwordVerify": function(query) {
      this.newPwVer.borderLeftWidth = "10px";
      this.newPwVer.borderLeftColor = "Orange";
      clearTimeout(this.timer);
      this.timer = setTimeout(() => {
        if (
          this.formData.password != this.formData.passwordVerify ||
          this.formData.passwordVerify == ""
        ) {
          this.newPwVer.borderLeftWidth = "10px";
          this.newPwVer.borderLeftColor = "Tomato";
          this.pwVerError = true;
        } else {
          this.newPwVer.borderLeftWidth = "10px";
          this.newPwVer.borderLeftColor = "MediumSeaGreen";
          this.pwVerError = false;
        }
      }, 500);
    }
  },
  methods: {
    postForm() {
      axios
        .post("/register", {
          firstname: this.formData.firstname,
          lastname: this.formData.lastname,
          username: this.formData.username,
          email: this.formData.email,
          password: this.formData.password,
          passwordVerify: this.formData.passwordVerify
        })
        .then(response => {
          this.responseMessage = response.data;
          this.status = response.data.status;
          this.$delete(this.responseMessage, "status");
          setTimeout(() => {
            this.$router.push('/login').bind(this);
          }, 1400);
        })
        .catch(e => {
          this.errors.push(e);
          this.status = e.response.data.status;
          this.responseMessage = e.response.data;
          this.$delete(this.responseMessage, "status");
        });
    }
  }
};
</script>

<style>
@import '../css/forms.css';
</style>
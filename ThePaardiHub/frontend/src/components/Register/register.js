export default {
    name: 'Register',
    props: {
      errorMessage: {
        type: String,
        required: false,
        default: ''
      },
      passwordMessage: {
        type: String,
        required: false,
        default: "Password must be at least 8 char"
      },
      passwordPattern: {
        type: String,
        required: false,
        default: '.{8,255}'
      }
    },
    data() {
      return {
        emailSuccessClass: '',
        passwordSuccessClass: '',
        emailIconClass: '',
        passwordIconClass: '',
        submitBtnDisabled: true,
      }
    },
    methods: {
      checkEmailValidation: function () {
        if (this.$refs.txtEmail.checkValidity()) {
          this.emailSuccessClass = 'has-success'
          this.emailIconClass = 'glyphicon glyphicon-ok form-control-feedback'
          if(this.$refs.txtPassword.checkValidity()) this.submitBtnDisabled = false
        } else {
          this.emailSuccessClass = 'has-error'
          this.emailIconClass = 'glyphicon glyphicon-remove form-control-feedback'
          this.submitBtnDisabled = true
        }
      },
      checkPasswordValidation: function () {
        if (this.$refs.txtPassword.checkValidity()) {
          this.passwordSuccessClass = 'has-success'
          this.passwordIconClass = 'glyphicon glyphicon-ok form-control-feedback'
          if(this.$refs.txtEmail.checkValidity()) this.submitBtnDisabled = false
        } else {
          this.passwordSuccessClass = 'has-error'
          this.passwordIconClass = 'glyphicon glyphicon-remove form-control-feedback'
          this.submitBtnDisabled = true
        }
      },
      submitLogin: function () {
        let email = this.$refs.txtEmail.value.trim()
        let password = this.$refs.txtPassword.value.trim()
        let passwordVerify = this.$refs.txtPasswordVerify.value.trim()
        let username = this.$refs.txtUsername.value.trim()
        let firstname = this.$refs.txtFirstname.value.trim()
        let lastname = this.$refs.txtLastname.value.trim()
        // COOKIE FUNCTIONS!
        // 'key=value; expires=current dateTime in UTC; path=/'
          document.cookie = 'email=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'
          document.cookie = 'password=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'
          console.log('We just deleted the cookies: ' + document.cookie)

          var data = JSON.stringify({
            "username": username,
            "password": password,
            "passwordVerify": passwordVerify,
            "firstname": firstname,
            "lastname": lastname,
            "email": email
          });
          
          var xhr = new XMLHttpRequest();
          xhr.withCredentials = true;
          xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
              console.log(this.responseText);
            }
          });
          xhr.open("POST", "http://localhost:8080/createaccount");
          xhr.setRequestHeader("content-type", "application/json");
          xhr.send(data);
      }
    },
  };
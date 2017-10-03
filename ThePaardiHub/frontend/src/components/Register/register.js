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
        default: "Password length must be greater than 4 but not longer than 10"
      },
      passwordPattern: {
        type: String,
        required: false,
        default: '.{5,10}'
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
    mounted: function () {
      let email = document.cookie.match('(^|;)\\s*' + 'email' + '\\s*=\\s*([^;]+)')
      let password = document.cookie.match('(^|;)\\s*' + 'password' + '\\s*=\\s*([^;]+)')
      this.$refs.txtEmail.value = email ? email.pop() : ''
      this.$refs.txtPassword.value = password ? password.pop() : ''
      if (email) this.submitBtnDisabled = false
      console.log('We just check to see if there were cookies: ' + document.cookie)
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
        // COOKIE FUNCTIONS!
        // 'key=value; expires=current dateTime in UTC; path=/'
          document.cookie = 'email=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'
          document.cookie = 'password=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'
          console.log('We just deleted the cookies: ' + document.cookie)
          this.$emit('loginCredentials',
          {
            'email': email,
            'password': password
          }
        ),

        
          register() {
          axios.post('http://localhost:8080/register', {
            body: this.emailSuccessClass, this.passwordSuccessClass 
          })
          .then(response => {
              console.log(response.data)
          })
          .catch(e => {
              this.errors.push(e)
          })
        }
    
      }}
    
  }

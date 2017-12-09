// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueAuth from '@websanova/vue-auth'
import i18n from './router/index'

Vue.config.productionTip = true
Vue.config.lang = 'fr';

Vue.router = router

Vue.use(VueAxios, axios)
Vue.use(VueAuth, {
  auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
  http: require('@websanova/vue-auth/drivers/http/axios.1.x.js'),
  router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js'),
  authRedirect: { path: '/login' },
  forbiddenRedirect: { path: '/403' },
  notFoundRedirect: { path: '/404' },
  tokenDefaultName: "Authorization",
  tokenStore: ['localStorage', 'cookie'],
  fetchData: { url: '', method: '', enabled: false },
  refreshData: {
    url: '/refresh',
    method: 'POST',
    enabled: false,
    interval: 30
  },
  registerData: {
    url: '/register',
    method: 'POST',
    redirect: '/login'
  },
  loginData: {
    url: '/login',
    method: 'POST',
    redirect: '/',
    fetchUser: false
  }
});

var app = new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  i18n,
}).$mount('#app');

// Create a Vue instance with 'i18n' option
//app({ i18n }).$mount('#app')

// Translations
/*const messages = {
  en: {
    message: {
      // HEADER
      // navbar unauthorized
      brand: 'PRTY.fi',
      about: 'About',
      register: 'Register',
      login: 'Login',
      // navbar additions (authorized)
      mygames: 'My Games',
      profile: 'Profile',
      logout: 'Logout',
      // FOOTER
      contact: 'Contact',
      // HOME
      gametoken: 'Please, enter Game Token here!',
      go: 'Go!',
      // ABOUT
      host: 'Host',
      user: 'User',
      // REGISTER, LOGIN AND PROFILE FORM
      // headers
      registerheader: 'Register an account',
      loginheader: 'Login here',
      profileheader: 'User Profile',
      // infotexts of forms
      changeprofiledetails: 'Here you can manage your user details. Add e.g. new email address or username and then update details. If you would like to change your password, please click Change Your Password button first.',
      // fields
      firstname: 'First name',
      lastname: 'Last name',
      username: 'Username',
      email: 'Email',
      password: 'Password',
      confirmpasswd: 'Confirm password',
      currentpw: 'Current password',
      newpw: 'New password',
      confirmnewpw: 'Confirm new password',
      // links
      alreadyaccount: 'Already have an account',
      forgotpw: 'Forgot your password?',
      createaccount: 'Create an account',
      cancel: 'Cancel',
      // buttons
      submit: 'Submit',
      login: 'Login',
      update: 'Update',
      changepw: 'Change your password',

    }
  },
  fr: {
    message: {
      // HEADER
      // navbar unauthorized
      brand: 'PRTY.fi',
      about: 'Autour de',
      register: 'Registrer',
      login: 'Login',
      // navbar additions (authorized)
      mygames: 'Mes jeux',
      profile: 'Profil',
      logout: 'Déconnexion',
      // FOOTER
      contact: 'Contact',
      // HOME
      gametoken: 'S\'il vous plaît, entrez Game Token ici!',
      go: 'Aller!',
      // ABOUT
      host: 'Ambianceur',
      user: 'Utilisateur',
      // REGISTER, LOGIN AND PROFILE FORM
      // headers
      registerheader: 'Enregistrer un compte',
      loginheader: 'Connectez-vous ici',
      profileheader: 'Profil de l\'utilisateur',
      // infotexts of forms
      changeprofiledetails: 'Ici vous pouvez gérer vos informations utilisateur. Ajouter par exemple nouvelle adresse e-mail ou nom d\'utilisateur, puis mettre à jour les détails. Si vous souhaitez changer votre mot de passe, cliquez d\'abord sur le bouton Changer votre mot de passe.',
      // fields
      firstname: 'Prénom',
      lastname: 'Nom de famille',
      username: 'Nom d\'utilisateur',
      email: 'Courriel',
      password: 'Mot de passe',
      confirmpasswd: 'Confirmez le mot de passe',
      currentpw: 'Mot de passe actuel',
      newpw: 'Nouveau mot de passe',
      confirmnewpw: 'Confirmer le nouveau mot de passe',
      // links
      alreadyaccount: 'Vous avez déjà un compte',
      forgotpw: 'Mot de passe oublié?',
      createaccount: 'Créer un compte',
      cancel: 'Se désabonner',
      // buttons
      submit: 'Envoyer',
      login: 'Login',
      update: 'Mise à jour',
      changepw: 'Changez votre mot de passe',
    }
  }
}*/

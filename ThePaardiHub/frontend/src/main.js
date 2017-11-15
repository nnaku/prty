// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueAuth from '@websanova/vue-auth'

Vue.config.productionTip = true

Vue.router = router
Vue.use(VueAxios, axios)
Vue.axios.defaults.baseURL = 'http://localhost:8080/';
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
  components: { App }
})

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueAuth from '@websanova/vue-auth'
import VueI18n from 'vue-i18n'
import localesJSON from './locale/locales.json'
import VueCookie from 'vue-cookie'

Vue.config.productionTip = true
Vue.config.lang = 'en';

Vue.router = router

// cookie monster
Vue.use(VueCookie)
// localize plugin
Vue.use(VueI18n)
// xhr plugin
Vue.use(VueAxios, axios)
// jwt plugin
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

export const i18n = new VueI18n({
  locale: 'en',
  messages: localesJSON
});

var app = new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  i18n
}).$mount('#app');
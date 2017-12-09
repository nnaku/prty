import Vue from 'vue'
import VueI18n from 'vue-i18n'
import Router from 'vue-router'
import Meta from 'vue-meta'

import Home from '@/components/Home'
import About from '@/components/About'
import User from '@/components/UserProfile'
import Login from '@/components/LoginForm'
import Register from '@/components/RegisterForm'
import ReserPassword from '@/components/ReserPasswordForm'
import Games from '@/components/Games'
import Logout from '@/components/Logout'

import messages from '../locale/message'

Vue.use(Router)
Vue.use(Meta)
// localize plugin
Vue.use(VueI18n)

const i18n = new VueI18n({
  locale: 'fr',
  messages: messages
});

// Pitäiskö tänne saada exportattua i18n?

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },{
      path: "/about",
      name: 'About',
      component: About
    },{
      path: "/user",
      name: 'User',
      meta: {auth: true},
      component: User
    },{
      path: "/games",
      name: 'Games',
      //meta: {auth: true},
      component: Games
    },{
      path: "/login",
      name: 'Login',
      component: Login
    },{
      path: "/logout",
      name: 'Logout',
      component: Logout
    },{
      path: "/register",
      name: 'Register',
      component: Register
    },{
      path: "/reset",
      name: 'ReserPassword',
      component: ReserPassword
    }
  ]
}) 

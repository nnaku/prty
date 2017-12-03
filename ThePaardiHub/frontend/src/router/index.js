import Vue from 'vue'
import Router from 'vue-router'
import vmodal from 'vue-js-modal'
import Meta from 'vue-meta'

import Home from '@/components/Home'
import About from '@/components/About'
import User from '@/components/UserProfile'
import Login from '@/components/LoginForm'
import Games from '@/components/Games'


Vue.use(Router)
Vue.use(vmodal)
Vue.use(Meta)

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
      meta: {auth: true},
      component: Games
    },{
      path: "/login",
      name: 'Login',
      component: Login
    }
  ]
})

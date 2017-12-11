import Vue from 'vue'
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
import Test from '@/components/Test'
import Lobby from '@/components/Lobby'
import Game from '@/components/Game'



Vue.use(Router)
Vue.use(Meta)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    }, {
      path: '/test',
      name: 'Test',
      component: Test
    }, {
      path: "/about",
      name: 'About',
      component: About
    }, {
      path: "/user",
      name: 'User',
      meta: { auth: true },
      component: User
    }, {
      path: "/games",
      name: 'Games',
      meta: { auth: true },
      component: Games
    }, {
      path: "/lobby/:token",
      name: 'Lobby',
      meta: { auth: true },
      component: Lobby
    }, {
      path: "/game/:token",
      name: 'Game',
      component: Game
    }, {
      path: "/login",
      name: 'Login',
      component: Login
    }, {
      path: "/logout",
      name: 'Logout',
      component: Logout
    }, {
      path: "/register",
      name: 'Register',
      component: Register
    }, {
      path: "/reset",
      name: 'ReserPassword',
      component: ReserPassword
    }
  ]
}) 

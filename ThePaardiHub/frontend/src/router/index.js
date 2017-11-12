import Vue from 'vue'
import Router from 'vue-router'
import vmodal from 'vue-js-modal'

import Home from '@/components/Home'
import About from '@/components/About'
import User from '@/components/UserProfile'
import Logout from '@/components/Logout'
import Login from '@/components/LoginForm'


Vue.use(Router)
Vue.use(vmodal)

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
      component: User
    },{
      path: "/logout",
      name: 'Logout',
      component: Logout
    },{
      path: "/login",
      name: 'Login',
      component: Login
    }
  ]
})

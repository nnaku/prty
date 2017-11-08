import Vue from 'vue'
import Router from 'vue-router'
import vmodal from 'vue-js-modal'
import Home from '@/components/Home'
import About from '@/components/About'
import Test from '@/components/Test'

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
      path: "/test",
      name: 'Test',
      component: Test
    }
  ]
})

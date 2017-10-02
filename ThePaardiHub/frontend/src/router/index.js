import Vue from 'vue'
import Router from 'vue-router'
import Test from '@/components/Test'
import Hello from '@/components/Hello'
import Home from '@/components/Home/Home.vue'
import Login from '@/components/Login/Login.vue'
import Account from '@/components/Account/Account.vue'
import Register from '@/components/Register/Register.vue'

Vue.use(Router)

export default new Router({
	routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/account',
            name: 'Account',
            component: Account
        },
        {
            path: '/test',
            name: 'Test',
            component: Test
        
        },
        {
            path: '/register',
            name: 'Register',
            component: Register
        }
    ]
})

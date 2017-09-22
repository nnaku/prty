import Vue from 'vue'
import Router from 'vue-router'
import Test from '@/components/Test'
import Hello from '@/components/Hello'
import Home from '@/components/Home/Home.vue'

Vue.use(Router)

export default new Router({
	routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/test',
            name: 'Test',
            component: Test
        }
    ]
})

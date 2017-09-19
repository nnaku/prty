import Vue from 'vue'
import Router from 'vue-router'
import Test from '@/components/Test'
import Hello from '@/components/Hello'

Vue.use(Router)

export default new Router({
  
	routes: [
        {
            path: '/',
            name: 'Hello',
            component: Hello
        },
        {
        path: '/test',
        name: 'Test',
        component: Test
        }
    ]
})
import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Events from '@/components/Events'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Events',
      component: Events
    },
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
    }
  ]
})

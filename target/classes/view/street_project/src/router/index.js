import Vue from 'vue'
import Router from 'vue-router'
import SuperAdmin from '@/components/SuperAdmin'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/SuperAdmin',
      name: 'SuperAdmin',
      component: SuperAdmin
    }]
})

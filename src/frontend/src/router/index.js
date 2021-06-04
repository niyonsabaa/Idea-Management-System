import Vue from 'vue'
import Router from 'vue-router'

// Containers
const TheContainer = () => import('@/containers/TheContainer')

// Views
const Dashboard = () => import('@/views/Dashboard')

import NewIdea from "../views/ideas/NewIdea.vue";
import IdeaList from "../views/ideas/IdeaList.vue";

import NewGender from "../views/gender/NewGender.vue"
import GenderList from "../views/gender/GenderList.vue";
import Login from "../views/Login.vue"
import Register from "../views/Register.vue"
import Notes from "../views/Notes.vue"
import Attachments from "../views/Attachments.vue"

Vue.use(Router)

export default new Router({
  mode: 'hash', // https://router.vuejs.org/api/#mode
  linkActiveClass: 'active',
  scrollBehavior: () => ({ y: 0 }),
  routes: configRoutes()
})

function configRoutes() {
  return [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
   
    {
      
      path: '/',
      redirect: '/dashboard',
      name: 'Home',
      component: TheContainer,
      children: [
        {
          path: 'dashboard',
          name: 'Dashboard',
          component: Dashboard
        }, 
        {
          path: '/notes',
          name: 'Notes',
          component: Notes,
        },  
        {
          path: '/attachments',
          name: 'Attachments',
          component: Attachments,
        },        
        {
          path: 'ideas',
          redirect: '/ideas/cards',
          name: 'Ideas',
          component: {
            render(c) { return c('router-view') }
          },
          children: [

            {
              path: 'newIdea',
              name: 'NewIdea',
              component: NewIdea
            },

            {
              path: 'ideaList',
              name: 'IdeaList',
              component: IdeaList
            },
          ]
        },
        {
          path: 'gender',
          redirect: '/gender/cards',
          name: 'Gender',
          component: {
            render(c) { return c('router-view') }
          },
          children: [

            {
              path: 'newGender',
              name: 'NewGender',
              component: NewGender
            },

            {
              path: 'genderList',
              name: 'GenderList',
              component: GenderList
            },
          ]
        }

      ]
    }
  ]
}


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
import Users from "../views/Users.vue"
import NewCountry from "../views/countries/NewCountry.vue"
import CountryList from "../views/countries/CountryList.vue"
import NewCategory from "../views/category/NewCategory.vue"
import CategoryList from "../views/category/CategoryList.vue"
import NewPostfix from "../views/postfix/NewPostfix.vue"
import PostfixList from"../views/postfix/PostfixList.vue"
import NewPrefix from "../views/prefix/NewPrefix.vue"
import PrefixList from "../views/prefix/PrefixList.vue"
import NewPriority from "../views/priority/NewPriority.vue"
import PriorityList from "../views/priority/PriorityList.vue"
import Profile from "../views/Profile.vue";

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
      path: '/',
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
          path: '/users',
          name: 'Users',
          component: Users,
        }, 
        {
          path: '/profile',
          name: 'Profile',
          component: Profile,
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
        },
        {
          path: 'countries',
          redirect: '/countries/cards',
          name: 'Countries',
          component: {
            render(c) { return c('router-view') }
          },
          children: [

            {
              path: 'newCountry',
              name: 'NewCountry',
              component: NewCountry
            },

            {
              path: 'countryList',
              name: 'CountryList',
              component: CountryList
            },
          ]
        },

        {
          path: 'category',
          redirect: '/category/cards',
          name: 'Category',
          component: {
            render(c) { return c('router-view') }
          },
          children: [

            {
              path: 'newCategory',
              name: 'NewCategory',
              component: NewCategory
            },

            {
              path: 'categoryList',
              name: 'CategoryList',
              component: CategoryList
            },
          ]
        },

        {
          path: 'postfix',
          redirect: '/postfix/cards',
          name: 'Postfix',
          component: {
            render(c) { return c('router-view') }
          },
          children: [

            {
              path: 'newPostfix',
              name: 'NewPostfix',
              component: NewPostfix
            },

            {
              path: 'postfixList',
              name: 'PostfixList',
              component: PostfixList
            },
          ]
        },

        {
          path: 'prefix',
          redirect: '/prefix/cards',
          name: 'Prefix',
          component: {
            render(c) { return c('router-view') }
          },
          children: [

            {
              path: 'newPrefix',
              name: 'NewPrefix',
              component: NewPrefix
            },

            {
              path: 'prefixList',
              name: 'PrefixList',
              component: PrefixList
            },
          ]
        },

        {
          path: 'priority',
          redirect: '/priority/cards',
          name: 'Priority',
          component: {
            render(c) { return c('router-view') }
          },
          children: [

            {
              path: 'newPriority',
              name: 'NewPriority',
              component: NewPriority
            },

            {
              path: 'priorityList',
              name: 'PriorityList',
              component: PriorityList
            },
          ]
        }
      ]
    }
  ]
}


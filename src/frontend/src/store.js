import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
Vue.use(Vuex)

const state = {
  sidebarShow: 'responsive',
  sidebarMinimize: false,
  token:'',
  successRegistration:false,
  username:'',
  userId:'',
  role:'User'
}

const mutations = {
  toggleSidebarDesktop (state) {
    const sidebarOpened = [true, 'responsive'].includes(state.sidebarShow)
    state.sidebarShow = sidebarOpened ? false : 'responsive'
  },

  toggleSidebarMobile (state) {
    const sidebarClosed = [false, 'responsive'].includes(state.sidebarShow)
    state.sidebarShow = sidebarClosed ? true : 'responsive'
  },

  set (state, [variable, value]) {
    state[variable] = value
  },

  setToken(state, currentToken){
    state.token = currentToken
  },

  setUsername(state, currentUsername){
    state.username = currentUsername
  },

  setUserId(state,currentUserId){
    state.userId = currentUserId
  },

  setRole(state,currentRole){
    state.role = currentRole
  },

  setSuccessRegistration(state){
    state.successRegistration = true
  }
}

export default new Vuex.Store({
  plugins: [createPersistedState({
    storage: window.sessionStorage,
})],
  state,
  mutations
})
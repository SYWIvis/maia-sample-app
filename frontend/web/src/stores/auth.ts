import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', () => {
  const username = ref<string | null>(localStorage.getItem('auth_username'))
  const password = ref<string | null>(localStorage.getItem('auth_password'))

  function login(user: string, pass: string) {
    username.value = user
    password.value = pass
    localStorage.setItem('auth_username', user)
    localStorage.setItem('auth_password', pass)
  }

  function logout() {
    username.value = null
    password.value = null
    localStorage.removeItem('auth_username')
    localStorage.removeItem('auth_password')
  }

  function isLoggedIn() {
    return username.value !== null
  }

  function getAuthHeader() {
    if (!username.value || !password.value) return null
    return 'Basic ' + btoa(`${username.value}:${password.value}`)
  }

  return { username, password, login, logout, isLoggedIn, getAuthHeader }
})

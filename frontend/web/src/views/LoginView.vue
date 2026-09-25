<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import axios from 'axios'

const username = ref('')
const password = ref('')
const errorMessage = ref('')
const authStore = useAuthStore()
const router = useRouter()

async function handleLogin() {
  errorMessage.value = ''
  try {
    await axios.get('/api/users/me', {
      auth: { username: username.value, password: password.value },
    })
    authStore.login(username.value, password.value)
    router.push('/products')
  } catch {
    errorMessage.value = 'ログインに失敗しました。'
  }
}
</script>

<template>
  <main>
    <h1>Login</h1>
    <form @submit.prevent="handleLogin">
      <div>
        <label>Username</label>
        <input v-model="username" type="text" />
      </div>
      <div>
        <label>Password</label>
        <input v-model="password" type="password" />
      </div>
      <button type="submit">Log in</button>
    </form>
    <p v-if="errorMessage">{{ errorMessage }}</p>
  </main>
</template>

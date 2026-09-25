<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import axios from 'axios'
import { useAuthStore } from '@/stores/auth'

const username = ref('')
const errorMessage = ref('')
const authStore = useAuthStore()

onMounted(async () => {
  try {
    const response = await axios.get('/api/users/me', {
      auth: {
        username: authStore.username ?? '',
        password: authStore.password ?? '',
      },
    })
    username.value = response.data
  } catch {
    errorMessage.value = 'アカウント情報の取得に失敗しました。ログインしてください。'
  }
})
</script>

<template>
  <main>
    <h1>My Account</h1>
    <p v-if="errorMessage">{{ errorMessage }}</p>
    <div v-else>
      <p>Username: {{ username }}</p>
    </div>
    <RouterLink to="/change-password">Change Password</RouterLink>
  </main>
</template>

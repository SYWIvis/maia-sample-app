<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const newPassword = ref('')
const errorMessage = ref('')
const successMessage = ref('')
const authStore = useAuthStore()
const router = useRouter()

async function handleChangePassword() {
  errorMessage.value = ''
  successMessage.value = ''
  try {
    await axios.post('/api/users/change-password', { newPassword: newPassword.value }, {
  auth: {
    username: authStore.username ?? '',
    password: authStore.password ?? '',
  },
})
    authStore.login(authStore.username ?? '', newPassword.value)
    successMessage.value = 'パスワードを変更しました。'
    newPassword.value = ''
  } catch {
    errorMessage.value = 'パスワードの変更に失敗しました。'
  }
}
</script>

<template>
  <main>
    <h1>Change Password</h1>
    <form @submit.prevent="handleChangePassword">
      <div>
        <label>New Password</label>
        <input v-model="newPassword" type="password" />
      </div>
      <button type="submit">Change Password</button>
    </form>
    <p v-if="errorMessage">{{ errorMessage }}</p>
    <p v-if="successMessage">{{ successMessage }}</p>
  </main>
</template>

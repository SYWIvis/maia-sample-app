<script setup lang="ts">
import { RouterLink, RouterView, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const authStore = useAuthStore()
const router = useRouter()

function handleLogout() {
  authStore.logout()
  router.push('/login')
}
</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/products">Products</RouterLink>

      <template v-if="authStore.isLoggedIn()">
        <RouterLink to="/profile">My Account</RouterLink>
        <button @click="handleLogout">Log out</button>
      </template>
      <template v-else>
        <RouterLink to="/login">Login</RouterLink>
      </template>
    </nav>
  </header>

  <RouterView />
</template>

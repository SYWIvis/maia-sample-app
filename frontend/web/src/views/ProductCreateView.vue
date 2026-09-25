<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { productControllerApi } from '@/api-client'

const name = ref('')
const price = ref<number | null>(null)
const errorMessage = ref('')
const router = useRouter()

async function handleCreate() {
  errorMessage.value = ''
  try {
    await productControllerApi().createProduct({
      name: name.value,
      price: price.value ?? 0,
    })
    router.push('/products')
  } catch {
    errorMessage.value = '作成に失敗しました。ログインが必要です。'
  }
}
</script>

<template>
  <main>
    <h1>Create Product</h1>
    <form @submit.prevent="handleCreate">
      <div>
        <label>Name</label>
        <input v-model="name" type="text" />
      </div>
      <div>
        <label>Price</label>
        <input v-model.number="price" type="number" />
      </div>
      <button type="submit">Create</button>
    </form>
    <p v-if="errorMessage">{{ errorMessage }}</p>
  </main>
</template>

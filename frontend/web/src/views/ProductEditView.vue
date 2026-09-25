<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { productControllerApi } from '@/api-client'

const route = useRoute()
const router = useRouter()
const id = route.params.id as string

const name = ref('')
const price = ref<number | null>(null)
const errorMessage = ref('')

onMounted(async () => {
  const products = (await productControllerApi().getAllProducts()).data
  const current = products.find((p) => p.id === id)
  if (current) {
    name.value = current.name ?? ''
    price.value = current.price ?? 0
  }
})

async function handleUpdate() {
  errorMessage.value = ''
  try {
    await productControllerApi().updateProduct(id, {
      name: name.value,
      price: price.value ?? 0,
    })
    router.push('/products')
  } catch {
    errorMessage.value = '更新に失敗しました。ログインが必要です。'
  }
}
</script>

<template>
  <main>
    <h1>Edit Product</h1>
    <form @submit.prevent="handleUpdate">
      <div>
        <label>Name</label>
        <input v-model="name" type="text" />
      </div>
      <div>
        <label>Price</label>
        <input v-model.number="price" type="number" />
      </div>
      <button type="submit">Update</button>
    </form>
    <p v-if="errorMessage">{{ errorMessage }}</p>
  </main>
</template>

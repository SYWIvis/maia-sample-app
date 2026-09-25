<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { productControllerApi } from '@/api-client'
import type { Product } from '@/generated/api-client'

const products = ref<Product[]>([])
const loading = ref(true)
const errorMessage = ref('')

async function loadProducts() {
  loading.value = true
  try {
    products.value = (await productControllerApi().getAllProducts()).data
  } catch (error) {
    errorMessage.value = 'Failed to load products.'
    console.error(error)
  } finally {
    loading.value = false
  }
}

async function handleDelete(id: string) {
  if (!confirm('本当に削除しますか？')) return
  try {
    await productControllerApi().deleteProduct(id)
    await loadProducts()
  } catch {
    alert('削除に失敗しました。ログインが必要です。')
  }
}

onMounted(loadProducts)
</script>

<template>
  <main class="product-page">
    <h1>Products</h1>
    <RouterLink to="/products/create">+ New Product</RouterLink>

    <p v-if="loading">Loading products...</p>
    <p v-else-if="errorMessage" class="error">{{ errorMessage }}</p>

    <div v-else class="product-grid">
      <div v-for="product in products" :key="product.id" class="product-card">
        <h2>{{ product.name }}</h2>
        <p class="price">¥{{ (product.price ?? 0).toLocaleString() }}</p>
        <RouterLink :to="`/products/${product.id}/edit`">Edit</RouterLink>
        <button @click="handleDelete(product.id!)">Delete</button>
      </div>
    </div>
  </main>
</template>

<style scoped>
.product-page { max-width: 800px; margin: 0 auto; padding: 2rem; }
.product-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 1rem; margin-top: 1.5rem; }
.product-card { border: 1px solid #ddd; border-radius: 8px; padding: 1rem; text-align: center; }
.price { font-weight: bold; color: #2c3e50; }
.error { color: red; }
</style>

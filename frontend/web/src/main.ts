import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { i18n } from './locales/i18n'
import { globalErrorHandler } from './shared/error-handler/global-error-handler.ts'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(i18n)
app.use(globalErrorHandler)

app.mount('#app')

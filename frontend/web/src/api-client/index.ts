import axios from 'axios'
import * as apiClient from '@/generated/api-client'
import { NotFoundError, NetworkError, HttpError, UnknownError } from '@/shared/error-handler/custom-error'
import { useEventBus } from '@vueuse/core'
import { unhandledErrorEventKey } from '@/shared/events'
import { useAuthStore } from '@/stores/auth'

function createConfig(): apiClient.Configuration {
  const authStore = useAuthStore()
  const config = new apiClient.Configuration({
    username: authStore.username ?? undefined,
    password: authStore.password ?? undefined,
  })
  return config
}

const axiosInstance = axios.create({})
const unhandledErrorEventBus = useEventBus(unhandledErrorEventKey)

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (axios.isAxiosError(error)) {
      if (!error.response) {
        unhandledErrorEventBus.emit({ message: error.message })
        return Promise.reject(new NetworkError(error.message, error))
      }
      if (error.response.status === 404) {
        unhandledErrorEventBus.emit({ message: error.message, status: 404 })
        return Promise.reject(new NotFoundError(error.message, error))
      }
      unhandledErrorEventBus.emit({ message: error.message, status: error.response.status })
      return Promise.reject(new HttpError(error.message, error))
    }
    unhandledErrorEventBus.emit({ message: 'Unknown Error' })
    return Promise.reject(new UnknownError('Unknown Error', error))
  },
)

function productControllerApi() {
  return new apiClient.ProductControllerApi(createConfig(), '', axiosInstance)
}

export { productControllerApi }

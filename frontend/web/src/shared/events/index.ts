import type { EventBusKey } from '@vueuse/core'

type UnhandledErrorEventPayload = {
  message: string
  id?: string
  title?: string
  detail?: string
  status?: number
  timeout?: number
}

export const unhandledErrorEventKey: EventBusKey<UnhandledErrorEventPayload> =
  Symbol('unhandledErrorEventKey')

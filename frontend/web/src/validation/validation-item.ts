import { z } from 'zod'
import { i18n } from '@/locales/i18n'

const required = (message: string) => z.string().trim().min(1, message)

export function ValidationItems() {
  const { t } = i18n.global
  const validationItems = {
    email: z.string().email(t('email')),
    required: (requiredMessage: string = t('required')) => required(requiredMessage),
    requiredEmail: (requiredMessage: string = t('required')) =>
      required(requiredMessage).email(t('email')),
  }
  return validationItems
}

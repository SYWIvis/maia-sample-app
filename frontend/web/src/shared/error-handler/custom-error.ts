export interface ProblemDetails {
  detail: string
  exceptionId: string
  exceptionValues: string[]
  instance: string
  status: number
  title: string
  type: string
}

export abstract class CustomErrorBase extends Error {
  cause?: Error | null

  constructor(message: string, cause?: Error) {
    super(message)
    this.cause = cause
  }

  abstract toJSON(): Record<string, unknown>
}

export class HttpError extends CustomErrorBase {
  response?: ProblemDetails | null

  constructor(message: string, cause?: Error & { response?: { data?: ProblemDetails } }) {
    super(message, cause)
    this.response = cause?.response?.data ?? null
    this.name = 'HttpError'
  }

  toJSON() {
    return {
      timestamp: new Date().toISOString(),
      name: this.name,
      message: this.message,
      stack: this.stack,
      response: this.response ?? null,
      cause: this.cause ?? null,
    }
  }
}

export class NotFoundError extends HttpError {
  constructor(message: string, cause?: Error) {
    super(message, cause)
    this.name = 'NotFoundError'
  }
}

export class NetworkError extends HttpError {
  constructor(message: string, cause?: Error) {
    super(message, cause)
    this.name = 'NetworkError'
  }
}

export class UnknownError extends CustomErrorBase {
  constructor(message: string, cause?: Error) {
    super(message, cause)
    this.name = 'UnknownError'
  }

  toJSON() {
    return {
      timestamp: new Date().toISOString(),
      name: this.name,
      message: this.message,
      stack: this.stack,
      cause: this.cause ?? null,
    }
  }
}

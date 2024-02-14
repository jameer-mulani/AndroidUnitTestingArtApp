package com.jameermulani.hellounittestingandroid.util

data class Resource<out T>(
    val status: STATUS, val data: T?, val error: String?
) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(status = STATUS.SUCCESS, data = data, error = null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(status = STATUS.LOADING, data = null, error = null)
        }

        fun <T> error(message: String): Resource<T> {
            return Resource(status = STATUS.FAILED, data = null, error = message)
        }
    }
}


enum class STATUS {
    SUCCESS, LOADING, FAILED
}

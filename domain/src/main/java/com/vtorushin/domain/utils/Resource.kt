package com.vtorushin.domain.utils

import com.vtorushin.domain.utils.Status.ERROR
import com.vtorushin.domain.utils.Status.LOADING
import com.vtorushin.domain.utils.Status.SUCCESS
import com.vtorushin.domain.utils.Status.NOT_STARTED

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T? = null, message: String): Resource<T> =
            Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T? = null): Resource<T> =
            Resource(status = LOADING, data = data, message = null)
        fun <T> notStarted(data: T? = null): Resource<T> =
            Resource(status = NOT_STARTED, data = data, message = null)
    }
}
package com.ahrijksmuseum.view.mappers

import android.content.res.Resources
import com.ahrijksmuseum.view.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class UiErrorsMapper(private val resources: Resources) {

    fun map(error: Throwable?): String =
        when (error) {
            is ConnectException -> resources.getString(R.string.connect_error)
            is UnknownHostException -> resources.getString(R.string.unknown_host_error)
            is SocketTimeoutException -> resources.getString(R.string.socket_time_out_error)
            else -> resources.getString(R.string.unknown_network_error)
        }
}

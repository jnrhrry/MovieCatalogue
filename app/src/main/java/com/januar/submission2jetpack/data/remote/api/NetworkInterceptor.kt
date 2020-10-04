package com.januar.submission2jetpack.data.remote.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(context: Context): Interceptor{

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectionStatus())
            throw  NoInternetExceptions("No Connections.")
        return chain.proceed(chain.request())
    }

    private fun connectionStatus(): Boolean{
        val connectivityManager = applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var result = false

        if (Build.VERSION.SDK_INT > 23){
            connectivityManager?.let {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when{
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            }
        } else{
            connectivityManager.let {
                it?.activeNetworkInfo.apply {
                    result = when (this?.type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }


}
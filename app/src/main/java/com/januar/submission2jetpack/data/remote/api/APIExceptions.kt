package com.januar.submission2jetpack.data.remote.api

import java.io.IOException


class APIExceptions(message: String): IOException(message)

class NoInternetExceptions(message: String): IOException(message)
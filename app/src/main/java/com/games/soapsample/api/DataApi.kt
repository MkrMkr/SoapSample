package com.games.soapsample.api

import com.games.soapsample.request.SoapEnvelope
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DataApi {

    @Headers("Content-Type: text/xml", "Accept-Charset: utf-8")
    @POST("/server")
    fun requestStateInfo(@Body body: SoapEnvelope): Call<Any>;

}
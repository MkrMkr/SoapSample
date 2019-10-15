package com.games.soapsample.api

import com.games.soapsample.request.CitiesRequest
import com.games.soapsample.response.CitiesResponseEnvelope
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DataApi {

    @Headers("Content-Type: text/xml", "Accept-Charset: utf-8")
    @POST("/server")
    fun citiesAsSingle(@Body body: CitiesRequest): Single<CitiesResponseEnvelope>;

    @Headers("Content-Type: text/xml", "Accept-Charset: utf-8")
    @POST("/server")
    fun citiesAsCallback(@Body body: CitiesRequest): Call<Any>;

}
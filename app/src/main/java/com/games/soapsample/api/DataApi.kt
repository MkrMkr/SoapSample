package com.games.soapsample.api

import com.games.soapsample.request.CitiesRequestEnvelope
import com.games.soapsample.request.GetApartmentsRequestEnvelope
import com.games.soapsample.request.GetContractRequestEnvelope
import com.games.soapsample.response.CitiesResponseEnvelope
import com.games.soapsample.response.GetApartmentsResponseEnvelope
import com.games.soapsample.response.GetContractResponseEnvelope
import com.games.soapsample.utils.Envelope
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DataApi {

    @Headers("Content-Type: text/xml", "Accept-Charset: utf-8")
    @POST("/server")
    fun citiesAsSingle(@Body body: CitiesRequestEnvelope): Single<CitiesResponseEnvelope>;

    @Headers("Content-Type: text/xml", "Accept-Charset: utf-8")
    @POST("/server")
    fun getContractAsSingle(@Body body: Envelope): Single<GetContractResponseEnvelope>;

    @Headers("Content-Type: text/xml", "Accept-Charset: utf-8")
    @POST("/server")
    fun getApartmentsAsSingle(@Body body: GetApartmentsRequestEnvelope): Single<GetApartmentsResponseEnvelope>;
}
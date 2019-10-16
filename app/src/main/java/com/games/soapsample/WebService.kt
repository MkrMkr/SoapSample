package com.games.soapsample

import android.util.Log
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

class WebService() {
    val retrofit: Retrofit;

    private var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor {
        Log.i("myRequest", it)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    init {
        retrofit = Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create())
            .baseUrl("https://vonder-mock.dev.concisesoftware.com")
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }

    private fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES).build()

}
package com.games.soapsample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.games.soapsample.api.DataApi
import com.games.soapsample.request.Body
import com.games.soapsample.request.CitiesRequest
import com.games.soapsample.request.Header
import com.games.soapsample.request.ListOfCities
import com.games.soapsample.response.CitiesResponseEnvelope
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity() : AppCompatActivity() {

    private var webService: WebService = WebService()
    var citiesResponse: Single<CitiesResponseEnvelope>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataApi = webService.retrofit.create(DataApi::class.java)
        //only for tests
        send_req_button_call.setOnClickListener {
            makeRequestCall(dataApi)
        }

        send_req_button_rx.setOnClickListener {
            makeRequestRx(dataApi)
        }
    }

    //only for tests
    private fun makeRequestCall(dataApi: DataApi) {
        val request = CitiesRequest(Header(), Body(ListOfCities()))

        val abcd: Call<Any> = dataApi.citiesAsCallback(request);

        abcd.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: retrofit2.Response<Any>) {
                Log.i("callTest", "call success ${response.body()}");
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i("callTest", "call error ${t.message}");
            }
        });
    }

    private fun makeRequestRx(dataApi: DataApi) {
        val request = CitiesRequest(Header(), Body(ListOfCities()))

        citiesResponse = dataApi.citiesAsSingle(request);

        citiesResponse?.let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Log.i("callTest", "loading started");
                }.doAfterTerminate {
                    Log.i("callTest", "loading finished");
                }.subscribe({
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show();
                    Log.i("callTest", "succes: $it");
                }, {
                    Log.i("callTest", "error: $it");
                });
        }
    }


}

package com.games.soapsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.games.soapsample.api.DataApi
import com.games.soapsample.request.Body
import com.games.soapsample.request.Header
import com.games.soapsample.request.ListOfCities
import com.games.soapsample.request.SoapEnvelope
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity() : AppCompatActivity() {

    private var webService: WebService = WebService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataApi = webService.retrofit.create(DataApi::class.java)
        send_req_button.setOnClickListener {
            makeRequest(dataApi)
        }
    }


    private fun makeRequest(dataApi: DataApi) {

        val request = SoapEnvelope(Header(), Body(ListOfCities()))

        val abcd: Call<Any> =
            dataApi.requestStateInfo(request);
        abcd.enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i("callTest", "${t.message}");
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.i("callTest", "response body: ${response.body()}");
            }
        });

    }


}

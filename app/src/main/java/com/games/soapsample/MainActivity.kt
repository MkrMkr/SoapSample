package com.games.soapsample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.games.soapsample.api.DataApi
import com.games.soapsample.request.*
import com.games.soapsample.response.CitiesResponseEnvelope
import com.games.soapsample.response.GetApartmentsResponseEnvelope
import com.games.soapsample.response.GetContractResponseEnvelope
import com.games.soapsample.utils.HeaderWithContent
import com.games.soapsample.utils.TokenContainer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {

    private var webService: WebService = WebService()
    var citiesResponse: Single<CitiesResponseEnvelope>? = null;
    var getContractResponse: Single<GetContractResponseEnvelope>? = null;
    var getApartmentsResponse: Single<GetApartmentsResponseEnvelope>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataApi = webService.retrofit.create(DataApi::class.java)

        send_cities_request.setOnClickListener {
            makeCitiesRequest(dataApi)
        }

        send_getcontract_request.setOnClickListener {
            makeGetContractRequest(dataApi)
        }

        send_get_apartments_request.setOnClickListener {
            makeGetApartments(dataApi)
        }
    }

    private fun makeCitiesRequest(dataApi: DataApi) {
        val request = CitiesRequestEnvelope(body = Body(ListOfCities()))

        citiesResponse = dataApi.citiesAsSingle(request);

        citiesResponse?.let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Log.i("callTest", "cities loading started");
                }.doAfterTerminate {
                    Log.i("callTest", "cities loading finished");
                }.subscribe({
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show();
                    Log.i("callTest", "cities succes: $it");
                }, {
                    Log.i("callTest", "cities error: $it");
                });
        }
    }

    private fun makeGetContractRequest(dataApi: DataApi) {
        //tokenTag valid till +- 15.11.2019
        val request = GetContractRequestEnvelope(
            HeaderWithContent(
                TokenContainer(
                    token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlJEVTBPREE1TjBNeE1FVkdRekEzTnpFeFJFTTJNRU5FTVRjelF6VTNRVUl3T0RNMk16aEdPQSJ9.eyJpc3MiOiJodHRwczovL2Rldi1rZHh2OGNocC5ldS5hdXRoMC5jb20vIiwic3ViIjoiQXlRZzcxSkR3cEdoTzFxbXNtdEV6b1E3aGNsTEhxcjBAY2xpZW50cyIsImF1ZCI6Imh0dHBzOi8vZGV2LWtkeHY4Y2hwLmV1LmF1dGgwLmNvbS9hcGkvdjIvIiwiaWF0IjoxNTcxMjA4ODQ4LCJleHAiOjE1NzM4MDA4NDgsImF6cCI6IkF5UWc3MUpEd3BHaE8xcW1zbXRFem9RN2hjbExIcXIwIiwic2NvcGUiOiJyZWFkOmNsaWVudF9ncmFudHMgY3JlYXRlOmNsaWVudF9ncmFudHMgZGVsZXRlOmNsaWVudF9ncmFudHMgdXBkYXRlOmNsaWVudF9ncmFudHMgcmVhZDp1c2VycyB1cGRhdGU6dXNlcnMgZGVsZXRlOnVzZXJzIGNyZWF0ZTp1c2VycyByZWFkOnVzZXJzX2FwcF9tZXRhZGF0YSB1cGRhdGU6dXNlcnNfYXBwX21ldGFkYXRhIGRlbGV0ZTp1c2Vyc19hcHBfbWV0YWRhdGEgY3JlYXRlOnVzZXJzX2FwcF9tZXRhZGF0YSBjcmVhdGU6dXNlcl90aWNrZXRzIHJlYWQ6Y2xpZW50cyB1cGRhdGU6Y2xpZW50cyBkZWxldGU6Y2xpZW50cyBjcmVhdGU6Y2xpZW50cyByZWFkOmNsaWVudF9rZXlzIHVwZGF0ZTpjbGllbnRfa2V5cyBkZWxldGU6Y2xpZW50X2tleXMgY3JlYXRlOmNsaWVudF9rZXlzIHJlYWQ6Y29ubmVjdGlvbnMgdXBkYXRlOmNvbm5lY3Rpb25zIGRlbGV0ZTpjb25uZWN0aW9ucyBjcmVhdGU6Y29ubmVjdGlvbnMgcmVhZDpyZXNvdXJjZV9zZXJ2ZXJzIHVwZGF0ZTpyZXNvdXJjZV9zZXJ2ZXJzIGRlbGV0ZTpyZXNvdXJjZV9zZXJ2ZXJzIGNyZWF0ZTpyZXNvdXJjZV9zZXJ2ZXJzIHJlYWQ6ZGV2aWNlX2NyZWRlbnRpYWxzIHVwZGF0ZTpkZXZpY2VfY3JlZGVudGlhbHMgZGVsZXRlOmRldmljZV9jcmVkZW50aWFscyBjcmVhdGU6ZGV2aWNlX2NyZWRlbnRpYWxzIHJlYWQ6cnVsZXMgdXBkYXRlOnJ1bGVzIGRlbGV0ZTpydWxlcyBjcmVhdGU6cnVsZXMgcmVhZDpydWxlc19jb25maWdzIHVwZGF0ZTpydWxlc19jb25maWdzIGRlbGV0ZTpydWxlc19jb25maWdzIHJlYWQ6ZW1haWxfcHJvdmlkZXIgdXBkYXRlOmVtYWlsX3Byb3ZpZGVyIGRlbGV0ZTplbWFpbF9wcm92aWRlciBjcmVhdGU6ZW1haWxfcHJvdmlkZXIgYmxhY2tsaXN0OnRva2VucyByZWFkOnN0YXRzIHJlYWQ6dGVuYW50X3NldHRpbmdzIHVwZGF0ZTp0ZW5hbnRfc2V0dGluZ3MgcmVhZDpsb2dzIHJlYWQ6c2hpZWxkcyBjcmVhdGU6c2hpZWxkcyBkZWxldGU6c2hpZWxkcyByZWFkOmFub21hbHlfYmxvY2tzIGRlbGV0ZTphbm9tYWx5X2Jsb2NrcyB1cGRhdGU6dHJpZ2dlcnMgcmVhZDp0cmlnZ2VycyByZWFkOmdyYW50cyBkZWxldGU6Z3JhbnRzIHJlYWQ6Z3VhcmRpYW5fZmFjdG9ycyB1cGRhdGU6Z3VhcmRpYW5fZmFjdG9ycyByZWFkOmd1YXJkaWFuX2Vucm9sbG1lbnRzIGRlbGV0ZTpndWFyZGlhbl9lbnJvbGxtZW50cyBjcmVhdGU6Z3VhcmRpYW5fZW5yb2xsbWVudF90aWNrZXRzIHJlYWQ6dXNlcl9pZHBfdG9rZW5zIGNyZWF0ZTpwYXNzd29yZHNfY2hlY2tpbmdfam9iIGRlbGV0ZTpwYXNzd29yZHNfY2hlY2tpbmdfam9iIHJlYWQ6Y3VzdG9tX2RvbWFpbnMgZGVsZXRlOmN1c3RvbV9kb21haW5zIGNyZWF0ZTpjdXN0b21fZG9tYWlucyByZWFkOmVtYWlsX3RlbXBsYXRlcyBjcmVhdGU6ZW1haWxfdGVtcGxhdGVzIHVwZGF0ZTplbWFpbF90ZW1wbGF0ZXMgcmVhZDptZmFfcG9saWNpZXMgdXBkYXRlOm1mYV9wb2xpY2llcyByZWFkOnJvbGVzIGNyZWF0ZTpyb2xlcyBkZWxldGU6cm9sZXMgdXBkYXRlOnJvbGVzIHJlYWQ6cHJvbXB0cyB1cGRhdGU6cHJvbXB0cyByZWFkOmJyYW5kaW5nIHVwZGF0ZTpicmFuZGluZyIsImd0eSI6ImNsaWVudC1jcmVkZW50aWFscyJ9.MxhFIRemIroPEF3giGhnPuhX_kqu86BLTS7KnebVQYTQg2JjjHgL7LCE8xzE6s_tYyAaTHsbSoHDThbjhEWlaCK3GbLtAA0A0cAeUKq1SNdfmfvSnlC7VsFB9Ve5M8qiY9-j7pu7JzLbIg_xy3PYiqa4yxsDtFU1YmhzqPgbB4CTbUJzpCNf0JcLvrZMh1DkcfPVahHd7YlTFkT0gfa3y7ErN3jU3QwA6y6cfz3QWFwvab_iRb6b2IFlYbKWDCO9of5nCKy6jWYOqNw-Y6yJNDmdxp0AUC08vfYUWsDoyYqb0fIPUVgDzAG962C0Wbv07hxeS3HEg3h5olBVD20HhQ"
                )
            ),
            GetContractBody(
                GetContract()
            )
        )

        getContractResponse = dataApi.getContractAsSingle(request);

        getContractResponse?.let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Log.i("callTest", "getContract loading started");
                }.doAfterTerminate {
                    Log.i("callTest", "getContract loading finished");
                }.subscribe({
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show();
                    Log.i("callTest", "getContract succes: $it");
                }, {
                    Log.i("callTest", "getContract error: $it");
                });
        }
    }

    private fun makeGetApartments(dataApi: DataApi) {
        //tokenTag valid till +- 15.11.2019
        val request = GetApartmentsRequestEnvelope(
            body = GetApartmentsRequestBody(
                GetApartments(
                    apartmentsFilter = ApartmentsFilter(
                        cityId = CityId("141dc580-3afd-3dfc-b3b7-a017469e3eb2"),
                        typeId = TypeId("cd96665e-c7c4-3642-b46a-914a0971c395")
                    ),
                    offset = Offset("?"),
                    limit = Limit("1")
                )
            )
        )

        getApartmentsResponse = dataApi.getApartmentsAsSingle(request);

        getApartmentsResponse?.let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Log.i("callTest", "getApartments loading started");
                }.doAfterTerminate {
                    Log.i("callTest", "getApartments loading finished");
                }.subscribe({
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show();
                    Log.i("callTest", "getApartments succes: $it");
                }, {
                    Log.i("callTest", "getApartments error: $it");
                });
        }
    }

}

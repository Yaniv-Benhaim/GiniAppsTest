package co.il.giniappstest.networking

import co.il.giniappstest.data.models.NumbersResponse
import co.il.giniappstest.other.Constants.NUMBER_QUERY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NumberApi {

    @GET(NUMBER_QUERY)
    suspend fun getNumbers() : Response<NumbersResponse>
}
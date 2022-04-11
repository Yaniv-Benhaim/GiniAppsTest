package co.il.giniappstest.repositories

import co.il.giniappstest.data.models.NumbersResponse
import co.il.giniappstest.networking.NumberApi
import co.il.giniappstest.other.Constants.FAILED_REQUEST
import co.il.giniappstest.utils.Resource
import co.il.giniappstest.utils.Status
import retrofit2.HttpException
import java.io.IOException

class NumberRepository(private val numberApi: NumberApi) {


    suspend fun getNumbers() : Resource<NumbersResponse> {

        try {
            val response = numberApi.getNumbers()
            if (response.isSuccessful) {
                return Resource(Status.SUCCESS, response.body(), null)
            }
        } catch (e: IOException) {
            return Resource(Status.ERROR, null, e.message)
        } catch (e: HttpException) {
            return Resource(Status.ERROR, null, e.message)
        } catch (e: Exception) {
            return Resource(Status.ERROR, null, e.message)
        }

        return Resource(Status.ERROR, null, FAILED_REQUEST)
    }
}
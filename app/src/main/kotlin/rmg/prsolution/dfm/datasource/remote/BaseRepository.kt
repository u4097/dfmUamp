package com.oleg.photodocs.datasource.remote

import rmg.prsolution.dfm.data.repository.resouces.Resource
import rmg.prsolution.dfm.data.repository.resouces.ResourceState
import retrofit2.Response
import timber.log.Timber


/**
 * Created by Oleg Sitnikov on 2019-02-12
 */

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Resource<T>? {

        val result: Resource<T> = safeApiResource(call, errorMessage)
//        var data : Resource<T>? = null

        when (result.state) {
            is ResourceState.SUCCESS ->{}
//                Timber.tag("$$$").d("${result.state}  - ${result.data.toString()}")
            is ResourceState.ERROR -> {
                Timber.e("$errorMessage & Exception - ${result.message}")
            }
        }
        return result

    }

    private suspend fun <T : Any> safeApiResource(call: suspend () -> Response<T>, errorMessage: String): Resource<T> {
        val response = call.invoke()
        var message = ""
        var code = ""
        if (response.isSuccessful) return Resource(ResourceState.SUCCESS, response.body())

        try {
            val errorJsonString = response.errorBody()?.string()
            code = response.code().toString()
            message = errorJsonString.toString()
/*            message = JsonParser().parse(errorJsonString)
                .asJsonObject["message"]
                .asString*/
        } catch (e: Exception) {
            Timber.tag("$$$").d(e.message)
        }


        return Resource(ResourceState.ERROR, null, "$code - $message")
    }
}


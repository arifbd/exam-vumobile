package xyz.arifz.vumobile.data.datasource

import android.util.Log
import retrofit2.Response
import xyz.arifz.vumobile.data.remote.UserEndPoint
import xyz.arifz.vumobile.data.remote.UserListResponse
import xyz.arifz.vumobile.data.service.ApiClient

class UserRemoteDataSourceImpl : UserRemoteDataSource {

    companion object {
        private val TAG = UserRemoteDataSourceImpl::class.java.simpleName
    }

    override fun getUserListFromServer(pageNumber: Long): Response<UserListResponse>? {
        try {
            val response = ApiClient.client
                .create(UserEndPoint::class.java)
                .getUserListFromServer(pageNumber)
                .execute()

            Log.d(TAG, "$response")
            return response
        } catch (e: Exception) {
            Log.e(TAG, "$e")
        }

        return null
    }

}

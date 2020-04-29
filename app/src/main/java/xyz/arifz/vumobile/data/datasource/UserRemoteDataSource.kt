package xyz.arifz.vumobile.data.datasource

import retrofit2.Response
import xyz.arifz.vumobile.data.remote.UserListResponse

interface UserRemoteDataSource {
    fun getUserListFromServer(pageNumber: Long): Response<UserListResponse>?
}
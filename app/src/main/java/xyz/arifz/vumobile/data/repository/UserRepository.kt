package xyz.arifz.vumobile.data.repository

import retrofit2.Response
import xyz.arifz.vumobile.data.datasource.UserDataSource
import xyz.arifz.vumobile.data.datasource.UserRemoteDataSource
import xyz.arifz.vumobile.data.datasource.UserRemoteDataSourceImpl
import xyz.arifz.vumobile.data.remote.UserListResponse

class UserRepository : UserDataSource, UserRemoteDataSource {

    companion object {
        private var notificationRepository: UserRepository? = null
        private var remoteDataSource: UserRemoteDataSource = UserRemoteDataSourceImpl()

        @Synchronized
        @JvmStatic
        fun getInstance(): UserRepository {
            if (notificationRepository == null)
                notificationRepository = UserRepository()
            return notificationRepository!!
        }
    }

    override fun getUserList(pageNumber: Long): UserListResponse? {
        val response = getUserListFromServer(pageNumber)
        return response?.body()
    }

    override fun getUserListFromServer(pageNumber: Long): Response<UserListResponse>? {
        return remoteDataSource.getUserListFromServer(pageNumber)
    }
}
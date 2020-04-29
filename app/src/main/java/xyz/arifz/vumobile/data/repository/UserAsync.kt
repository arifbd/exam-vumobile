package xyz.arifz.vumobile.data.repository

import xyz.arifz.vumobile.data.remote.UserListResponse

interface UserAsync {

    interface UserCallback {
        fun onLoadSuccess(userListResponse: UserListResponse)
        fun onLoadFailed(exception: Exception)
    }

    fun getUsers(pageNumber: Long, callback: UserCallback)
}
package xyz.arifz.vumobile.data.datasource

import xyz.arifz.vumobile.data.remote.UserListResponse

interface UserDataSource {
    fun getUserList(pageNumber: Long): UserListResponse?
}
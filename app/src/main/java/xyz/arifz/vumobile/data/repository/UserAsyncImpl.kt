package xyz.arifz.vumobile.data.repository

import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.UiThread
import xyz.arifz.vumobile.data.remote.UserListResponse

@EBean
open class UserAsyncImpl : UserAsync {

    private val dataSource = UserRepository.getInstance()

    @Background
    override fun getUsers(pageNumber: Long, callback: UserAsync.UserCallback) {
        val response = dataSource.getUserList(pageNumber)
        usersAwait(response, callback)
    }

    @UiThread
    open fun usersAwait(response: UserListResponse?, callback: UserAsync.UserCallback) {
        if (response == null)
            callback.onLoadFailed(Exception())
        else
            callback.onLoadSuccess(response)
    }

}
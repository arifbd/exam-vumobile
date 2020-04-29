package xyz.arifz.vumobile.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import xyz.arifz.vumobile.data.dto.User
import xyz.arifz.vumobile.data.remote.UserListResponse
import xyz.arifz.vumobile.data.repository.UserAsync
import xyz.arifz.vumobile.data.repository.UserAsyncImpl_
import xyz.arifz.vumobile.utils.Constants
import xyz.arifz.vumobile.viewmodel.live.SingleLiveEventKotlin

class MainViewModel(application: Application) : AndroidViewModel(application), UserAsync.UserCallback {

    var userList: ObservableList<User> = ObservableArrayList()
    var isLoading: ObservableBoolean = ObservableBoolean(true)
    var isLoadingMore: ObservableBoolean = ObservableBoolean(false)
    var isFirstPage: ObservableBoolean = ObservableBoolean(true)
    var itemClickSingleEvent: SingleLiveEventKotlin<User> = SingleLiveEventKotlin()
    var totalPageSingleEvent: SingleLiveEventKotlin<Long> = SingleLiveEventKotlin()
    private var userAsync: UserAsync = UserAsyncImpl_.getInstance_(application)

    fun fetchUsers(pageNumber: Long) {
        isLoadingMore.set(pageNumber > Constants.INITIAL_CURRENT_PAGE_NUMBER)
        isFirstPage.set(pageNumber == Constants.INITIAL_CURRENT_PAGE_NUMBER)
        userAsync.getUsers(pageNumber, this)
    }

    override fun onLoadSuccess(userListResponse: UserListResponse) {
        val users = userListResponse.users
        if (!users.isNullOrEmpty())
            userList.addAll(users)
        totalPageSingleEvent.value = userListResponse.totalPages
        isLoading.set(false)
        isLoadingMore.set(false)
    }

    override fun onLoadFailed(exception: Exception) {
        isLoading.set(false)
        isLoadingMore.set(false)
    }
}
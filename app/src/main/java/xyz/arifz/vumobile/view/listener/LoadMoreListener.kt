package xyz.arifz.vumobile.view.listener

import xyz.arifz.vumobile.utils.Constants

interface LoadMoreListener {
    fun goToNextPage(currentPage: Long = Constants.INITIAL_CURRENT_PAGE_NUMBER, totalPage: Long = Constants.INITIAL_TOTAL_PAGE_NUMBER)
}
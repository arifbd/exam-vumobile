package xyz.arifz.vumobile.view.base

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.arifz.vumobile.utils.Constants
import xyz.arifz.vumobile.view.listener.LoadMoreListener

abstract class ScrollingRecyclerActivity : AppCompatActivity(), LoadMoreListener {
    lateinit var loadMoreListener: LoadMoreListener
    lateinit var mLayoutManager: GridLayoutManager
    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    private var visibleThreshold = 1
    var totalPage: Long = Constants.INITIAL_TOTAL_PAGE_NUMBER
    var currentPage: Long = Constants.INITIAL_CURRENT_PAGE_NUMBER
    var isLoadingMore: Boolean = false
    var isSearchModeOn: Boolean = false
    var isLoading: Boolean = false

    fun setTotalPageCounter(totalPage: Long) {
        isLoading = false
        this.totalPage = totalPage
    }

    fun setListener(loadMoreListener: LoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

    fun setRecyclerViewScrollListener(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                totalItemCount = mLayoutManager.itemCount
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
                if (!isSearchModeOn && !isLoadingMore && currentPage < totalPage) {
                    if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        isLoading = true
                        currentPage++
                        loadMoreListener.goToNextPage(currentPage, totalPage)
                        isLoadingMore = true
                    }
                } else {
                    loadMoreListener.goToNextPage(-1, -1)
                    isLoadingMore = false
                }
            }
        })
    }
}
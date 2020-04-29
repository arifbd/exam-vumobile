package xyz.arifz.vumobile.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import xyz.arifz.vumobile.R
import xyz.arifz.vumobile.data.dto.User
import xyz.arifz.vumobile.databinding.ActivityMainBinding
import xyz.arifz.vumobile.utils.fullScreenImageDialog
import xyz.arifz.vumobile.view.adapters.UserRecycleAdapter
import xyz.arifz.vumobile.view.base.ScrollingRecyclerActivity
import xyz.arifz.vumobile.viewmodel.MainViewModel

class MainActivity : ScrollingRecyclerActivity() {
    private var binding: ActivityMainBinding? = null
    private var viewModel: MainViewModel? = null
    private var mAdapter: UserRecycleAdapter? = null
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initObservables()
        setListener(this)
        initRecyclerView(rv_user)
        setupRecyclerAdapter(rv_user)
        snackbar = binding?.root?.let { Snackbar.make(it, getString(R.string.message_no_new_data_available), Snackbar.LENGTH_LONG) }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        if (currentPage != totalPage)
            viewModel?.fetchUsers(currentPage)
    }

    private fun initObservables() {
        viewModel?.totalPageSingleEvent?.observe(this, Observer { totalPage -> setTotalPageCounter(totalPage) })
        viewModel?.itemClickSingleEvent?.observe(this, Observer { user -> userItemClicked(user) })
    }

    private fun initRecyclerView(mRecyclerView: RecyclerView) {
        mLayoutManager = GridLayoutManager(mRecyclerView.context, 2, GridLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = mLayoutManager
        setRecyclerViewScrollListener(rv_user)
    }

    private fun setupRecyclerAdapter(mRecyclerView: RecyclerView) {
        mAdapter = viewModel?.let { UserRecycleAdapter(ArrayList(0), it) }
        mRecyclerView.adapter = mAdapter
    }

    override fun goToNextPage(currentPage: Long, totalPage: Long) {
        if (currentPage == -1L && totalPage == -1L) {
            if (!isLoading) {
                if (snackbar?.isShown == false)
                    snackbar?.show()
                isLoading = false
            }
        } else
            viewModel?.fetchUsers(currentPage)
    }

    private fun userItemClicked(user: User?) {
        user?.avatar?.fullScreenImageDialog(this)
    }

}

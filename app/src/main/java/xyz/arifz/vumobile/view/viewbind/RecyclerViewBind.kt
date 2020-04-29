package xyz.arifz.vumobile.view.viewbind

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import xyz.arifz.vumobile.data.dto.User
import xyz.arifz.vumobile.view.adapters.UserRecycleAdapter

object RecyclerViewBind {

    @JvmStatic
    @BindingAdapter(value = ["user_items", "is_first_page"], requireAll = true)
    fun setUserItems(recyclerView: RecyclerView, items: List<User>, isFirstPage: Boolean) {
        if (isFirstPage)
            (recyclerView.adapter as UserRecycleAdapter?)?.replaceData(items)
        else
            (recyclerView.adapter as UserRecycleAdapter?)?.addData(items)
    }

}
package xyz.arifz.vumobile.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.arifz.vumobile.data.dto.User
import xyz.arifz.vumobile.databinding.ItemUserBinding
import xyz.arifz.vumobile.view.listener.ItemClickListener
import xyz.arifz.vumobile.viewmodel.MainViewModel

class UserRecycleAdapter(private var items: MutableList<User>, private var viewModel: MainViewModel) : RecyclerView.Adapter<UserRecycleAdapter.UserViewHolder>(), ItemClickListener {

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override
    fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item: User = items[position]
        holder.binding.item = item
        holder.binding.listener = this
    }

    override
    fun getItemCount(): Int {
        return items.size
    }

    fun replaceData(items: List<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addData(items: List<User>) {
        val users = items.distinctBy { it.id }
        this.items.clear()
        this.items.addAll(users)
        notifyItemChanged(this.items.size)
    }

    override fun onItemClicked(vararg any: Any?) {
        val user =  any[0]
        if (user != null && user is User)
            viewModel.itemClickSingleEvent.value = user
    }

    class UserViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}
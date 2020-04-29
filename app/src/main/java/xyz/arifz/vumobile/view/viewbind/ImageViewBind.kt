package xyz.arifz.vumobile.view.viewbind

import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import xyz.arifz.vumobile.R

object ImageViewBind {

    @JvmStatic
    @BindingAdapter(value = ["is_fullscreen","photoUrl"], requireAll = false)
    fun loadImageFromUrl(view: ImageView, fullScreen: Boolean, url: String?) {
        try {
            Glide.with(view.context)
                .load(url)
                .apply(RequestOptions().placeholder(R.drawable.def_avatar))
                .into(view)
        } catch (e: Exception) {
            view.setImageResource(R.drawable.def_avatar)
            Log.e(TAG, "$e")
        }

        try {
            val display: Display = view.display
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)
            var width = outMetrics.widthPixels
            if (!fullScreen)
                width = (outMetrics.widthPixels / 2) - 24
            val layoutParams = LinearLayout.LayoutParams(width, width)
            view.layoutParams = layoutParams
        } catch (e: Exception) {
            Log.e(TAG, "$e")
        }
    }

    private const val TAG: String = "ImageViewBind"
}
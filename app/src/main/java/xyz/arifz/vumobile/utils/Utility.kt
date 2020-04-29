package xyz.arifz.vumobile.utils

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.dialog_image.*
import xyz.arifz.vumobile.view.viewbind.ImageViewBind

fun String?.fullScreenImageDialog(context: Context){
    val d = Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    d.requestWindowFeature(Window.FEATURE_NO_TITLE)
    d.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    d.setContentView(xyz.arifz.vumobile.R.layout.dialog_image)
    ImageViewBind.loadImageFromUrl(d.imageView, true, this)
    d.imageView.setOnClickListener { d.dismiss() }
    d.show()
}
package xyz.arifz.vumobile

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import org.androidannotations.annotations.EApplication

@EApplication
open class App : Application() {

    override
    fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
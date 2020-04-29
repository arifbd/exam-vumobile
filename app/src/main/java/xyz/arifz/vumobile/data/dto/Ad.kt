package xyz.arifz.vumobile.data.dto

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ad(
    @SerializedName("company") val company: String,
    @SerializedName("url") val url: String,
    @SerializedName("text") val text: String
) : Serializable {
    override fun toString(): String {
        return GsonBuilder().serializeNulls().create().toJson(this)
    }
}
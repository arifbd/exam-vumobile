package xyz.arifz.vumobile.data.dto

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("avatar") val avatar: String
) : Serializable {
    override fun toString(): String {
        return GsonBuilder().serializeNulls().create().toJson(this)
    }
}
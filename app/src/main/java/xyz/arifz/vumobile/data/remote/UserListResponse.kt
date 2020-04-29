package xyz.arifz.vumobile.data.remote

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import xyz.arifz.vumobile.data.dto.Ad
import xyz.arifz.vumobile.data.dto.User
import java.io.Serializable

class UserListResponse(

    @SerializedName("page") val page: Long,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Long,
    @SerializedName("total_pages") val totalPages: Long,
    @SerializedName("data") val users: List<User>,
    @SerializedName("ad") val ad: Ad

) : Serializable {
    override fun toString(): String {
        return GsonBuilder().serializeNulls().create().toJson(this)
    }
}
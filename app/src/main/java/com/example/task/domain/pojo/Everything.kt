package com.example.task.domain.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Everything (

    @SerializedName("status")
    @Expose
    val status:String?,

    @SerializedName("totalResult")
    @Expose
    val totalResult:String?,

    @SerializedName("articles")
    @Expose
    val articles:Array<Article>?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Everything

        if (status != other.status) return false
        if (totalResult != other.totalResult) return false
        if (articles != null) {
            if (other.articles == null) return false
            if (!articles.contentEquals(other.articles)) return false
        } else if (other.articles != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status?.hashCode() ?: 0
        result = 31 * result + (totalResult?.hashCode() ?: 0)
        result = 31 * result + (articles?.contentHashCode() ?: 0)
        return result
    }
}
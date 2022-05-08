package com.abdurrahmanjun.core.remote

import com.google.gson.annotations.SerializedName

data class BasePagingResponse<T>(
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("count_per_page")
    var countPerPage: Int? = null,
    @SerializedName("current_page")
    var currentPage: Int? = null,
    @SerializedName("data")
    var `data`: List<T>? = null
)
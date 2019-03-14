package rmg.prsolution.dfm.model

import com.squareup.moshi.Json

data class ChannelResponse(
        @Json(name = "items")
        val items: List<Item?>?,
        @Json(name = "pages")
        val pages: Int?,
        @Json(name = "page")
        val page: Int?,
        @Json(name = "per_page")
        val perPage: Int?,
        @Json(name = "total")
        val total: Int?
)
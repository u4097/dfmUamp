package rmg.prsolution.dfm.domain.model

import com.squareup.moshi.Json

data class Item(
        @Json(name = "description")
        val description: String?,
        @Json(name = "stream_url")
        val streamUrl: String?,
        @Json(name = "id")
        val id: String?,
        @Json(name = "api_url")
        val apiUrl: String?,
        @Json(name = "logo_id")
        val logoId: String?,
        @Json(name = "logo_path")
        val logoPath: String?,
        @Json(name = "light_text")
        val lightText: Boolean?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "is_live")
        val isLive: Boolean?,
        @Json(name = "order")
        val order: Int?,
        @Json(name = "dummy_id")
        val dummyId: String?
)
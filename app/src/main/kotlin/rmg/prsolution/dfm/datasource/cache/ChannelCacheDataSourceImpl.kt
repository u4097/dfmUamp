package rmg.prsolution.dfm.datasource.cache

import kotlinx.coroutines.Deferred
import rmg.prsolution.dfm.cache.AppCache
import rmg.prsolution.dfm.data.datasource.ChannelCacheDataSource
import rmg.prsolution.dfm.domain.model.ChannelResponse


class ChannelCacheDataSourceImpl constructor(
        val cache: AppCache<ChannelResponse>
) : ChannelCacheDataSource {
    val key = "channel"

    override fun set(channels: ChannelResponse): Deferred<ChannelResponse> {
        return cache.save(key, channels)
    }

    override fun get(): Deferred<ChannelResponse> =
            cache.load(key)
}

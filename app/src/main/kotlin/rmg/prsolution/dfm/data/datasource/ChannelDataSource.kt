package rmg.prsolution.dfm.data.datasource

import kotlinx.coroutines.Deferred
import rmg.prsolution.dfm.data.repository.resouces.Resource
import rmg.prsolution.dfm.domain.model.ChannelResponse


interface ChannelCacheDataSource {

    fun get(): Deferred<ChannelResponse>

    fun set(channels: ChannelResponse): Deferred<ChannelResponse>

}

interface ChannelRemoteDataSource {

    suspend fun get(): Resource<ChannelResponse>?

}

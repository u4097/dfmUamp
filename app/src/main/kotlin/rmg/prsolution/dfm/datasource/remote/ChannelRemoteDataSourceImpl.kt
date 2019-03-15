package rmg.prsolution.dfm.datasource.remote

import com.oleg.photodocs.datasource.remote.BaseRepository
import rmg.prsolution.dfm.data.datasource.ChannelRemoteDataSource
import rmg.prsolution.dfm.data.repository.resouces.Resource
import rmg.prsolution.dfm.domain.model.ChannelResponse
import rmg.prsolution.dfm.networking.ChannelApi

class ChannelRemoteDataSourceImpl constructor(
    private val channelApi: ChannelApi
) : ChannelRemoteDataSource, BaseRepository() {

    override suspend fun get(): Resource<ChannelResponse>? {
        val response = safeApiCall(
            call = { channelApi.getChannelAsync().await() },
            errorMessage = "Error Auth user"
        )
        return response
    }


}
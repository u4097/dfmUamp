package rmg.prsolution.dfm.data.repository

import rmg.prsolution.dfm.data.datasource.ChannelCacheDataSource
import rmg.prsolution.dfm.data.datasource.ChannelRemoteDataSource
import rmg.prsolution.dfm.data.repository.resouces.Resource
import rmg.prsolution.dfm.data.repository.resouces.ResourceState
import rmg.prsolution.dfm.domain.model.ChannelResponse
import rmg.prsolution.dfm.domain.repository.ChannelRepository
import timber.log.Timber

class ChannelRepositoryImpl constructor(
        private val cacheDataSource: ChannelCacheDataSource,
        private val remoteDataSource: ChannelRemoteDataSource
) : ChannelRepository {

    override suspend fun get(refresh: Boolean): Resource<ChannelResponse>? {
        when (refresh) {
            true -> {
                val channels = remoteDataSource.get()
                // Save to cache
                cacheDataSource.set(channels = channels?.data!!)
                return channels
            }
        }
        var channelList: ChannelResponse?
        // Get from cache
        var response = cacheDataSource.get()
        response.await().let {
            channelList = it
        }
        channelList?.items.let {
            if (!channelList?.items?.isEmpty()!!) {
                Timber.d("Get channel from cache")
                return Resource(ResourceState.SUCCESS, channelList)
            }
        }

        val channels = remoteDataSource.get()
        Timber.d("Get channel from server:\n ${channels?.data}")
        channels?.data.let {
            try {
                // Save to cache
                cacheDataSource.set(channels = channels?.data!!)
            } catch (e: Exception) {
                Timber.e("Can't save channel into cache: ${e.stackTrace}")
            }
        }
        return channels
    }

}



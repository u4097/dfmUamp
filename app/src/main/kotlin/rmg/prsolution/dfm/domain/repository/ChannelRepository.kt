package rmg.prsolution.dfm.domain.repository

import rmg.prsolution.dfm.domain.model.ChannelResponse

interface ChannelRepository {
    suspend fun get(refresh: Boolean): rmg.prsolution.dfm.data.repository.resouces.Resource<ChannelResponse>?
}

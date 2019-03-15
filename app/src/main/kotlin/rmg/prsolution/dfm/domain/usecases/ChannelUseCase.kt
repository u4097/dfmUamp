package rmg.prsolution.dfm.domain.usecases

import rmg.prsolution.dfm.data.repository.resouces.Resource
import rmg.prsolution.dfm.domain.model.ChannelResponse
import rmg.prsolution.dfm.domain.repository.ChannelRepository


class ChannelUseCase constructor(private val channelRepository: ChannelRepository) {

    suspend fun get(refresh: Boolean): Resource<ChannelResponse>? =
        channelRepository.get(refresh)

}

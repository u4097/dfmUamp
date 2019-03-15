package rmg.prsolution.dfm.ui.viewmodels

import kotlinx.coroutines.launch
import rmg.prsolution.dfm.data.repository.resouces.Resource
import rmg.prsolution.dfm.domain.model.ChannelResponse
import rmg.prsolution.dfm.domain.usecases.ChannelUseCase
import rmg.prsolution.dfm.utils.SingleLiveEvent

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 22:22
 */


class ChannelViewModel constructor(val documentUseCase: ChannelUseCase) : AbstractViewModel() {

    val documents = SingleLiveEvent<Resource<ChannelResponse>>()

    fun getChannels(refresh: Boolean = false) {
        scope.launch {
            val response = documentUseCase.get(refresh)
            documents.postValue(response)
        }
    }

}
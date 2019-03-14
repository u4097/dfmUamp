package rmg.prsolution.dfm.networking

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import rmg.prsolution.dfm.model.ChannelResponse

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 21:40
 */


// Шаблоны документов
interface ChannelApi {
     @GET("channel_list")
     fun getChannelAsync(): Deferred<Response<ChannelResponse>>
}


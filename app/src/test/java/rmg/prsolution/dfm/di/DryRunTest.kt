package rmg.prsolution.dfm.di

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import rmg.prsolution.App
import java.util.concurrent.TimeUnit

class DryRunTest : AutoCloseKoinTest() {
    object DatasourceProperties {
        const val MOCK_URL = "http://localhost/mock/"
        const val DEV_URL = "https://dfm.ru/api/"
    }
//    val retrofit by inject<Retrofit>()
//    val mockRetrofit by inject<MockRetrofit>()
//    val mVm: ChannelViewModel by inject()
//    val debugRetrofitConfig by inject<DebugRetrofitConfig>()


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
/*
        startKoin {
            logger(Level.DEBUG)

            module {
                viewModel { ChannelViewModel(channelUseCase = get()) as ChannelViewModel }
                single {
                    DebugRetrofitConfig(
                            get(),
                            listOf(
                                    Endpoint("Mock", MOCK_URL, isMock = true),
                                    Endpoint("Develop", DEV_URL, isMock = false)
                            ),
                            get()
                    )
                }
            }
        }
*/


    }

    @Test
    fun testRemoteConfiguration() {
        // Use remote web service with SERVER_URL property from koin.properties file

//        loadAppModules()

//        mVm.getChannels()
    }

    @Test
    fun testLocalConfiguration() {
    }

    // OkHTTP Client
    private fun httpClient(): OkHttpClient {
        // Logging Interceptor
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        // Http Client Builder
        val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)

        clientBuilder.addInterceptor(httpLoggingInterceptor)
        clientBuilder.addInterceptor(ChuckInterceptor(App.instance))

        return clientBuilder.build()
    }

    // Retrofit
    private fun retrofitClient(httpClient: OkHttpClient): Retrofit {
//        val currentEndpoint: Endpoint = debugRetrofitConfig.currentEndpoint
        return Retrofit.Builder()
                .baseUrl("http://localhost/mock/")
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

    }


}
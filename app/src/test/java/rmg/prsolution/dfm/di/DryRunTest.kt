package rmg.prsolution.dfm.di

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import rmg.prsolution.App
import java.util.concurrent.TimeUnit

class DryRunTest : AutoCloseKoinTest() {
    val okHttpClient: OkHttpClient by inject()
    val retrofitClient: Retrofit by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

/*
    val testOkHttp = koinApplication {
        logger(Level.DEBUG)

        module {
            single{httpClient()}
            single { retrofitClient(get()) }
        }
    }
*/

    @Before
    fun before() {

    }

    @Test
    fun testRemoteConfiguration() {
        // Use remote web service with SERVER_URL property from koin.properties file

        startKoin {
            logger(Level.DEBUG)

            module {
                single { httpClient() as OkHttpClient }
                single { retrofitClient(get()) as Retrofit }
            }
        }

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
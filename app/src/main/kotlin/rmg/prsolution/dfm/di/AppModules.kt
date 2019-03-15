package rmg.prsolution.dfm.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import rmg.prsolution.dfm.AppConfiguration
import rmg.prsolution.dfm.cache.LruCache
import rmg.prsolution.dfm.data.datasource.ChannelCacheDataSource
import rmg.prsolution.dfm.data.datasource.ChannelRemoteDataSource
import rmg.prsolution.dfm.data.repository.ChannelRepositoryImpl
import rmg.prsolution.dfm.datasource.cache.ChannelCacheDataSourceImpl
import rmg.prsolution.dfm.datasource.remote.ChannelRemoteDataSourceImpl
import rmg.prsolution.dfm.domain.model.ChannelResponse
import rmg.prsolution.dfm.domain.repository.ChannelRepository
import rmg.prsolution.dfm.domain.usecases.ChannelUseCase
import rmg.prsolution.dfm.ui.viewmodels.ChannelViewModel

fun loadAppModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            networkModule,
            cacheModule
    )
}

// VIEW MODEL`S
val viewModelModule: Module = module {
    viewModel { ChannelViewModel(channelUseCase = get()) }
}

// USE CASES
val useCaseModule: Module = module {
    factory { ChannelUseCase(channelRepository = get()) }
}

// REPOSITORY
val repositoryModule: Module = module {
    single { ChannelRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as ChannelRepository }
}

// DATASOURCE (cache and remote)
val dataSourceModule: Module = module {
    // Channel
    single { ChannelRemoteDataSourceImpl(channelApi = get()) as ChannelRemoteDataSource }
    single { ChannelCacheDataSourceImpl(cache = get(DOCUMENT_CACHE)) as ChannelCacheDataSource }

}

// NETWORK API
val networkModule: Module = module {
    single { AppConfiguration.createChannelApi()}
}

// CACHE
val cacheModule: Module = module {
    single(name = DOCUMENT_CACHE) { LruCache<ChannelResponse>() }
}

private const val DOCUMENT_CACHE = "DOCUMENT_CACHE"




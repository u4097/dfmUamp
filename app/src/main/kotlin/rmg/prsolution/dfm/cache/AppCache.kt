package rmg.prsolution.dfm.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appmattus.layercache.Cache
import com.appmattus.layercache.createDiskLruCache
import com.appmattus.layercache.createLruCache
import com.appmattus.layercache.toLiveData
import kotlinx.coroutines.Deferred
import java.io.File

abstract class AppCache<T>  {
    abstract fun load(key: String): Deferred<T>
    abstract fun save(key: String, value: T?) : Deferred<T>
}

class LruCache<T> : AppCache<T>() {

    val cache = Cache.createLruCache<String, Any>(1000)

    override fun load(key: String): Deferred<T> = cache.get(key) as Deferred<T>

    override fun save(key: String, anyObject: T?): Deferred<T> =
        cache.set(key, anyObject!!) as Deferred<T>
}

class DiskLruCache<T>(cacheFile: File?) : AppCache<T>() {

    val cache = Cache.createDiskLruCache(cacheFile!!, 10000)

    override fun load(key: String): Deferred<T> = cache.get(key) as Deferred<T>

    override fun save(key: String, value: T?): Deferred<T> =
        cache.set(key, value as String) as Deferred<T>
}


class LiveDataCache<T> {

    val cache = Cache.createLruCache<String, Any>(1000)

    val liveDataCache = cache.toLiveData()

    fun load(key: String): MutableLiveData<T> = liveDataCache.get(key) as MutableLiveData<T>

    fun save(key: String, anyObject: T?): LiveData<T> =
        liveDataCache.set(key, anyObject!!) as LiveData<T>

}

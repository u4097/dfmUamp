package rmg.prsolution.dfm.ui.viewmodels

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

/**
 * Base ViewModel
 * - handle  jobs with launch() and clear them on onCleared
 */
abstract class AbstractViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    val scope = CoroutineScope(coroutineContext)

    private fun cancelAllRequests() = coroutineContext.cancel()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }
}